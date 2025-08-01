package com.example.audiobooks.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import com.example.audiobooks.model.Audiobook;

public interface AudiobookRepository extends JpaRepository<Audiobook, Long> {

    // List for Author search
    List<Audiobook> findByAuthorContainingIgnoreCase(String author);

    // Pagination for big queries
    @NonNull
    @Override
    public Page<Audiobook> findAll(@NonNull Pageable pageable);

}
