package org.dk.mbe.tdd;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.dk.mbe.domain.Game;
import org.dk.mbe.domain.TennisFactory;
import org.dk.mbe.services.IGame;
import org.junit.Before;
import org.junit.Test;

public class GameTest {
	
	private IGame game;
	private String player1, player2;
	
	@Before
    public void setUp(){
		player1 = "Rafa";
		player2 = "Djoko";
        game = Game.launch(player1,player2);
    }
	
	@Test
	public void should_return_40_15_when_P1_wins_3points_and_P2_wins_1point() {
		game.winPoint(player1);// 15 - 0
		game.winPoint(player1);// 30 - 0
		game.winPoint(player2);// 30 - 15
		game.winPoint(player1);// 40 - 15
		assertThat(game.scoreStatement(), is("40 - 15"));
	}
	
	@Test
	public void should_return_DEUCE_DEUCE_when_score_is_deuce() {
		game.winPoint(player1);// 15 - 0
		game.winPoint(player2);// 15 - 15
		game.winPoint(player1);// 30 - 15
		game.winPoint(player2);// 30 - 30
		game.winPoint(player1);// 40 - 30
		game.winPoint(player2);// 40 - 40
		assertThat(game.scoreStatement(), is("40 - 40"));
		game.winPoint(player1);
		assertThat(game.scoreStatement(), is("ADV - 40"));
		game.winPoint(player2);
		assertTrue(game.isDeuceEnabled());
		assertThat(game.scoreStatement(), is("DEUCE - DEUCE"));
	}
	
	@Test
	public void should_return_P1Name_when_score_is_DEUCE_and_P1_wins_2points() {
		should_return_DEUCE_DEUCE_when_score_is_deuce();
		game.winPoint(player1);
		assertThat(game.scoreStatement(), is("ADV - 40"));
		game.winPoint(player1);
		assertThat(game.findGameWinner(), is(player1));
		assertThat(game.scoreStatement(), is("0 - 0"));
		
	}
	
	
}
