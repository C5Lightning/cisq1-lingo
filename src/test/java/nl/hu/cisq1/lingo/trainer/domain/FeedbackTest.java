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
		Feedback fb = new Feedback("woord",
				List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT,
						Mark.CORRECT, Mark.CORRECT,
						Mark.INVALID, Mark.INVALID));
		assertTrue(fb.guessIsInvalid());

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
	void giveHintFunctionTest(Feedback fb, List<Character> expectedHint) {

	}

	private static List<Character> toCList(String str){
		return str.chars().mapToObj(i -> Character.forDigit(i, 10)).collect(Collectors.toList());
	}

	private static Stream<Arguments> provideHintExamples() {
		Feedback fbWoord = Feedback.absent("woord");

		Feedback fbHallo = Feedback.correct("hallo");
		return Stream.of(
				Arguments.of(fbWoord.giveHint(toCList("....."), "woord"), toCList(".....")),
				Arguments.of(fbWoord, true),
				Arguments.of("  ", true),
				Arguments.of("not blank", false)
		);
	}
	// tests voor elke hint optie, moet met max 3 tests wel te doen zijn
	//
}