package org.dk.mbe.domain;

import org.dk.mbe.services.IPlayer;
import org.dk.mbe.services.ISet;

public class Set implements ISet {

	private IPlayer player1, player2;
	private String matchWinner;
	private boolean tieBreakEnabled;

	public Set() {
	}

	public Set(String player1Name, String player2Name) {
		player1 = new Player(player1Name);
		player2 = new Player(player2Name);
	}

	public static ISet launch(String player1Name, String player2Name) {
		return new Set(player1Name, player2Name);
	}

	@Override
	public void score(String playerName) {
		IPlayer pointWinner = findPlayer(playerName);
		IPlayer opponent = opponentOf(playerName);
		if (!isSetPoint(pointWinner, opponent) && !isTieBreakPoint(pointWinner, opponent)) {
			if (isTieBreak(pointWinner, opponent)) {
				pointWinner.winTiePoint();
				tieBreakEnabled = true;
			} else {
				pointWinner.winGame();
			}
		} else if (isSetPoint(pointWinner, opponent)) {
			pointWinner.winGame();
			matchWinner = pointWinner.getName();
		} else if (isTieBreakPoint(pointWinner, opponent)) {
			pointWinner.winTiePoint();
			matchWinner = pointWinner.getName();
		}
	}

	@Override
	public String scoreStatement() {
		if (tieBreakEnabled) {
			return "TieBreak: " + player1.retrieveTieScore() + " - " + player2.retrieveTieScore();
		}
		return "Set: " + player1.retrieveSetScore() + " - " + player2.retrieveSetScore();
	}

	public String getMatchWinner() {
		return matchWinner;
	}

	private IPlayer findPlayer(String playerName) {
		return playerName.equals(player1.getName()) ? player1 : player2;

	}

	private IPlayer opponentOf(String playerName) {
		return !playerName.equals(player1.getName()) ? player1 : player2;
	}

	public static boolean isSetPoint(IPlayer pointWinner, IPlayer opponent) {
		int pointWinnerScore = pointWinner.retrieveSetScore() + 1;
		int opponentScore = opponent.retrieveSetScore();
		if (pointWinnerScore >= 6 && (pointWinnerScore - opponentScore) >= 2) {
			return true;
		}
		return false;
	}

	public static boolean isTieBreakPoint(IPlayer pointWinner, IPlayer opponent) {
		int pointWinnerScore = pointWinner.retrieveTieScore() + 1;
		int opponentScore = opponent.retrieveTieScore();
		if (pointWinnerScore >= 7 && (pointWinnerScore - opponentScore) >= 2) {
			return true;
		}
		return false;
	}

	public boolean isTieBreak(IPlayer pointWinner, IPlayer opponent) {
		int pointWinnerScore = pointWinner.retrieveSetScore();
		int opponentScore = opponent.retrieveSetScore();
		if (pointWinnerScore == 6 && opponentScore == 6) {
			return true;
		}
		return false;
	}

	public boolean isTieBreakEnabled() {
		return tieBreakEnabled;
	}
}
