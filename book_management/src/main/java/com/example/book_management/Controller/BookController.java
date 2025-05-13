package com.example.book_management.Controller;

import com.example.book_management.Model.Book;
import com.example.book_management.Service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "*") // Optionnel, à adapter selon vos besoins
public class BookController {

    private final BookService service;

    private BookController(BookService service) {
        this.service = service;
    }

    public static BookController createBookController(BookService service) {
        return new BookController(service);
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book createdBook = service.createBook(book);
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = service.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<Book> getBook(@PathVariable String isbn) {
        return service.getBookByIsbn(isbn)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{isbn}")
    public ResponseEntity<Book> updateBook(@PathVariable String isbn, @RequestBody Book book) {
        if (!service.existsByIsbn(isbn)) {
            return ResponseEntity.notFound().build();
        }
        book.setIsbn(isbn);
        Book updatedBook = service.updateBook(isbn, book);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<Void> deleteBook(@PathVariable String isbn) {
        if (!service.existsByIsbn(isbn)) {
            return ResponseEntity.notFound().build();
        }
        service.deleteBook(isbn);
        return ResponseEntity.noContent().build();
    }
}
