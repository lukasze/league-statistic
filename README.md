# League Statistics

## Story

### Football League Statistics
A newspaper from your hometown needs someone to create an archive of statistics
from your local backyard soccer league. Since you are very close to the players and fans,
you decided to create a program that will provide these statistics
at every moment of the season and in all subsequent ones.

As the whole history database is being built in parallel by someone else
and you don't want to wait, you got the idea to create a simulation of the season
that will generate data that you don't have yet to test your program.

## What are you going to learn?

- Java 8 Stream API
- Method references
- usage of lambda expression
- practical usage of the Model-View-Controller design pattern

## Tasks

1. Implement the 'Season' class, where all matches are be played on a peer-to-peer basis using the 'League' team collection.
    - The `playMatch()` method provides result of a match played with two given teams. It increments teams' `Wins`, `Losts` or `Draws` value.
    - The `scoredGoals()` method returns the number of goals scored by given team in one match. Method contains logic of each player's scoring chance. It increments player's `Goals` stats.
    - The `playAllGames()` method contains logic of all matches played in one round. Every team has to play game against every team exactly once.

2. Implement the methods in League Statistics class with use of Streams.
    - All methods in League Statistics class are public static methods.
    - No loops (while, do-while, for, foreach) are used in League Statistics class.
    - All methods in League Statistics class are single-line return methods - and consist of streams, lambda expressions, method references and have no braces.

3. Implement the `Display` class with all necessary methods for creating console view for the league statistics.
    - Implement methods displaying:
- whole table with columns of name, points, goals, wins, draws, loses,
- single match results.

4. [OPTIONAL] Player become an abstract class. Create subclasses of Player and new attributes of modifiers according to your idea in order to get match simulation more realistic.
    - Implement subclasses of `Player` such as `Attacker`, `Midfielder`, `Defender`, `Goalkeeper` and create new skill rates accordingly.
    - Use more complex logic for match result e.g.: sum of Attackers and Midfielders chances against Defenders and Goalkeepers. Instead of checking score chance for each player, divide game for attacking and defending rounds, aggregate skill rates of various players depending on role keeping in mind individual scoring chances etc. The more realistic simulation - the more fun you get by implementing it.

5. [OPTIONAL] Create a new class responsible for keeping all necessary data for single game result. Use it as a parameter deciding of league position instead of goals numbers. Save the results with all season statistics in a .txt file
    - Result class keeps information regarding season, teams in the game, goals scored and scorers.
    - Create display methods for all statistics requested in `LeagueStatistics` class.
    - Implement a method to archive the results and all season statistics in a .txt file

6. [OPTIONAL] Instead of simple implementation of `playAllGames()` implement logic to schedule turns. Each turn all teams are set up in pairs in order to play a match. Every time the set of pairs is different until the season is fully played.
    - Overload the `playAllGames()` method or create new method responsible to play all seasons games using the robin-round algorithm.

## General requirements

None

## Hints

- Start by implementing the `Season` class where the league is simulated.
  `League Statistics` will process its properties.
- Manage all randomization through the pre-created static `getRandomValue()` method in `Utils`.
- In `LeagueStatistics` class start implementation with `getAllTeamsSorted()` method to display full table after a played season.
- The `getTeamWithTheLongestName()` method form `LeagueStatistics` can be used in `Display` class to set the table borders width.


## Background materials

- [Model View Controller](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller)
- [Java Streams Introduction](https://www.baeldung.com/java-8-streams-introduction)
- [Java 8 Stream API documentation](https://docs.oracle.com/javase/8/docs/api/java/util/stream/package-summary.html)
- [Streams with examples](https://stackify.com/streams-guide-java-8/)
- [Lambda Expressions](https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html)
- [Method References](https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html)
- [Random Vs ThreadLocalRandom](https://stackoverflow.com/questions/23396033/random-over-threadlocalrandom)

