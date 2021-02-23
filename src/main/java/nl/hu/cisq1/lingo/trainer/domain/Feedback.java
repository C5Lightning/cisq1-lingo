package nl.hu.cisq1.lingo.trainer.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class Feedback {
	private String attempt;
	private List<Mark> hints;

	public Feedback(String attempt) {
		this.attempt = attempt;
		this.hints = new ArrayList<>();
	}

	public Feedback(String attempt, List<Mark> hints) {
		this.attempt = attempt;
		this.hints = hints;
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
