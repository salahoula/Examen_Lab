package com.example.book_management.Repository;

import com.example.book_management.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepository extends JpaRepository<Book, String> {
}
