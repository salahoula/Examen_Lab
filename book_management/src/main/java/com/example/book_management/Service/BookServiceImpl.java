package com.example.book_management.Service;

import com.example.book_management.Model.Book;
import com.example.book_management.Repository.BookRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    @Cacheable(value = "books")  // Cache la liste complète des livres
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    @Cacheable(value = "book", key = "#isbn")  // Cache un livre par son ISBN
    public Optional<Book> getBookByIsbn(String isbn) {
        return bookRepository.findById(isbn);
    }

    @Override
    @CachePut(value = "book", key = "#book.isbn")  // Met à jour le cache pour ce livre
    @CacheEvict(value = "books", allEntries = true) // Invalide le cache de la liste complète
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    @CachePut(value = "book", key = "#isbn")  // Met à jour le cache pour ce livre
    @CacheEvict(value = "books", allEntries = true) // Invalide le cache de la liste complète
    public Book updateBook(String isbn, Book book) {
        book.setIsbn(isbn);
        return bookRepository.save(book);
    }

    @Override
    @CacheEvict(value = {"book", "books"}, allEntries = true) // Invalide tous les caches liés aux livres
    public void deleteBook(String isbn) {
        bookRepository.deleteById(isbn);
    }

    @Override
    public boolean existsByIsbn(String isbn) {
        return bookRepository.existsById(isbn);
    }
}
