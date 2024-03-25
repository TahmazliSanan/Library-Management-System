package org.pronet.implementations;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.pronet.entities.Author;
import org.pronet.payloads.AuthorDto;
import org.pronet.repositories.AuthorRepository;
import org.pronet.services.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;

    @Override
    public void addAuthor(AuthorDto authorDto) {
        Author author = modelMapper.map(authorDto, Author.class);
        authorRepository.save(author);
    }

    @Override
    public AuthorDto findAuthorById(Long authorId) {
        Optional<Author> author = authorRepository.findById(authorId);
        return modelMapper.map(author, AuthorDto.class);
    }

    @Override
    public List<AuthorDto> findAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        return authors
                .stream()
                .map((author) -> modelMapper.map(author, AuthorDto.class))
                .toList();
    }

    @Override
    public void editAuthorDetails(AuthorDto authorDto) {
        Author author = modelMapper.map(authorDto, Author.class);
        authorRepository.save(author);
    }

    @Override
    public void removeAuthor(Long authorId) {
        authorRepository.deleteById(authorId);
    }
}