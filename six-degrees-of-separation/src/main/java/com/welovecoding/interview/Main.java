package com.welovecoding.interview;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

  public static String testSet[] = {
    "Angelina Jolie→Brad Pitt",
    "Angelina Jolie→Angelina Jolie",
    "Angelina Jolie→Jodie Foster",
    "Kristen Stewart→Angelina Jolie",
    "Lysette Anthony→Liam Neeson",
    "Lysette Anthony→Angelina Jolie",
    "Brad Pitt→Kristen Stewart",
    "Matt Damon→Jodie Foster"
  };

  public static void main(String[] args) throws Exception {
    buildFilmography("filmography.txt");
    System.out.println("Actors in Filmography: " + SixDegreesOfSeparation.ACTORS_TO_MOVIES.size());
    runTestSet();
  }

  public static void buildFilmography(String fileName) throws Exception {
    File file = new File(fileName);

    FileReader fileReader = new FileReader(file);
    BufferedReader reader = new BufferedReader(fileReader);

    String line;
    while ((line = reader.readLine()) != null) {
      line = line.trim();

      if (line.isEmpty()) {
        break;
      }

      final List<String> splitActorMovies = Arrays.asList(line.split(":"));

      if (splitActorMovies.size() != 2) {
        String error = "Mapping input line must have one actor followed by \":\" and at least one movie";
        throw new IllegalArgumentException(error);
      }

      final String actorName = splitActorMovies.get(0).trim();
      final String[] parsedMovies = splitActorMovies.get(1).split(",");
      final Set<String> inputMovies = new HashSet<>();

      for (String s : parsedMovies) {
        inputMovies.add(s.trim());
      }

      SixDegreesOfSeparation.ACTORS_TO_MOVIES.put(actorName, inputMovies);
      for (String movie : inputMovies) {
        movie = movie.trim();
        Set<String> actors = SixDegreesOfSeparation.MOVIES_TO_ACTORS.get(movie);
        if (actors == null) {
          actors = new HashSet<>();
          SixDegreesOfSeparation.MOVIES_TO_ACTORS.put(movie, actors);
        }
        actors.add(actorName);
      }
    }
  }

  public static void runTestSet() {
    for (int i = 0; i < testSet.length; i++) {
      String testCase = testSet[i];
      String[] combination = testCase.split("→");

      String actorName1 = combination[0];
      String actorName2 = combination[1];

      printDegreeOfSeparation(actorName1, actorName2);
    }
  }

  public static void printDegreeOfSeparation(String actorName1, String actorName2) {
    int degreeOfSeparation = SixDegreesOfSeparation.getDegreeOfSeparation(actorName1, actorName2);
    String sentence = String.format("'%s' knows '%s' along '%d' node(s).", actorName1, actorName2, degreeOfSeparation);
    System.out.println(sentence);
  }
}
