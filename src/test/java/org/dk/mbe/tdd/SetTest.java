package org.dk.mbe.tdd;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.dk.mbe.domain.Set;
import org.dk.mbe.services.ISet;
import org.junit.Before;
import org.junit.Test;

public class SetTest {
	
	ISet set;
	String player1, player2;
	
	
	@Before
	public void setUp() {
		player1 = "Rafa";
		player2 = "Djoko";
		set = Set.launch(player1,player2);
	}
	
	@Test
	public void should_get_1_1_when_P1_and_P2_win_a_game() {
		set.score(player1);
		set.score(player2);
		assertThat(set.scoreStatement(), equalTo("Set: 1 - 1"));
	}
	
	@Test
	public void should_get_P1_winner_when_P1_wins_6games_and_P2_less_than_5games() {
		set.score(player2); // 0 - 1
		set.score(player2); // 0 - 2
		set.score(player2); // 0 - 3
		for (int i = 0; i < 5; i++) {
			set.score(player1);
		}
		assertThat(set.scoreStatement(), equalTo("Set: 5 - 3"));
		assertThat(set.getMatchWinner(), nullValue());
		set.score(player1);// 6 - 3
		assertThat(set.scoreStatement(), equalTo("Set: 6 - 3"));
		assertThat(set.getMatchWinner(), equalTo("Rafa"));
	}
	
	public void should_get_P1_winner_when_P1_wins_7games_and_P2_less_than_6games() {
		for (int i = 0; i < 5; i++) {
			set.score(player1);
			set.score(player2);
		}
		set.score(player1); // 6 - 5
		assertThat(set.getMatchWinner(), nullValue());
		set.score(player1);
		assertThat(set.scoreStatement(), equalTo("Set: 7 - 5"));
		assertThat(set.getMatchWinner(), equalTo("Rafa"));
	}
	
	@Test
	public void should_start_tiebreak_when_P1_and_P2_win_6games() {
		for (int i = 0; i < 6; i++) {
			set.score(player1);
			set.score(player2);
		}
		assertThat(set.scoreStatement(), equalTo("Set: 6 - 6"));
		set.score(player1); // TieBreak ON : 1 - 0
		assertTrue(set.isTieBreakEnabled());
		assertThat(set.scoreStatement(), equalTo("TieBreak: 1 - 0"));
	}
	
	public void should_get_P1_winner_when_he_wins_7_tie_points_and_P2_less_than_6() {
		for (int i = 0; i < 6; i++) {
			set.score(player1);
			set.score(player2);
		}
		assertThat(set.scoreStatement(), equalTo("Set: 6 - 6"));
		for (int i = 0; i < 5; i++) {
			set.score(player1);
			set.score(player2);
		}
		assertThat(set.scoreStatement(), equalTo("TieBreak: 5 - 5"));
		set.score(player1);
		set.score(player1);
		assertThat(set.scoreStatement(), equalTo("TieBreak: 7 - 5"));
		assertThat(set.getMatchWinner(), equalTo("Rafa"));
	}
	
	public void should_get_no_winner_if_delta_scoreP1P2_tiebreak_is_less_than_2() {
		for (int i = 0; i < 6; i++) {
			set.score(player1);
			set.score(player2);
		}
		for (int i = 0; i < 40; i++) {
			set.score(player1);
			set.score(player2);
		}
		set.score(player1);
		assertThat(set.scoreStatement(), equalTo("TieBreak: 41 - 40"));
		assertThat(set.getMatchWinner(), nullValue());
	}

}
