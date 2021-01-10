package org.echocat.kata.java.part1.configuration;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

import org.echocat.kata.java.part1.Exception.DataLoaderException;
import org.springframework.core.io.ClassPathResource;

import com.coreoz.windmill.files.FileSource;
import com.coreoz.windmill.imports.Parsers;
import com.coreoz.windmill.imports.Row;
import com.coreoz.windmill.imports.parsers.csv.CsvParserConfig;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CsvDataLoader {

	public static Stream<Row> getCsvRows(String filePath, char columnSeparator) throws DataLoaderException {
		if (filePath == null || filePath.isEmpty()) {
			log.warn("File path is null or empty");
			throw new DataLoaderException("Invalid file path");
		}

		Stream<Row> rowStream = null;
		try {
			rowStream = Parsers
					.csv(CsvParserConfig.builder().charset(StandardCharsets.UTF_8).separator(columnSeparator).build())
					.parse(FileSource.of(new ClassPathResource(filePath).getInputStream()));
		} catch (IOException e) {
			log.error("Failed to load {}", filePath, e);
			// TODO: Only filename not path
			throw new DataLoaderException("Failed to load " + filePath);
		}

		return rowStream;
	}
}
