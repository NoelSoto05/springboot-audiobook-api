package com.example.audiobooks.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.audiobooks.model.Audiobook;

public interface AudiobookRepository extends JpaRepository<Audiobook, Long> {

    // List for Author search
    List<Audiobook> findByAuthorContainingIgnoreCase(String author);

    // Pagination for big queries
    public Page<Audiobook> findAll(Pageable pageable);

}
