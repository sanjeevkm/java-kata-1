package org.echocat.kata.java.part1.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.echocat.kata.java.part1.configuration.Publications;
import org.echocat.kata.java.part1.model.Publication;
import org.springframework.stereotype.Service;

@Service
public class PublicationServiceImpl implements PublicationService {
	private final Publications publications;

	PublicationServiceImpl(Publications publications) {
		this.publications = publications;
	}

	@Override
	public List<Publication> getAllPublications() {
		return publications.getPublications();
	}

	@Override
	public List<Publication> getPublicationsByEmail(String authorEmail) {
		List<Publication> allPublicationList = publications.getPublications();
		List<Publication> matchedPublicationList = new LinkedList<>();

		allPublicationList.stream().forEach(x -> x.getAuthors().stream().forEach(y -> {
			if (authorEmail.equals(y.getEmail())) {
				matchedPublicationList.add(x);
			}
		}));

		return matchedPublicationList;
	}

	@Override
	public Optional<Publication> getPublicationByIsbn(String isbn) {
		List<Publication> publicationsList = publications.getPublications();

		Optional<Publication> publicationOptional = publicationsList.stream().filter(x -> isbn.equals(x.getIsbn()))
				.findFirst();

		return publicationOptional;
	}
}
