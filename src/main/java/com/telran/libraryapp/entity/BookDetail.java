package com.telran.libraryapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class BookDetail {

    private Integer id;
    private String publisher;
    private Integer year;
    private String abstractToBook;

    public static List<BookDetail> bookDetailsList = new ArrayList<>(Arrays.asList(
            new BookDetail(1, "Manning Publications", 2018, "Comprehensive guide to modern Java programming"),
            new BookDetail(2, "Addison-Wesley", 2011, "A comprehensive introduction to algorithms"),
            new BookDetail(3, "Addison-Wesley", 1994, "A classic guide to software design patterns"),
            new BookDetail(4, "George Newnes", 1892, "Detective stories featuring Sherlock Holmes"),
            new BookDetail(5, "Bloomsbury", 1997, "The first book in the Harry Potter series"),
            new BookDetail(6, "Signet Classic", 1949, "A dystopian novel about a totalitarian regime"),
            new BookDetail(7, "J.B. Lippincott & Co.", 1960, "A novel about racial injustice in the Deep South"),
            new BookDetail(8, "T. Egerton, Whitehall", 1813, "A romantic novel about the manners of British gentry"),
            new BookDetail(9, "Charles Scribner's Sons", 1925, "A novel about the American dream in the 1920s"),
            new BookDetail(10, "Harper & Brothers", 1851, "A story of the obsessive quest for a giant whale"),
            new BookDetail(11, "The Russian Messenger", 1869, "A historical novel about the Napoleonic wars"),
            new BookDetail(12, "Little, Brown and Company", 1951, "A novel about teenage angst and alienation"),
            new BookDetail(13, "Penguin Classics", 800, "An epic poem about the adventures of Odysseus"),
            new BookDetail(14, "Penguin Classics", 1320, "An epic poem about the afterlife"),
            new BookDetail(15, "Smith, Elder & Co.", 1847, "A novel about the experiences of the eponymous heroine"),
            new BookDetail(16, "Thomas Cautley Newby", 1847, "A novel about the intense relationship between two families"),
            new BookDetail(17, "Chatto & Windus", 1932, "A dystopian novel about a future World State"),
            new BookDetail(18, "Revue de Paris", 1856, "A novel about the life of a doctor's wife in provincial France"),
            new BookDetail(19, "Harper & Row", 1967, "A novel about the Buendía family in the town of Macondo"),
            new BookDetail(20, "Grasset", 1913, "A modernist novel about involuntary memory"),
            new BookDetail(21, "Sylvia Beach", 1922, "A modernist novel about the experiences of Leopold Bloom"),
            new BookDetail(22, "Francisco de Robles", 1605, "A novel about the adventures of an aging knight"),
            new BookDetail(23, "Penguin Classics", 762, "An epic poem about the Trojan War"),
            new BookDetail(24, "George Allen & Unwin", 1937, "A fantasy novel about the journey of Bilbo Baggins"),
            new BookDetail(25, "Ballantine Books", 1953, "A dystopian novel about a future American society where books are banned")));


}





