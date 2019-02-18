package org.dk.mbe.domain;

import org.dk.mbe.services.IPlayer;
import org.dk.mbe.services.IScoreCard;

public class Player implements IPlayer {

	private String name;
	private IScoreCard scoreCard;
	private boolean hasTheAdvantage;

	public Player(String fullname) {
		this.name = fullname;
		scoreCard = new ScoreCard();
	}

	public void winPoint() {
		scoreCard.addPoint();
	}

	public String getName() {
		return name;
	}

	public boolean advantageEnabled() {
		return hasTheAdvantage;
	}

	public void setAdvantageEnabled(boolean hasTheAdvantage) {
		this.hasTheAdvantage = hasTheAdvantage;

	}

	public int retrieveScore() {
		return scoreCard.getGameScore();
	}

	public void reset() {
		hasTheAdvantage = false;
		scoreCard.reset();
	}
}
