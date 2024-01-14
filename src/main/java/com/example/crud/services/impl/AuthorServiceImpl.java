package com.example.crud.services.impl;

import com.example.crud.dtos.AuthorDto;
import com.example.crud.dtos.CustomDto;
import com.example.crud.entities.AuthorEntity;
import com.example.crud.exceptions.HttpException;
import com.example.crud.repositories.AuthorRepository;
import com.example.crud.services.AuthorService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    final private AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public boolean authorExist(Long id) {
        return this.authorRepository.existsById(id);
    }


    public AuthorDto createAuthor(AuthorDto authorDto) {
        AuthorEntity authorEntity = AuthorEntity.builder().name(authorDto.getName()).age(authorDto.getAge()).build();
        AuthorEntity newAuthor = this.authorRepository.save(authorEntity);

        return AuthorDto.builder().id(newAuthor.getId()).name(newAuthor.getName()).age(newAuthor.getAge()).build();
    }

    public AuthorDto getAuthor(Long id) {
        Optional<AuthorEntity> optionalAuthorEntity = this.authorRepository.findById(id);

        if (optionalAuthorEntity.isEmpty()) {
            throw new HttpException(HttpStatus.NOT_FOUND, "Invalid user id");
        }

        AuthorEntity authorEntity = optionalAuthorEntity.get();
        return AuthorDto.builder().name(authorEntity.getName()).age(authorEntity.getAge()).build();

    }

    public AuthorDto updateAuthor(Long id, AuthorDto authorDto) {

        if (!this.authorExist(id)) {
            throw new HttpException(HttpStatus.NOT_FOUND, "Invalid user id");
        }

        authorDto.setId(id);
        AuthorEntity authorEntity = AuthorEntity.builder().id(authorDto.getId()).name(authorDto.getName()).age(authorDto.getAge()).build();
        AuthorEntity updatedAuthor = this.authorRepository.save(authorEntity);
        return AuthorDto.builder().id(updatedAuthor.getId()).name(updatedAuthor.getName()).age(updatedAuthor.getAge()).build();

    }

    public void deleteAuthor(Long id){
        if (!this.authorExist(id)) {
            throw new HttpException(HttpStatus.NOT_FOUND, "Invalid user id");
        }

        this.authorRepository.deleteById(id);
    }


    public List<AuthorDto> findAuthorsWithAgeGreaterThan(Integer age) {
        List<AuthorEntity> authorEntities = this.authorRepository.findAuthorsWithAgeGreaterThan(age);
        List<AuthorDto> authorDtos = new ArrayList<>();

        for (AuthorEntity authorEntity : authorEntities) {
            AuthorDto authorDto = AuthorDto.builder().id(authorEntity.getId()).name(authorEntity.getName()).age(authorEntity.getAge()).build();
            authorDtos.add(authorDto);
        }

        return authorDtos;
    }


    public List<CustomDto> findAuthorsWithAgeLessThan(Integer age) {
        List<Object[]> objects = this.authorRepository.findAuthorsWithAgeLessThan(age);
        List<CustomDto> customDtos = new ArrayList<>();

        for (Object[] object : objects) {
            CustomDto customDto = CustomDto.builder().id((Long) object[0]).name((String) object[1]).build();
            customDtos.add(customDto);
        }

        return customDtos;
    }
}
