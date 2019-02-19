package org.dk.mbe.domain;

import org.dk.mbe.services.IScoreCard;

public class ScoreCard implements IScoreCard {

	private int gameScore;
	private int setScore;
	private int tieScore;

	@Override
	public void addPoint() {
		switch (gameScore) {
		case 0:
			gameScore = 15;
			break;
		case 15:
			gameScore = 30;
			break;
		case 30:
			gameScore = 40;
			break;
		default:
			throw new IllegalStateException("Invalid state. You can not win more points.");
		}
	}

	@Override
	public void addGame() {
		setScore++;
	}

	@Override
	public void addTiePoint() {
		tieScore++;
	}

	@Override
	public void reset() {
		gameScore = 0;
		setScore = 0;
		tieScore = 0;
	}

	public int getGameScore() {
		return gameScore;
	}

	public int getSetScore() {
		return setScore;
	}

	public int getTieScore() {
		return tieScore;
	}

}
