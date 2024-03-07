package com.leaderboard.entities;

//Class representing a users score entry
public class ScoreEntry {
	private String userID;
	private int score;

	// Getters & Setters
	public ScoreEntry(String userID, int score) {
		this.userID = userID;
		this.score = score;
	}

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
