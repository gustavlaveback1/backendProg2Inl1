package com.example.bookclub.controller;

import com.example.bookclub.domain.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BookController {

    @GetMapping("/home")
    public String home(Model model) {

        model.addAttribute("greeting", "Välkommen till Bokklubben!");
        model.addAttribute("date", LocalDate.now());

        return "home";
    }

    @GetMapping("/books")
    public String books(Model model) {

        List<Book> books = new ArrayList<>();

        books.add(new Book("Sagan om ringen", "J.R.R. Tolkien", "Fantasy"));
        books.add(new Book("Män som hatar kvinnor", "Stieg Larsson", "Deckare"));

        model.addAttribute("books", books);

        return "booklist";
    }

    @GetMapping("/genre")
    public String genre(
            @RequestParam(name = "type", required = false) String type,
            Model model) {

        List<Book> books = new ArrayList<>();

        books.add(new Book("Sagan om ringen", "J.R.R. Tolkien", "Fantasy"));
        books.add(new Book("Män som hatar kvinnor", "Stieg Larsson", "Deckare"));

        List<Book> filteredBooks = new ArrayList<>();

        if (type == null || type.isBlank()) {
            filteredBooks = books;
        } else {
            for (Book book : books) {
                if (book.getGenre().equalsIgnoreCase(type)) {
                    filteredBooks.add(book);
                }
            }
        }

        model.addAttribute("books", filteredBooks);

        return "genre";
    }
}