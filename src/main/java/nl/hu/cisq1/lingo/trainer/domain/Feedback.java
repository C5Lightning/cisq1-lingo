package nl.hu.cisq1.lingo.trainer.domain;

import lombok.Getter;
import lombok.Setter;
import nl.hu.cisq1.lingo.trainer.domain.exception.InvalidFeedbackException;
import nl.hu.cisq1.lingo.words.domain.Word;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Getter
@Setter
public class Feedback {
	private Word word;
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

	public ArrayList<Mark> giveHint(){
		String w = this.word.getValue();
		String a = this.attempt;
		ArrayList<Mark> marks = new ArrayList<>();
		char[] ch = new char[a.length()];

		for (int i = 0; i < a.length(); i++) {
			ch[i] = a.charAt(i);
		}
		for(char i : ch){
			if (i == w.charAt(i)){
				marks.add(Mark.CORRECT);
			}
			else if(w.contains(String.valueOf(i))){
				marks.add(Mark.PRESENT);
			}
			else if(!w.contains(String.valueOf(i))){
				marks.add(Mark.ABSENT);
			}
		}
		for(int i = 0; i < a.length() - this.word.getLength(); i++){
			marks.add(Mark.INVALID);
		}
		return marks;
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
