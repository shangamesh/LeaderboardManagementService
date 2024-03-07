package com.leaderboard.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

//Class representing a leaderboard
public class Leaderboard {
	private String leaderboardID;
	private long startEpochSeconds;
	private long endEpochSeconds;
	private SortedSet<LeaderboardEntry> entries;

	public Leaderboard(String leaderboardID, long startEpochSeconds, long endEpochSeconds) {
		this.leaderboardID = leaderboardID;
		this.startEpochSeconds = startEpochSeconds;
		this.endEpochSeconds = endEpochSeconds;
		this.entries = new TreeSet<>((a, b) -> Integer.compare(b.getScore(), a.getScore()));
	}

	// Method to get the rank list of the leaderboard
	public List<LeaderboardEntry> getRankList() {
		return new ArrayList<>(entries);
	}

	// Method to add a score entry to the leaderboard
	public void addScoreEntry(ScoreEntry scoreEntry) {
		// Check if the score entry's timestamp falls within the active period of the
		// leaderboard
		if (System.currentTimeMillis() / 1000 >= startEpochSeconds
				&& System.currentTimeMillis() / 1000 <= endEpochSeconds) {
			// Add the score entry if it's higher than the current score of the user
			entries.removeIf(entry -> entry.getUserID().equals(scoreEntry.getUserID()));
			entries.add(new LeaderboardEntry(scoreEntry.getUserID(), scoreEntry.getScore()));
		}
	}

	// Getters & Setters
	public String getLeaderboardID() {
		return leaderboardID;
	}

	public void setLeaderboardID(String leaderboardID) {
		this.leaderboardID = leaderboardID;
	}

	public long getStartEpochSeconds() {
		return startEpochSeconds;
	}

	public void setStartEpochSeconds(long startEpochSeconds) {
		this.startEpochSeconds = startEpochSeconds;
	}

	public long getEndEpochSeconds() {
		return endEpochSeconds;
	}

	public void setEndEpochSeconds(long endEpochSeconds) {
		this.endEpochSeconds = endEpochSeconds;
	}

	public SortedSet<LeaderboardEntry> getEntries() {
		return entries;
	}
}
