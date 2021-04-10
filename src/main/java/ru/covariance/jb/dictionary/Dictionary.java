package ru.covariance.jb.dictionary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public final class Dictionary {

  private static final String DICTIONARY_FOLDER = "/dictionary/";

  private final List<String> observer;

  private List<String> loaded;
  private String lastQuery = "";

  public Dictionary(final List<String> observer) {
    this.observer = observer;
  }

  public void query(final String query) {
    if (!isValidQuery(query)) {
      observer.clear();
      return;
    }
    innerQuery(query.toLowerCase());
    lastQuery = query;
  }

  private boolean isValidQuery(final String query) {
    return query.toLowerCase().chars().allMatch(c -> 97 <= c && c <= 122);
  }

  private void innerQuery(final String query) {
    if (query.isEmpty()) {
      observer.clear();
      return;
    }
    if (lastQuery.isEmpty() || lastQuery.charAt(0) != query.charAt(0)) {
      load(query);
    }
    specifyQuery(query);
  }

  private void load(final String query) {

    InputStream inputStream = getClass().getResourceAsStream(DICTIONARY_FOLDER + query.charAt(0));
    if (inputStream == null) {
      throw new IllegalArgumentException(
          "Internal error: dictionary for query " + query + " not found");
    }

    try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
      loaded = reader.lines().collect(Collectors.toList());
    } catch (IOException e) {
      throw new IllegalArgumentException(
          "Internal error: error while loading dictionary for query " + query);
    }
  }

  private void specifyQuery(final String query) {
    int start = Collections.binarySearch(loaded, query, Comparator.naturalOrder());
    if (start < 0) {
      start = -(start + 1);
    }
    observer.clear();
    for (; start != loaded.size() && loaded.get(start).startsWith(query); ++start) {
      observer.add(loaded.get(start));
    }
  }
}
