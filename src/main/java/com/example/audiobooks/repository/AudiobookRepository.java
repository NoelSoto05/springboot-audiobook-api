package com.example.audiobooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.audiobooks.model.Audiobook;

public interface AudiobookRepository extends JpaRepository<Audiobook, Long> {

}
