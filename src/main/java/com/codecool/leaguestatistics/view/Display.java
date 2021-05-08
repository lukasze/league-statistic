package com.codecool.leaguestatistics.view;

import com.codecool.leaguestatistics.model.Team;

import java.util.List;

/**
 * Provides console view
 */
public class Display {

    public static void displayTable(List<Team> teams) {
        System.out.printf("%-3s|%-40s|%-8s|%-6s|%-7s|%-7s\n","Id", "Team Name", "Points", "Wins", "Draws", "Loses");
        for (int i = 0; i < teams.size(); i++) {
            System.out.printf("%-3d|%-40s|%-8d|%-6d|%-7d|%-7d\n",
                    i+1, teams.get(i).getName(), teams.get(i).getCurrentPoints(),
                    teams.get(i).getWins(), teams.get(i).getDraws(), teams.get(i).getLoses());
        }
    }

    public static void displayResult(Team team1, Team team2, int resultTeam1, int resultTeam2) {
        System.out.printf("%s vs %s | %d : %d \n", team1.getName(), team2.getName(), resultTeam1, resultTeam2);
    }

    public static void displayRound(int round) {
        System.out.printf(" --- Round {%d} ---\n", round);
    }
}
