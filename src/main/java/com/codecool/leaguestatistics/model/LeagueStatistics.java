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
        // 1. Get stream of teams
        return teams.stream()
                // 2. Use max method with comparator -> return Optional
                .max(Comparator.comparingInt(team -> team.getName().length()))
                // 3. Return object or throw an exception
                .orElseThrow(RuntimeException::new);
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
        // 1. Get teams stream
        return teams.stream()
                // 2. Map each team to best player
                .map(Team::getBestPlayer)
                // 3. Convert stream to a List
                .collect(Collectors.toList());
    }

    /**
     * Gets all teams, where there are players with no scored goals.
     */
    public static List<Team> getTeamsWithPlayersWithoutGoals(List<Team> teams) {
        // 1. Get teams stream
        return teams.stream()
                // 2. Filter teams where are players with no goals
                .filter(
                        // 2a. Stream each team
                        team -> team.getPlayers().stream()
                                // 2b. Find any player without goal
                                .anyMatch(player -> player.getGoals() == 0)
                )
                // 3. Convert stream into a List
                .collect(Collectors.toList());
    }

    /**
     * Gets players with given or higher number of goals scored.
     *
     * @param goals The minimal number of goals scored.
     * @return Collection of Players with given or higher number of goals scored.
     */
    public static List<Player> getPlayersWithAtLeastXGoals(List<Team> teams, int goals) {
        // 1 get teams stream
        return teams.stream()
                //2 get players stream
                .flatMap(team -> team.getPlayers().stream())
                //3 filter players witsh goals >= goals
                .filter(player -> player.getGoals() >= goals)
                //4 strem to list
                .collect(Collectors.toList());


    }

    /**
     * Gets the player with the highest skill rate for given Division.
     */
    public static Player getMostTalentedPlayerInDivision(List<Team> teams, Division division) {
        //1 get teams stream
        return teams.stream()
                //2 filter only teams in given division
                .filter(team -> team.getDivision().equals(division))
                //3 get players stream
                .flatMap(team -> team.getPlayers().stream())
                //4 use max method with getSkillRate
                .max(Comparator.comparing(Player::getSkillRate))
                //5 use or elseThrow
                .orElseThrow(RuntimeException::new);
    }

    /**
     * OPTIONAL
     * Returns the division with greatest amount of points.
     * If there is more than one division with the same amount current points, then check the amounts of wins.
     */
    public static Division getStrongestDivision(List<Team> teams) {
        return teams.stream()
                .collect(Collectors.groupingBy(Team::getDivision, Collectors.toList()))
                .entrySet().stream()
                .map(entry ->
                        new DivisionData(entry.getKey(), entry.getValue()))
                .sorted(Comparator.comparingInt(DivisionData::getTotalPoints).reversed())
                .max(Comparator.comparingInt(DivisionData::getTotalWins))
                .orElseThrow(RuntimeException::new).getDiv();
    }

    private static class DivisionData {
        private final Division div;
        private final List<Team> teams;

        private DivisionData(Division div, List<Team> teams) {
            this.div = div;
            this.teams = teams;
        }

        public int getTotalPoints() {
            return teams.stream().mapToInt(Team::getCurrentPoints).sum();
        }

        public int getTotalWins() {
            return teams.stream().mapToInt(Team::getWins).sum();
        }

        public Division getDiv() {
            return div;
        }
    }
}
