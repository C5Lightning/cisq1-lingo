package nl.hu.cisq1.lingo.trainer.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FeedbackTest {

	@Test
	@DisplayName("Word is guessed if all letters are correct")
	void wordIsGuessed(){
		Feedback fb = new Feedback("woord",
				List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT,
						Mark.CORRECT, Mark.CORRECT));
		assertTrue(fb.isWordGuessed());

	}

	@Test
	@DisplayName("Word is not guessed if atleast one letter is incorrect")
	void wordIsNotGuessed(){
		Feedback fb = new Feedback("woord",
				List.of(Mark.CORRECT, Mark.ABSENT, Mark.CORRECT,
						Mark.CORRECT, Mark.CORRECT));
		assertFalse(fb.isWordGuessed());

	}

	@Test
	@DisplayName("Word is not a valid attempt at a guess")
	void guessIsInvalid(){
		Feedback fb = new Feedback("woord",
				List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT,
						Mark.CORRECT, Mark.CORRECT,
						Mark.INVALID, Mark.INVALID));
		assertTrue(fb.guessIsInvalid());

	}

	@Test
	@DisplayName("Word is a valid attempt at a guess")
	void guessIsNotInvalid(){
		Feedback fb = new Feedback("woord",
				List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT,
						Mark.CORRECT, Mark.CORRECT));
		assertFalse(fb.guessIsInvalid());

	}
}