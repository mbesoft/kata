package org.dk.mbe.services;

public interface IScoreCard {

	void addPoint();
	
	void addGame();
	
	int getGameScore();
	
	int getSetScore();
	
	void reset();

	void addTiePoint();

	int getTieScore();
	
	
}
