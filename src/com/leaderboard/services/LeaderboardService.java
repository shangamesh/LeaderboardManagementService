package com.leaderboard.services;

import java.util.List;
import java.util.Set;

import com.leaderboard.entities.LeaderboardEntry;

public interface LeaderboardService {
	Set<String> getSupportedGames();

	String createLeaderboard(String gameID, long startEpochSeconds, long endEpochSeconds);

	void submitScore(String gameID, String userID, int score);

	List<LeaderboardEntry> getLeaderboard(String gameID, String leaderboardID);

	List<LeaderboardEntry> listPlayersNext(String gameID, String leaderboardID, String userID, int nPlayers);
}
