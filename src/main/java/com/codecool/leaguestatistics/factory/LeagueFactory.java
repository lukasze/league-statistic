package com.codecool.leaguestatistics.factory;

import com.codecool.leaguestatistics.Utils;
import com.codecool.leaguestatistics.model.Division;
import com.codecool.leaguestatistics.model.Player;
import com.codecool.leaguestatistics.model.Team;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Provides full set of teams with players
 */
public class LeagueFactory {

    /**
     * For each division, creates given amount of teams. Each team gets a newly created collection of players.
     * The amount of players should be taken from Utils.TEAM_SIZE
     *
     * @param teamsInDivision Indicates number of teams are in division
     * @return Full set of teams with players
     */
    public static List<Team> createLeague(int teamsInDivision) {
        // 1. Create List of teams
        List<Team> teams = new LinkedList<>();
        // 2. Iterate through Division enum values
        Arrays.stream(Division.values())
                .forEach(
                        // 2a. Create given amount of a team for a division
                        division -> teams.addAll(IntStream.range(0, teamsInDivision)
                                // 2b. Give players to a team
                                .mapToObj(i -> new Team(division, getPlayers(Utils.TEAM_SIZE)))
                                // 3. Transform stream into a List
                                .collect(Collectors.toList())
                        )
                );
        return teams;

    }

    /**
     * Returns a collection with a given amount of newly created players
     */
    private static List<Player> getPlayers(int amount) {
        // 1. Create IntStream
        return IntStream.range(0, amount)
                // 2. Map IntStream to an objects stream
                .mapToObj(i -> new Player(getPlayerSkillRate()))
                // 3. Transform PlayerStream into a PlayerList
                .collect(Collectors.toList());
    }

    private static int getPlayerSkillRate() {
        return Utils.getRandomValue(5, 21);
    }
}
