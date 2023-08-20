package com.github.jomardev25;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.github.jomardev25.service.FileStorageService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@SpringBootApplication
public class GleamPostApplication implements CommandLineRunner {

	private final FileStorageService fileStorageService;

	public static void main(String[] args) {
		SpringApplication.run(GleamPostApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		fileStorageService.init();
	}

}
