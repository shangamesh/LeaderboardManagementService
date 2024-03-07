package com.leaderboard.servicesimpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.leaderboard.entities.Leaderboard;
import com.leaderboard.entities.LeaderboardEntry;
import com.leaderboard.entities.ScoreEntry;
import com.leaderboard.services.LeaderboardService;
import com.leaderboard.utilities.LeaderboardHelper;

public class LeaderboardServiceImpl implements LeaderboardService {
	private Map<String, List<Leaderboard>> gameLeaderboards;
	private Set<String> supportedGames;
	private LeaderboardHelper leaderboardHelper;

	public LeaderboardServiceImpl() {
		gameLeaderboards = new HashMap<>();
		supportedGames = new HashSet<>();
		leaderboardHelper = new LeaderboardHelper();
	}

	@Override
	public Set<String> getSupportedGames() {
		return supportedGames;
	}

	@Override
	public String createLeaderboard(String gameID, long startEpochSeconds, long endEpochSeconds) {
		String leaderboardID = UUID.randomUUID().toString();
		Leaderboard leaderboard = new Leaderboard(leaderboardID, startEpochSeconds, endEpochSeconds);
		// Add current leaderboard to gameLeaderboards
		leaderboardHelper.addLeaderBoardToGameLeaderboards(gameLeaderboards, supportedGames, gameID, leaderboard);
		return leaderboardID;
	}

	@Override
	public void submitScore(String gameID, String userID, int score) {
		if (gameLeaderboards.containsKey(gameID)) {
			List<Leaderboard> leaderboards = gameLeaderboards.get(gameID);
			for (Leaderboard leaderboard : leaderboards) {
				leaderboard.addScoreEntry(new ScoreEntry(userID, score));
			}
		}
	}

	@Override
	public List<LeaderboardEntry> getLeaderboard(String gameID, String leaderboardID) {
		if (gameLeaderboards.containsKey(gameID)) {
			List<Leaderboard> leaderboards = gameLeaderboards.get(gameID);
			for (Leaderboard leaderboard : leaderboards) {
				if (leaderboard.getLeaderboardID().equals(leaderboardID)) {
					return leaderboard.getRankList();
				}
			}
		}
		return Collections.emptyList();
	}

	@Override
	public List<LeaderboardEntry> listPlayersNext(String gameID, String leaderboardID, String userID, int nPlayers) {
		List<LeaderboardEntry> rankList = getLeaderboard(gameID, leaderboardID);
		int index = leaderboardHelper.findUserIndex(rankList, userID);

		// If user not found, return empty list
		if (index == -1) {
			return Collections.emptyList();
		}

		// Fetch next players
		List<LeaderboardEntry> nextPlayers = leaderboardHelper.fetchNextPlayers(rankList, index, nPlayers);
		// Fetch previous players
		List<LeaderboardEntry> prevPlayers = leaderboardHelper.fetchPreviousPlayers(rankList, index, nPlayers);

		// Combine next and previous players and return
		List<LeaderboardEntry> combinedPlayers = new ArrayList<>();
		combinedPlayers.addAll(prevPlayers);
		combinedPlayers.addAll(nextPlayers);
		return combinedPlayers;
	}
}
