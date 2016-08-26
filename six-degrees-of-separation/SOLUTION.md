## Approach

- `Map<String, Set<String>> ACTORS_TO_MOVIES`: List of movies in which the given actor acted
- `Map<String, Set<String>> MOVIES_TO_ACTORS`: List of actors who appeared in the given movie

Creating the series of links from one actor to another involves starting with the first actor ("source") and searching through every movie that this actor has acted in. Then for every movie of the "source", it needs to be checked if the second actor ("destination") has also acted in this movie. 

If not, then it needs to be searched through all movies from every actor of that movie. Thus a recursive search with a maximum of six levels needs to be started which terminates if the "destination" is found or the maximum of 6 levels is reached. 

This kind of search warrants a tree like data structure.
