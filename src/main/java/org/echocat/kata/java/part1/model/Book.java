package org.echocat.kata.java.part1.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Book extends Publication {
	private String description;

	public Book(String title, String isbn, List<Author> authors, String description) {
		super(title, isbn, authors);

		this.description = description;
	}
}
