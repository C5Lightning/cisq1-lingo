package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.words.domain.Round;
import nl.hu.cisq1.lingo.words.domain.Word;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


public class RoundTest {

	@Test
	void createConstructorTest(){
		Round round = new Round(new Word("hallo"));
		Round round2 = new Round(new Word("hallo"));
		assertEquals(round, round2);

	}
}
