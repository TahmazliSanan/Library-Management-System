package org.pronet.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.pronet.payloads.AuthorDto;
import org.pronet.services.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping("/authors/add")
    public String addAuthorView(
            Model model) {
        AuthorDto authorDto = new AuthorDto();
        model.addAttribute("authorDto", authorDto);
        return "author/add-author";
    }

    @PostMapping("/authors/add")
    public String addAuthor(
            @Valid @ModelAttribute("authorDto") AuthorDto authorDto,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "author/add-author";
        }
        authorService.addAuthor(authorDto);
        return "redirect:/authors";
    }

    @GetMapping("/authors/details/{authorId}")
    public String findAuthorById(
            @PathVariable("authorId") Long authorId,
            Model model) {
        AuthorDto authorDto = authorService.findAuthorById(authorId);
        model.addAttribute("authorDto", authorDto);
        return "author/author-details";
    }

    @GetMapping("/authors")
    public String findAllAuthors(Model model) {
        List<AuthorDto> authorDtoList = authorService.findAllAuthors();
        model.addAttribute("authorDtoList", authorDtoList);
        return "author/author-list";
    }

    @GetMapping("/authors/{authorId}/edit")
    public String editAuthorDetailsView(
            @PathVariable("authorId") Long authorId,
            Model model) {
        AuthorDto authorDto = authorService.findAuthorById(authorId);
        model.addAttribute("authorDto", authorDto);
        return "author/edit-author";
    }

    @PostMapping("/authors/{authorId}/edit")
    public String editAuthorDetails(
            @PathVariable("authorId") Long authorId,
            @Valid @ModelAttribute("authorDto") AuthorDto authorDto,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "author/add-author";
        }
        authorDto.setId(authorId);
        authorService.editAuthorDetails(authorDto);
        return "redirect:/authors";
    }

    @GetMapping("/authors/{authorId}/remove")
    public String removeAuthorView(
            @PathVariable("authorId") Long authorId,
            Model model) {
        AuthorDto authorDto = authorService.findAuthorById(authorId);
        model.addAttribute("authorDto", authorDto);
        return "author/remove-author";
    }

    @PostMapping("/authors/{authorId}/remove")
    public String removeAuthor(
            @PathVariable("authorId") Long authorId) {
        authorService.removeAuthor(authorId);
        return "redirect:/authors";
    }
}