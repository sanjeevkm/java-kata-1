package org.echocat.kata.java.part1.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Publication {
	private String title;
	private String isbn;
	private List<Author> authors;

	public Publication(String title, String isbn, List<Author> authors) {
		super();
		this.title = title;
		this.isbn = isbn;
		this.authors = authors;
	}
}
