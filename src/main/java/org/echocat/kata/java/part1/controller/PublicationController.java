package org.echocat.kata.java.part1.controller;

import java.util.List;

import org.echocat.kata.java.part1.configuration.ApiRoutes;
import org.echocat.kata.java.part1.configuration.Publications;
import org.echocat.kata.java.part1.model.Publication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiRoutes.PUBLICATION_URL)
public class PublicationController {
	Publications publications;

	public PublicationController(Publications publications) {
		this.publications = publications;
	}

	@GetMapping("/")
	ResponseEntity<List<Publication>> getAllPublications() {
		return new ResponseEntity<>(publications.getPublications(), HttpStatus.OK);
	}
}
