package org.dk.mbe.services;

public interface IPlayer {
	
	void winPoint();
	
	String getName(); //Not really part of a contract

	int retrieveScore();
	
}
