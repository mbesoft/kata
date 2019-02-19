package org.dk.mbe.services;

public interface ISet {

	String scoreStatement();

	void score(String playerName);
	
	String getMatchWinner();

	boolean isTieBreakEnabled();
	
}
