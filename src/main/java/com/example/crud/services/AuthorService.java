package com.example.crud.services;

import com.example.crud.dtos.AuthorDto;
import com.example.crud.dtos.CustomDto;

import java.util.List;


public interface AuthorService {

    AuthorDto createAuthor(AuthorDto authorDto);

    AuthorDto getAuthor(Long id);

    boolean authorExist(Long id);

    AuthorDto updateAuthor(Long id, AuthorDto authorDto);

    void deleteAuthor(Long id);

    List<AuthorDto> findAuthorsWithAgeGreaterThan(Integer age);

    List<CustomDto> findAuthorsWithAgeLessThan(Integer age);


}
