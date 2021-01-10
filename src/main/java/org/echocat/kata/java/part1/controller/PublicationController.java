package org.echocat.kata.java.part1.controller;

import java.util.List;
import java.util.Optional;

import org.echocat.kata.java.part1.configuration.ApiRoutes;
import org.echocat.kata.java.part1.model.Publication;
import org.echocat.kata.java.part1.service.PublicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiRoutes.API_PATH+ApiRoutes.PUBLICATIONS_BASE_PATH)
public class PublicationController {
	private final PublicationService publicationService;

	public PublicationController(PublicationService publicationService) {
		this.publicationService = publicationService;
	}

	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<List<Publication>> getAllPublications() {
		return new ResponseEntity<>(publicationService.getAllPublications(), HttpStatus.OK);
	}

	@GetMapping(value = ApiRoutes.PUBLICATIONS_EMAIL_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<List<Publication>> getPublicationsByEmail(@PathVariable("email") String authorEmail) {
		List<Publication> publicationList = publicationService.getPublicationsByEmail(authorEmail);

		if (publicationList.isEmpty()) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(publicationList, HttpStatus.OK);
	}

	@GetMapping(value = ApiRoutes.PUBLICATIONS_ISBN_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Publication> getPublicationByIsbn(@PathVariable("isbn") String isbn) {
		Optional<Publication> publicationOptional = publicationService.getPublicationByIsbn(isbn);

		if (publicationOptional.isEmpty()) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(publicationOptional.get(), HttpStatus.OK);
	}

}
