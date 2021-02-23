package nl.hu.cisq1.lingo.trainer.domain;

import lombok.Getter;
import lombok.Setter;
import nl.hu.cisq1.lingo.trainer.domain.exception.InvalidFeedbackException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Getter
@Setter
public class Feedback {
	private String attempt;
	private List<Mark> hints;

	public Feedback(String attempt, List<Mark> hints) throws InvalidFeedbackException {
		this.attempt = attempt;
		this.hints = hints;
	}

	public static Feedback correct(String attempt) {
		return new Feedback(attempt, IntStream.range(0, attempt.length())
				.mapToObj(i -> Mark.CORRECT).collect(Collectors.toList()));
	}

	public static Feedback invalid(String attempt) {
		return new Feedback(attempt, IntStream.range(0, attempt.length())
				.mapToObj(i -> Mark.INVALID).collect(Collectors.toList()));
	}

	public boolean isWordGuessed(){
		return hints.stream().allMatch(m -> m == Mark.CORRECT);
	}

	public boolean guessIsInvalid(){
		return hints.stream().anyMatch(m -> m == Mark.INVALID);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Feedback feedback = (Feedback) o;
		return Objects.equals(attempt, feedback.attempt) &&
				Objects.equals(hints, feedback.hints);
	}

	@Override
	public int hashCode() {
		return Objects.hash(attempt, hints);
	}

	@Override
	public String toString() {
		return "Feedback{" +
				"attempt='" + attempt + '\'' +
				", hints=" + hints +
				'}';
	}
}
