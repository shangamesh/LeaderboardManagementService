package com.leaderboard.utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.leaderboard.entities.Leaderboard;
import com.leaderboard.entities.LeaderboardEntry;

public class LeaderboardHelper {

	public void addLeaderBoardToGameLeaderboards(Map<String, List<Leaderboard>> gameLeaderboards,
			Set<String> supportedGames, String gameID, Leaderboard leaderboard) {
		if (!gameLeaderboards.containsKey(gameID)) {
			gameLeaderboards.put(gameID, new ArrayList<>());
			supportedGames.add(gameID);
		}
		gameLeaderboards.get(gameID).add(leaderboard);
	}

	public int findUserIndex(List<LeaderboardEntry> rankList, String userID) {
		for (int i = 0; i < rankList.size(); i++) {
			if (rankList.get(i).getUserID().equals(userID)) {
				return i;
			}
		}
		return -1;
	}

	public List<LeaderboardEntry> fetchNextPlayers(List<LeaderboardEntry> rankList, int index, int nPlayers) {
		int startIndexNext = index + 1;
		int endIndexNext = Math.min(rankList.size(), startIndexNext + nPlayers);
		return rankList.subList(startIndexNext, endIndexNext);
	}

	public List<LeaderboardEntry> fetchPreviousPlayers(List<LeaderboardEntry> rankList, int index, int nPlayers) {
		int startIndexPrev = Math.max(0, index - nPlayers);
		int endIndexPrev = index;
		return rankList.subList(startIndexPrev, endIndexPrev);
	}

}
