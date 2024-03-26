package org.pronet.repositories;

import org.pronet.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query(value = "SELECT * FROM books WHERE name LIKE %:keyword%", nativeQuery = true)
    List<Book> searchBooks(String keyword);
}