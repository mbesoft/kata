package org.dk.mbe.domain;

import org.dk.mbe.services.IPlayer;
import org.dk.mbe.services.IScoreCard;

public class Player implements IPlayer {

	private String name;
	private IScoreCard scoreCard;
	private boolean hasTheAdvantage;

	public Player(String fullname) {
		this.name = fullname;
		this.scoreCard = new ScoreCard();
	}

	@Override
	public void winGame() {
		scoreCard.addGame();
	}
	
	@Override
	public void winPoint() {
		scoreCard.addPoint();
	}

	@Override
	public int retrieveGameScore() {
		return scoreCard.getGameScore();
	}

	public void reset() {
		hasTheAdvantage = false;
		scoreCard.reset();
	}

	@Override
	public int retrieveSetScore() {
		return scoreCard.getSetScore();
	}

	@Override
	public void winTiePoint() {
		scoreCard.addTiePoint();
	}

	@Override
	public int retrieveTieScore() {
		return scoreCard.getTieScore();
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


}
