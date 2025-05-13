package com.example.book_management.Service;

import com.example.book_management.Model.Book;
import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> getAllBooks();
    Optional<Book> getBookByIsbn(String isbn);
    Book createBook(Book book);
    Book updateBook(String isbn, Book book);
    void deleteBook(String isbn);
    boolean existsByIsbn(String isbn);
}
