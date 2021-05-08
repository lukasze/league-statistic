package com.codecool.leaguestatistics.model;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Provides all necessary statistics of played season.
 */
public class LeagueStatistics {

    /**
     * Gets all teams with highest points order, if points are equal next deciding parameter is sum of goals of the team.
     */
    public static List<Team> getAllTeamsSorted(List<Team> teams) {
        //1. Create stream
        return teams.stream()
                //2. Sort
                .sorted(
                        //2a. Sorting by points
                        Comparator.comparingInt(Team::getCurrentPoints) //(team -> team.getCurrentPoints())
                                //2b. Sorting by goals
                                .thenComparing(
                                        // Get Players from team and create a stream
                                        team -> team.getPlayers().stream()
                                                // Map to stream of int
                                                .mapToInt(
                                                        Player::getGoals
                                                )
                                                //Sum players goals
                                                .sum()
                                )
                                // reverse
                                .reversed()
                )
                //2. Transform stream to list
                .collect(Collectors.toList());
    }

    /**
     * Gets all players from each team in one collection.
     */
    public static List<Player> getAllPlayers(List<Team> teams) {
        // 1. Get stream of teams
        return teams.stream()
                //2. Use flatMap method
                .flatMap(team -> team.getPlayers().stream())
                //3. Stream to list
                .collect(Collectors.toList());
    }

    /**
     * Gets team with the longest name
     */
    public static Team getTeamWithTheLongestName(List<Team> teams) {
        throw new RuntimeException("getTeamWithTheLongestName method not implemented");
    }

    /**
     * Gets top teams with least number of lost matches.
     * If the amount of lost matches is equal, next deciding parameter is team's current points value.
     *
     * @param teamsNumber The number of Teams to select.
     * @return Collection of selected Teams.
     */
    public static List<Team> getTopTeamsWithLeastLoses(List<Team> teams, int teamsNumber) {
        // 1. Get teams stream
        return teams.stream()
                //2. sort
                .sorted(
                // 2a sort by looses
                        Comparator.comparingInt(Team::getLoses)
                                .reversed()// team.getLoses()
                // 2b sort by points
                                .thenComparing(Team::getCurrentPoints)
                                .reversed()
                )
                //3. limit to teamsNumber
                .limit(teamsNumber)
                //4. stream of teams to list of teams
                .collect(Collectors.toList());
    }

    /**
     * Gets a player with the biggest goals number from each team.
     */
    public static List<Player> getTopPlayersFromEachTeam(List<Team> teams) {
        throw new RuntimeException("getTopPlayersFromEachTeam method not implemented");
    }

    /**
     * Gets all teams, where there are players with no scored goals.
     */
    public static List<Team> getTeamsWithPlayersWithoutGoals(List<Team> teams) {
        throw new RuntimeException("getTeamsWithPlayersWithoutGoals method not implemented");
    }

    /**
     * Gets players with given or higher number of goals scored.
     *
     * @param goals The minimal number of goals scored.
     * @return Collection of Players with given or higher number of goals scored.
     */
    public static List<Player> getPlayersWithAtLeastXGoals(List<Team> teams, int goals) {
        throw new RuntimeException("getPlayersWithAtLeastXGoals method not implemented");
    }

    /**
     * Gets the player with the highest skill rate for given Division.
     */
    public static Player getMostTalentedPlayerInDivision(List<Team> teams, Division division) {
        throw new RuntimeException("getMostTalentedPlayerInDivision method not implemented");
    }

    /**
     * OPTIONAL
     * Returns the division with greatest amount of points.
     * If there is more than one division with the same amount current points, then check the amounts of wins.
     */
    public static Division getStrongestDivision(List<Team> teams) {
        throw new RuntimeException("getStrongestDivision method not implemented");
    }
}
