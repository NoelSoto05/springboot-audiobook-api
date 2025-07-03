package com.example.audiobooks.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.audiobooks.model.Audiobook;
import com.example.audiobooks.repository.AudiobookRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/audiobooks")
public class AudiobookController {

    private AudiobookRepository audiobookRepository;

    public AudiobookController(AudiobookRepository audiobookRepository) {
        this.audiobookRepository = audiobookRepository;
    }

    @GetMapping
    public String getAllAudiobooks() {
        return "List of all audiobooks: " + audiobookRepository.findAll();
    }

    @GetMapping("{id}")
    public String getAudiobookById(@PathVariable String id) {
        return "Audio book with ID: " + id + " retrieved successfully: "
                + audiobookRepository.findById(Long.parseLong(id)).orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Audiobook not found"));
    }

    @PostMapping
    public String postNewAudiobook(@RequestBody Audiobook audiobook) {

        if (audiobook == null || audiobook.getTitle() == null || audiobook.getAuthor() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid audiobook data");
        }
        // Assuming the entity is a valid audiobook object
        audiobookRepository.save(audiobook);

        return audiobook.toString();
    }

    @DeleteMapping("/{id}")
    public void deleteAudiobook(@PathVariable Long id) {
        if (!audiobookRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "AudioBook Does Not Exist");
        }
        audiobookRepository.deleteById(id);
    }

}
