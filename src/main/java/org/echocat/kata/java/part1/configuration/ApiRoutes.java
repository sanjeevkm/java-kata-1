package org.echocat.kata.java.part1.configuration;

public interface ApiRoutes {
	final String API_PATH = "/api";
	final String PUBLICATIONS_BASE_PATH = "/publications";
	final String PUBLICATIONS_ISBN_PATH =  "/isbn/{isbn}";
	final String PUBLICATIONS_EMAIL_PATH = "/author-email/{email}";
}
