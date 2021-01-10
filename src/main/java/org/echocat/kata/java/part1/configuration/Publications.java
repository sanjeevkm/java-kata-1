package org.echocat.kata.java.part1.configuration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.echocat.kata.java.part1.model.Author;
import org.echocat.kata.java.part1.model.Book;
import org.echocat.kata.java.part1.model.Magazine;
import org.echocat.kata.java.part1.model.Publication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.coreoz.windmill.imports.Row;

@Component
public class Publications {
	@Value("${app.magazines.csv.path}")
	private String magazinesCsvFilePath;
	@Value("${app.books.csv.path}")
	private String booksCsvFilePath;
	@Value("${app.authors.csv.path}")
	private String authorsCsvFilePath;
	@Value("${app.csv.column-separator}")
	private char csvColumnSeparator;

	private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
	private List<Publication> publicationList = new LinkedList<>();

	@PostConstruct
	void loadPublications() {
		List<Author> authorList = null;
		Map<String, Author> emailAuthorMap = new HashMap<>();

		// Load Authors
		Stream<Row> authorRowStream = CsvDataLoader.getCsvRows(authorsCsvFilePath, csvColumnSeparator);

		if (authorRowStream != null) {
			authorList = authorRowStream
					// skip the header row that contains the column names
					.skip(1).map(x -> new Author(x.cell(0).asString(), x.cell(1).asString(), x.cell(2).asString()))
					.collect(Collectors.toList());

			authorRowStream.close();
		}

		// Put authors into map for fast lookups
		authorList.stream().forEach(x -> emailAuthorMap.put(x.getEmail(), x));

		// Load Books
		Stream<Row> bookRowStream = CsvDataLoader.getCsvRows(booksCsvFilePath, csvColumnSeparator);

		if (bookRowStream != null) {
			publicationList.addAll(bookRowStream
					// skip the header row that contains the column names
					.skip(1)
					.map(x -> new Book(x.cell(0).asString(), x.cell(1).asString(),
							getAuthors(x.cell(2).asString(), emailAuthorMap), x.cell(3).asString()))
					.collect(Collectors.toList()));

			bookRowStream.close();
		}

		// Load Magazines
		Stream<Row> magazineRowStream = CsvDataLoader.getCsvRows(magazinesCsvFilePath, csvColumnSeparator);

		if (magazineRowStream != null) {
			publicationList.addAll(magazineRowStream
					// skip the header row that contains the column names
					.skip(1)
					.map(x -> new Magazine(x.cell(0).asString(), x.cell(1).asString(),
							getAuthors(x.cell(2).asString(), emailAuthorMap), getDate(x.cell(3).asString())))
					.collect(Collectors.toList()));

			magazineRowStream.close();
		}
	}

	private LocalDate getDate(String date) {
		return LocalDate.parse(date, dateFormatter);
	}

	public List<Publication> getPublications() {
		return publicationList;
	}

	private List<Author> getAuthors(String emails, Map<String, Author> emailAuthorMap) {
		List<Author> authorList = new LinkedList<>();
		String[] authorEmails = emails.split(",");

		Arrays.stream(authorEmails).forEach(x -> authorList.add(emailAuthorMap.get(x)));

		return authorList;
	}
}
