package org.echocat.kata.java.part1.service;

import java.util.List;
import java.util.Optional;

import org.echocat.kata.java.part1.model.Publication;

public interface PublicationService {

	List<Publication> getAllPublications();

	List<Publication> getPublicationsByEmail(String authorEmail);

	Optional<Publication> getPublicationByIsbn(String isbn);
}
