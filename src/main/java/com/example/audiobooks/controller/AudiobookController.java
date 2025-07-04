package com.example.audiobooks.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.audiobooks.model.Audiobook;
import com.example.audiobooks.repository.AudiobookRepository;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/audiobooks")
public class AudiobookController {

    private AudiobookRepository audiobookRepository;

    public AudiobookController(AudiobookRepository audiobookRepository) {
        this.audiobookRepository = audiobookRepository;
    }

    @GetMapping
    public List<Audiobook> getAllAudiobooks() {
        return audiobookRepository.findAll();
    }

    @GetMapping("/{id}")
    public Audiobook getAudiobookById(@PathVariable Long id) {
        return audiobookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Audiobook not found"));
    }

    @PostMapping
    public Audiobook postNewAudiobook(@RequestBody Audiobook audiobook) {

        if (audiobook == null || audiobook.getTitle() == null || audiobook.getAuthor() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid audiobook data");
        }
        // Assuming the entity is a valid audiobook object
        audiobookRepository.save(audiobook);

        return audiobook;
    }

    @DeleteMapping("/{id}")
    public void deleteAudiobook(@PathVariable Long id) {
        if (!audiobookRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "AudioBook Does Not Exist");
        }
        audiobookRepository.deleteById(id);

    }

    @GetMapping("/search")
    public List<Audiobook> getMethodName(@RequestParam String author) {

        if (author == null || author.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Search for Audiobook");
        }

        List<Audiobook> audiobook = audiobookRepository.findByAuthorContainingIgnoreCase(author);
        // If list is empty still return a list of books
        if (audiobook.isEmpty()) {

            return audiobookRepository.findAll();
        }

        return audiobook;
    }

    @GetMapping("/page")
    public Page<Audiobook> getAudiobookPages(@PageableDefault(size = 10) Pageable page) {

        return audiobookRepository.findAll(page);

    }

    // Later, you could centralize error handling with @ControllerAdvice for cleaner
    // code
}
