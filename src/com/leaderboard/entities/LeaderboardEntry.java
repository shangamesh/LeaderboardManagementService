package com.leaderboard.entities;

//Class representing a leaderboard entry
public class LeaderboardEntry {
	private String userID;
	private int score;

	public LeaderboardEntry(String userID, int score) {
		this.userID = userID;
		this.score = score;
	}

	// Getters & Setters
	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}