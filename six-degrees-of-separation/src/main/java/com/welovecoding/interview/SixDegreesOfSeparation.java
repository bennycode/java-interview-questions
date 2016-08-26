package com.welovecoding.interview;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SixDegreesOfSeparation {

  public static Map<String, Set<String>> ACTORS_TO_MOVIES = new HashMap<>();
  public static Map<String, Set<String>> MOVIES_TO_ACTORS = new HashMap<>();
  private static int iterations = 0;

  public static Set<String> getMoviesForActor(String actorName) {
    return ACTORS_TO_MOVIES.get(actorName);
  }

  public static Set<String> getActorsInMovie(String movieName) {
    return MOVIES_TO_ACTORS.get(movieName);
  }

  public static int getDegreeOfSeparation(String actorName1, String actorName2) {
    if (iterations == 6) {
      iterations = 0;
      return -1;
    }

    Set<String> moviesWithActor1 = ACTORS_TO_MOVIES.get(actorName1);

    for (String movieWithActor1 : moviesWithActor1) {
      Set<String> coWorkersOfActor1 = MOVIES_TO_ACTORS.get(movieWithActor1);
      for (String coWorkerOfActor1 : coWorkersOfActor1) {
        if (coWorkerOfActor1.equals(actorName2)) {
          int degree = iterations + 1;
          iterations = 0;
          return degree;
        }
      }
    }

    iterations += 1;
    for (String movieWithActor1 : moviesWithActor1) {
      Set<String> coWorkersOfActor1 = MOVIES_TO_ACTORS.get(movieWithActor1);
      for (String coWorkerOfActor1 : coWorkersOfActor1) {
        if (!coWorkerOfActor1.equals(actorName1)) {
          return getDegreeOfSeparation(coWorkerOfActor1, actorName2);
        }
      }
    }

    return -1;
  }
}
