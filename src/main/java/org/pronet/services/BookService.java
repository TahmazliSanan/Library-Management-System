package org.pronet.services;

import org.pronet.payloads.BookDto;

import java.util.List;

public interface BookService {
    void addBook(BookDto bookDto, Long authorId);
    BookDto findBookById(Long bookId);
    List<BookDto> findAllBooks();
    void editBookDetails(BookDto bookDto);
    void removeBook(Long bookId);
    List<BookDto> searchBooks(String keyword);
}