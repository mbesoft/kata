package org.dk.mbe.domain;

import org.dk.mbe.services.IScoreCard;

public class ScoreCard implements IScoreCard {

	private int gameScore;

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

	public int getGameScore() {
		return gameScore;
	}


	public void reset() {
		gameScore = 0;
	}

}
