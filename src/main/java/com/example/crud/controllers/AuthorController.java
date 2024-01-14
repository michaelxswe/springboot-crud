package com.example.crud.controllers;

import com.example.crud.dtos.AuthorDto;
import com.example.crud.dtos.CustomDto;
import com.example.crud.services.AuthorService;
import jakarta.validation.Valid;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log
@RestController
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }


    @PostMapping(path = "/authors")
    public ResponseEntity<AuthorDto> createAuthor(@Valid @RequestBody AuthorDto authorDto) {
        AuthorDto res = this.authorService.createAuthor(authorDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @GetMapping(path = "/authors/{id}")
    public ResponseEntity<AuthorDto> getAuthor(@PathVariable Long id) {
        AuthorDto res = this.authorService.getAuthor(id);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }


    @GetMapping(path = "/authors", params = "ageGreaterThan")
    public ResponseEntity<List<AuthorDto>> getAgeGreaterThan(@RequestParam(name = "ageGreaterThan") Integer age) {
        List<AuthorDto> res = this.authorService.findAuthorsWithAgeGreaterThan(age);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }


    @GetMapping(path = "/authors", params = "ageLessThan")
    public ResponseEntity<List<CustomDto>> getAgeLessThan(@RequestParam(name = "ageLessThan") Integer age) {
        List<CustomDto> res = this.authorService.findAuthorsWithAgeLessThan(age);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @PutMapping(path = "/authors/{id}")
    public ResponseEntity<AuthorDto> updateAuthor(@PathVariable Long id, @Valid @RequestBody AuthorDto authorDto) {
        AuthorDto res = this.authorService.updateAuthor(id, authorDto);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @DeleteMapping(path = "/authors/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        this.authorService.deleteAuthor(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
