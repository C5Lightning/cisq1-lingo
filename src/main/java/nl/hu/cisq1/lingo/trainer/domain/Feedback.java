package nl.hu.cisq1.lingo.trainer.domain;

import lombok.Getter;
import lombok.Setter;
import nl.hu.cisq1.lingo.trainer.domain.exception.InvalidFeedbackException;
import nl.hu.cisq1.lingo.words.domain.Word;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Getter
@Setter
public class Feedback {
	private Word word;
	private String attempt;
	private List<Mark> markings;

	public Feedback(String attempt, List<Mark> markings) throws InvalidFeedbackException {
		this.attempt = attempt;
		this.markings = markings;
	}

	public static Feedback correct(String attempt) {
		return new Feedback(attempt, IntStream.range(0, attempt.length())
				.mapToObj(i -> Mark.CORRECT).collect(Collectors.toList()));
	}

	public static Feedback absent(String attempt) {
		return new Feedback(attempt, IntStream.range(0, attempt.length())
				.mapToObj(i -> Mark.ABSENT).collect(Collectors.toList()));
	}

	public static Feedback invalid(String attempt) {
		return new Feedback(attempt, IntStream.range(0, attempt.length())
				.mapToObj(i -> Mark.INVALID).collect(Collectors.toList()));
	}

	public boolean isWordGuessed() {
		return markings.stream().allMatch(m -> m == Mark.CORRECT);
	}

	public boolean guessIsInvalid() {
		return markings.stream().anyMatch(m -> m == Mark.INVALID);
	}

	public String giveHint(String previousHint, String wordToGuess) throws InvalidFeedbackException {
		if (guessIsInvalid()) throw new InvalidFeedbackException("Markings invalid");
		StringBuilder hint = new StringBuilder();
		hint.setLength(wordToGuess.length());
		wordToGuess = wordToGuess.toUpperCase();

//		if (hints.empty())
//			hints.push(IntStream.range(0, this.attempt.length()).mapToObj(i -> '.').
//					collect(Collectors.toList()));

		for (int i = 0; i < wordToGuess.length(); i++)
			hint.setCharAt(i,  markings.get(i) == Mark.CORRECT ? wordToGuess.charAt(i) : previousHint.charAt(i));

		return hint.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Feedback feedback = (Feedback) o;
		return Objects.equals(attempt, feedback.attempt) &&
				Objects.equals(markings, feedback.markings);
	}

	@Override
	public int hashCode() {
		return Objects.hash(attempt, markings);
	}

	@Override
	public String toString() {
		return "Feedback{" +
				"attempt='" + attempt + '\'' +
				", hints=" + markings +
				'}';
	}
}
