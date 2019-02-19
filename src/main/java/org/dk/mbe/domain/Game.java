package org.dk.mbe.domain;

import org.dk.mbe.services.IGame;

public class Game implements IGame {
	// TODO use interfaces instead of impls (even if it doesn't really describe a behaviour)
	private Player player1;
	private Player player2;
	private Player gameWinner;
	private boolean deuceEnabled;
	
	public Game() {
		// TODO Auto-generated constructor stub
	}

	private Game(String fullname1, String fullname2) {
		this.player1 = new Player(fullname1);
		this.player2 = new Player(fullname2);
	}

	public static IGame launch(String player1Name, String player2Name) {
		return new Game(player1Name, player2Name);
	}

	public void winPoint(String playerName) {
		Player pointWinner, opponent;
		if (playerName.equals(player1.getName())) {
			pointWinner = player1;
			opponent = player2;
		} else {
			pointWinner = player2;
			opponent = player1;
		}
		if (deuceEnabled) {
			pointWinner.setAdvantageEnabled(true);
			deuceEnabled = false;
		} else if (opponent.advantageEnabled()) {
			opponent.setAdvantageEnabled(false);
			deuceEnabled = true;
		} else if (pointWinner.advantageEnabled()) {
			gameWinner = pointWinner;
			reset();
			pointWinner.setAdvantageEnabled(false);
		} else if (pointWinner.retrieveGameScore() == 40 && opponent.retrieveGameScore() < 40) {
			gameWinner = pointWinner;
			reset();
		}else if (pointWinner.retrieveGameScore() == 40 && pointWinner.retrieveGameScore() == opponent.retrieveGameScore()) {
			pointWinner.setAdvantageEnabled(true);
		} else {
			pointWinner.winPoint();
		}
	}

	public String scoreStatement() {
		if (deuceEnabled) {
			return "DEUCE - DEUCE";
		} else if (player1.advantageEnabled()) {
			return "ADV - 40";
		} else if (player1.advantageEnabled()) {
			return "40 - ADV";
		}
		return player1.retrieveGameScore() + " - " + player2.retrieveGameScore();
	}

	public void reset() {
		player1.reset();
		player2.reset();
	}
	
	public boolean isDeuceEnabled() {
		return deuceEnabled;
	}

	public String findGameWinner() {
		return gameWinner != null ? gameWinner.getName() : null;
	}

}
