package com.example.audiobooks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.audiobooks.model.Audiobook;

@SpringBootApplication
public class AudiobooksApplication {

	public static void main(String[] args) {
		SpringApplication.run(AudiobooksApplication.class, args);

		Audiobook book = new Audiobook("Mystery", "Bobby", "Haunted mansion", "2015");
		System.out.println(book);
	}

}
