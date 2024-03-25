package org.pronet.services;

import org.pronet.payloads.AuthorDto;

import java.util.List;

public interface AuthorService {
    void addAuthor(AuthorDto authorDto);
    AuthorDto findAuthorById(Long authorId);
    List<AuthorDto> findAllAuthors();
    void editAuthorDetails(AuthorDto authorDto);
    void removeAuthor(Long authorId);
}