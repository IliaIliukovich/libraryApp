package com.telran.libraryapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ThymeleafController {

    @GetMapping("/home")
    public String homepage() {
        return "index";
    }

    @GetMapping("/all")
    public String getAllBooks(Model model) {
        model.addAttribute("myBooks", Book.library);
        return "books";
    }

    @PostMapping("/all")
    public String addBook(@ModelAttribute("newBook") Book book) {
        Book.library.add(book);
        return "redirect:/all";
    }

    @ModelAttribute("newBook")
    public Book newBook() {
        return new Book();
    }

}
