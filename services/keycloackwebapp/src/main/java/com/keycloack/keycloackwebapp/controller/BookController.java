package com.keycloack.keycloackwebapp.controller;

import com.keycloack.keycloackwebapp.module.Book;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")

public class BookController {

    @GetMapping("books")
    public ResponseEntity<List<Book>> getBooks(){

        List<Book> books= new ArrayList<>();
        books.add(new Book(UUID.randomUUID().toString(), "Le Petit Prince", "Antoine de Saint-Exupéry", new Date(1943 - 1900, 4 - 1, 6))); // Date de publication : 6 avril 1943
        books.add(new Book(UUID.randomUUID().toString(), "1984", "George Orwell", new Date(1949 - 1900, 6 - 1, 8))); // Date de publication : 8 juin 1949
        books.add(new Book(UUID.randomUUID().toString(), "To Kill a Mockingbird", "Harper Lee", new Date(1960 - 1900, 7 - 1, 11)));

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(books);

    }

    @PostMapping("prot")
    //@PreAuthorize(("hasRole('ADMIN')"))
    public ResponseEntity<String> resourceProteger(){
        return ResponseEntity.ok("resource proteger");
    }

    @GetMapping("auth")
    public Authentication getAuthentication(
            Authentication authentication
    ){
        return authentication;
    }

}
