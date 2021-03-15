package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.words.domain.Game;
import nl.hu.cisq1.lingo.words.domain.Round;
import nl.hu.cisq1.lingo.words.domain.Word;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class GameTest {

	@Test
	void createConstructor(){
		ArrayList arrayList = new ArrayList();
		for(int i = 0; i < 2; i++){
			arrayList.add(new Round(new Word("woord")));
		}
		Game game = new Game(53, arrayList);

		ArrayList arrayList1 = new ArrayList();
		for(int i = 0; i < 2; i++){
			arrayList1.add(new Round(new Word("woord")));
		}
		Game game2 = new Game(53, arrayList);
		assertEquals(game,game2);
	}

}
