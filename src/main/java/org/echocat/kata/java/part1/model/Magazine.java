package org.echocat.kata.java.part1.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Magazine extends Publication {
	/// TODO: change to LocalDate
	private String publishedAt;

	public Magazine(String title, String isbn, List<Author> authors, String publishedAt) {
		super(title, isbn, authors);

		this.publishedAt = publishedAt;
	}
}
