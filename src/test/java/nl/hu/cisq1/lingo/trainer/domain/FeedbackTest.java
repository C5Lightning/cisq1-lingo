package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.exception.InvalidFeedbackException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class FeedbackTest {

	@Test
	@DisplayName("Word is guessed if all letters are correct")
	void wordIsGuessed() {
		Feedback fb = new Feedback("woord",
				List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT,
						Mark.CORRECT, Mark.CORRECT));
		assertTrue(fb.isWordGuessed());

	}

	@Test
	@DisplayName("Word is not guessed if atleast one letter is incorrect")
	void wordIsNotGuessed() {
		Feedback fb = new Feedback("woord",
				List.of(Mark.CORRECT, Mark.ABSENT, Mark.CORRECT,
						Mark.CORRECT, Mark.CORRECT));
		assertFalse(fb.isWordGuessed());

	}

	@Test
	@DisplayName("Word is not a valid attempt at a guess")
	void guessIsInvalid() {
		assertThrows(InvalidFeedbackException.class,
				() -> new Feedback("woord",
						List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT,
								Mark.CORRECT, Mark.CORRECT,
								Mark.INVALID, Mark.INVALID)));

	}

	@Test
	@DisplayName("Word is a valid attempt at a guess")
	void guessIsNotInvalid() {
		Feedback fb = new Feedback("woord",
				List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT,
						Mark.CORRECT, Mark.CORRECT));
		assertFalse(fb.guessIsInvalid());

	}

	@Test
	@DisplayName("Word is not a valid attempt at a guess")
	void lengthIsInvalidException() {
		assertThrows(InvalidFeedbackException.class,
				() -> new Feedback("woord", List.of(Mark.CORRECT)));
	}

	@Test
	@DisplayName("Word is guessed if all letters are correct")
	void wordIsGuessedStatic() {
		assertTrue(Feedback.correct("woord").isWordGuessed());

	}

	@Test
	@DisplayName("Word is not guessed if atleast one letter is incorrect")
	void guessIsInvalidStatic() {
		assertTrue(Feedback.invalid("woord").guessIsInvalid());
	}

	@ParameterizedTest
	@MethodSource("provideHintExamples")
	@DisplayName("Here the hint function will test if word is incorrect")
	void giveHintFunctionTest(String givenHint, String expectedHint) {
		assertEquals(givenHint, expectedHint);
	}

	private static Stream<Arguments> provideHintExamples() {
		Feedback fbSamen = Feedback.absent("woord");
		Feedback fbHallo = Feedback.correct("hallo");
		Feedback fbBattle = new Feedback("bottle", List.of(Mark.CORRECT,
				Mark.ABSENT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT));
		Feedback fbInitial = new Feedback("certain", List.of(Mark.ABSENT, Mark.ABSENT,
				Mark.ABSENT, Mark.CORRECT, Mark.ABSENT, Mark.ABSENT, Mark.ABSENT));

		return Stream.of(
				Arguments.of(fbSamen.giveHint("S....", "samen"), "S...."),
				Arguments.of(fbHallo.giveHint("HA...", "hallo"), "HALLO"),
				Arguments.of(fbBattle.giveHint("B.TT..", "battle"), "B.TTLE"),
				Arguments.of(fbInitial.giveHint("IN.....", "initial"), "IN.T...")
		);
	}
}