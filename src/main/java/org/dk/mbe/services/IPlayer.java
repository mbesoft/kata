package org.dk.mbe.services;

public interface IPlayer {
	
	String getName(); //Not really part of a behaviour

	void winPoint();

	void winGame();
	
	int retrieveGameScore();
	
	int retrieveSetScore();

	void winTiePoint();

	int retrieveTieScore();
	
}
