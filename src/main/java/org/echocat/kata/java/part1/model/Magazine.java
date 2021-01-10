package org.echocat.kata.java.part1.model;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Magazine extends Publication {
	private LocalDate publishedAt;

	public Magazine(String title, String isbn, List<Author> authors, LocalDate publishedAt) {
		super(title, isbn, authors);

		this.publishedAt = publishedAt;
	}
}
