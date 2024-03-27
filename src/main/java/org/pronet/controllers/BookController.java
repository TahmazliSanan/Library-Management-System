package org.pronet.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.pronet.payloads.BookDto;
import org.pronet.services.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BookController {
    private final BookService bookService;

    @GetMapping("/books/{authorId}/add")
    public String addBookView(
            @PathVariable("authorId") Long authorId,
            Model model) {
        BookDto bookDto = new BookDto();
        model.addAttribute("authorId", authorId);
        model.addAttribute("bookDto", bookDto);
        return "book/add-book";
    }

    @PostMapping("/books/{authorId}/add")
    public String addBook(
            @PathVariable("authorId") Long authorId,
            @Valid @ModelAttribute("bookDto") BookDto bookDto,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "book/add-book";
        }
        bookService.addBook(bookDto, authorId);
        return "redirect:/authors/details/" + authorId;
    }

    @GetMapping("/books/details/{bookId}")
    public String findBookById(
            @PathVariable("bookId") Long bookId,
            Model model) {
        BookDto bookDto = bookService.findBookById(bookId);
        model.addAttribute("bookDto", bookDto);
        return "book/book-details";
    }

    @GetMapping("/books")
    public String findAllBooks(
            Model model) {
        List<BookDto> bookDtoList = bookService.findAllBooks();
        model.addAttribute("bookDtoList", bookDtoList);
        return "book/book-list";
    }

    @GetMapping("/books/{bookId}/edit")
    public String editBookDetailsView(
            @PathVariable("bookId") Long bookId,
            Model model) {
        BookDto bookDto = bookService.findBookById(bookId);
        model.addAttribute("bookDto", bookDto);
        return "book/edit-book";
    }

    @PostMapping("/books/{bookId}/edit")
    public String editBookDetails(
            @PathVariable("bookId") Long bookId,
            @Valid @ModelAttribute("bookDto") BookDto bookDto,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "book/edit-book";
        }
        BookDto foundedBookDto = bookService.findBookById(bookId);
        bookDto.setId(bookId);
        bookDto.setAuthor(foundedBookDto.getAuthor());
        bookService.editBookDetails(bookDto);
        return "redirect:/books";
    }

    @GetMapping("/books/{bookId}/remove")
    public String removeBookView(
            @PathVariable("bookId") Long bookId,
            Model model) {
        BookDto bookDto = bookService.findBookById(bookId);
        model.addAttribute("bookDto", bookDto);
        return "book/remove-book";
    }

    @PostMapping("/books/{bookId}/remove")
    public String removeBook(
            @PathVariable("bookId") Long bookId) {
        bookService.removeBook(bookId);
        return "redirect:/books";
    }

    @GetMapping("/books/search")
    public String searchBooks(
            @RequestParam("keyword") String keyword,
            Model model) {
        List<BookDto> bookDtoList = bookService.searchBooks(keyword);
        model.addAttribute("bookDtoList", bookDtoList);
        return "book/book-list";
    }
}