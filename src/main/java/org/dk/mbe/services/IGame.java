package org.dk.mbe.services;

public interface IGame{
	
	public void winPoint(String playerName);

	public String scoreStatement();

	public boolean isDeuceEnabled();
	
	public String findGameWinner();

}
