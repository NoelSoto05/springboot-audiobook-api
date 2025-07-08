package com.example.audiobooks.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.audiobooks.dto.AudiobookResponse;
import com.example.audiobooks.model.Audiobook;
import com.example.audiobooks.repository.AudiobookRepository;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/audiobooks")
public class AudiobookController {

    private AudiobookRepository audiobookRepository;
    // private AudiobookResponse response;

    public AudiobookController(AudiobookRepository audiobookRepository) {
        this.audiobookRepository = audiobookRepository;
    }

    @GetMapping
    public List<AudiobookResponse> getAllAudiobooks() {

        List<Audiobook> rawDataFromDB = audiobookRepository.findAll();
        List<AudiobookResponse> response = new ArrayList<>();

        for (Audiobook book : rawDataFromDB) {
            response.add(new AudiobookResponse(book));
        }

        return response;
    }

    @GetMapping("/{id}")
    public AudiobookResponse getAudiobookById(@PathVariable Long id) {

        Audiobook audiobook = audiobookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Audiobook not found"));

        return new AudiobookResponse(audiobook);
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
    public List<AudiobookResponse> getSearchList(@RequestParam String author) {

        List<Audiobook> audiobooks = audiobookRepository.findByAuthorContainingIgnoreCase(author);

        return audiobooks.stream().map(AudiobookResponse::new).toList();

    }

    @GetMapping("/page")
    public Page<AudiobookResponse> getAudiobookPages(@PageableDefault(size = 10) Pageable page) {

        Page<Audiobook> books = audiobookRepository.findAll(page);

        return books.map(AudiobookResponse::new);

    }

    // Later, you could centralize error handling with @ControllerAdvice for cleaner
    // code
}
