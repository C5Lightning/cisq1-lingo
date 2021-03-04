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
	private Stack<List<Character>> hints;

	public Feedback(String attempt, List<Mark> markings) throws InvalidFeedbackException {
		this.attempt = attempt;
		this.markings = markings;
		this.hints = new Stack<>();
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

	public List<Character> giveHint(List<Character> previousHint, String wordToGuess) {
		List<Character> hint = new ArrayList<>();

		if (hints.empty())
			hints.push(IntStream.range(0, this.attempt.length()).mapToObj(i -> '.').
					collect(Collectors.toList()));

		for (int i = 0; i < this.attempt.length(); i++)
			hint.set(i,  markings.get(i) == Mark.CORRECT ? wordToGuess.charAt(i) : previousHint.get(i));

		hints.push(hint);
		return hint;
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
