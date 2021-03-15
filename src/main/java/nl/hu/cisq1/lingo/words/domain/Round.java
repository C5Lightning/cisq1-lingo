package nl.hu.cisq1.lingo.words.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.hu.cisq1.lingo.trainer.domain.Feedback;
import nl.hu.cisq1.lingo.trainer.domain.Mark;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@NoArgsConstructor
@Getter
@Setter
public class Round {
	private Word wordToGuess;
	private ArrayList<String> attempts;
	private Feedback lastFeedback;
	private Stack<String> hints;

	public Round(Word wordToGuess){
		this.wordToGuess = wordToGuess;
		this.attempts = new ArrayList<>();
		String firstHint = wordToGuess.getValue().substring(0, 1) +
				IntStream.range(0, wordToGuess.getLength() - 1)
				.mapToObj(i -> '.').collect(Collectors.toList());
		hints.push(firstHint);
	}

	public void makeGuess(String attempt){
		this.attempts.add(attempt);
		List<Mark> markings = new ArrayList<>(attempt.length());

		IntStream.range(0, attempt.length())
				.forEach(i -> markings.set(i, this.wordToGuess.getValue().charAt(i) == attempt.charAt(i)
						? Mark.CORRECT : Mark.ABSENT));

		if(attempt.length() == this.wordToGuess.getLength()){
			Feedback fb = new Feedback(attempt, markings);
			this.hints.push(fb.giveHint(this.hints.peek(), this.wordToGuess.getValue()));
		}
	}

	public String giveHint(){
		return this.hints.peek();
	}

	public int getRoundnumber(){
		return attempts.size();
	}

}
