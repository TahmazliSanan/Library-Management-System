package org.pronet.repositories;

import org.pronet.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query(value = "SELECT * FROM authors WHERE full_name LIKE %:keyword%", nativeQuery = true)
    List<Author> searchAuthors(String keyword);
}