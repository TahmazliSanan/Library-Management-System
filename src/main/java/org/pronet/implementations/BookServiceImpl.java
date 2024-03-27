package org.pronet.implementations;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.pronet.entities.Author;
import org.pronet.entities.Book;
import org.pronet.payloads.BookDto;
import org.pronet.repositories.AuthorRepository;
import org.pronet.repositories.BookRepository;
import org.pronet.services.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;

    @Override
    public void addBook(BookDto bookDto, Long authorId) {
        Optional<Author> author = authorRepository.findById(authorId);
        Book book = modelMapper.map(bookDto, Book.class);
        author.ifPresent(book::setAuthor);
        bookRepository.save(book);
    }

    @Override
    public BookDto findBookById(Long bookId) {
        Optional<Book> book = bookRepository.findById(bookId);
        return modelMapper.map(book, BookDto.class);
    }

    @Override
    public List<BookDto> findAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books
                .stream()
                .map((book) -> modelMapper.map(book, BookDto.class))
                .toList();
    }

    @Override
    public void editBookDetails(BookDto bookDto) {
        Book book = modelMapper.map(bookDto, Book.class);
        bookRepository.save(book);
    }

    @Override
    public void removeBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    @Override
    public List<BookDto> searchBooks(String keyword) {
        List<Book> books = bookRepository.searchBooks(keyword);
        return books
                .stream()
                .map((book) -> modelMapper.map(book, BookDto.class))
                .toList();
    }
}