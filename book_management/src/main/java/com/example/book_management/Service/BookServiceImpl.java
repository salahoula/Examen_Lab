package com.example.book_management.Service;

import com.example.book_management.Model.Book;
import com.example.book_management.Repository.BookRepository;
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
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> getBookByIsbn(String isbn) {
        return bookRepository.findById(isbn);
    }

    @Override
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(String isbn, Book book) {
        book.setIsbn(isbn);
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(String isbn) {
        bookRepository.deleteById(isbn);
    }

    @Override
    public boolean existsByIsbn(String isbn) {
        return bookRepository.existsById(isbn);
    }
}
