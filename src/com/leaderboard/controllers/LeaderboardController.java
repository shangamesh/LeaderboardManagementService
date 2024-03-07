package com.leaderboard.controllers;

import java.util.List;
import java.util.Set;

import com.leaderboard.entities.LeaderboardEntry;
import com.leaderboard.services.LeaderboardService;

//Class representing the leaderboard controller
public class LeaderboardController {
	private LeaderboardService leaderboardService;

	public LeaderboardController(LeaderboardService leaderboardService) {
		this.leaderboardService = leaderboardService;
	}

	// Method to create a new leaderboard for a game
	public String createLeaderboard(String gameID, long startEpochSeconds, long endEpochSeconds) {
		return leaderboardService.createLeaderboard(gameID, startEpochSeconds, endEpochSeconds);
	}

	// Method to submit a score for a user in a game
	public void submitScore(String gameID, String userID, int score) {
		leaderboardService.submitScore(gameID, userID, score);
	}

	// Method to get the rank list for a specific leaderboard
	public List<LeaderboardEntry> getLeaderboard(String gameID, String leaderboardID) {
		return leaderboardService.getLeaderboard(gameID, leaderboardID);
	}

	// Method to fetch the next N players and the previous N players relative to a
	// given user
	public List<LeaderboardEntry> listPlayersNext(String gameID, String leaderboardID, String userID, int nPlayers) {
		return leaderboardService.listPlayersNext(gameID, leaderboardID, userID, nPlayers);
	}

	// Method to get a list of supported GAME IDs
	public Set<String> getSupportedGames() {
		return leaderboardService.getSupportedGames();
	}
}
