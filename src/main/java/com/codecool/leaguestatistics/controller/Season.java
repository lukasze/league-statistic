package com.codecool.leaguestatistics.controller;

import com.codecool.leaguestatistics.Utils;
import com.codecool.leaguestatistics.factory.LeagueFactory;
import com.codecool.leaguestatistics.model.LeagueStatistics;
import com.codecool.leaguestatistics.model.Player;
import com.codecool.leaguestatistics.model.Team;
import com.codecool.leaguestatistics.view.Display;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides all necessary methods for season simulation
 */
public class Season {

    private List<Team> league;

    public Season() {
        league = new ArrayList<>();
    }

    /**
     * Fills league with new teams and simulates all games in season.
     * After all games played calls table to be displayed.
     */
    public void run() {
        this.league = LeagueFactory.createLeague(6);
        playAllGames();
        // Call Display methods below
        Display.displayTable(LeagueStatistics.getAllTeamsSorted(league));

    }

    /**
     * Playing whole round. Everyone with everyone one time. Number of teams in league should be even.
     * Following solution represents the robin-round tournament.
     */
    private void playAllGames() {
        int numDays = league.size() - 1;
        int halfSize = league.size() / 2;

        List<Team> tempTeams = new ArrayList<>(league);
        tempTeams.remove(0);

        int teamsSize = tempTeams.size();
        for (int round = 0; round < numDays; round++) {
            int teamIdx = round % teamsSize;

            Display.displayRound(round + 1);
            playMatch(tempTeams.get(teamIdx), league.get(0));

            for (int idx = 1; idx < halfSize; idx++) {
                int firstTeam = (round + idx) % teamsSize;
                int secondTeam = (round + teamsSize - idx) % teamsSize;
                playMatch(tempTeams.get(firstTeam), tempTeams.get(secondTeam));
            }
        }
    }

    /**
     * Plays single game between two teams and displays result after.
     */
    private void playMatch(Team team1, Team team2) {
        int resultTeam1 = getScoredGoals(team1);
        int resultTeam2 = getScoredGoals(team2);

        if (resultTeam1 > resultTeam2) {
            team1.setWins(team1.getWins() + 1);
            team2.setLoses(team2.getLoses() + 1);
        } else if (resultTeam2 > resultTeam1) {
            team2.setWins(team2.getWins() + 1);
            team1.setLoses(team1.getLoses() + 1);
        } else {
            team1.setDraws(team1.getDraws() + 1);
            team2.setDraws(team2.getDraws() + 1);
        }
        Display.displayResult(team1, team2, resultTeam1, resultTeam2);
    }

    /**
     * Checks for each player of given team chance to score based on skillrate.
     * Adds scored goals to player's and team's statistics.
     * @return All goals scored by the team in current game
     */
    private int getScoredGoals(Team team) {
        int goals = 0;
        for (Player player : team.getPlayers()) {
            if (player.getSkillRate() >= Utils.getRandomValue(1, 101)) {
                player.setGoals(player.getGoals() + 1);
                goals += 1;
            }
        }
        return goals;
    }
}
