Feature: Start a game
  The player starts a new game.
  A new game is added to the list of saved games.

  Rule: A player can have multiple games
  Rule: The points start at 0

Feature: Pause a game
  The player pauses and exits the game.
  The game state is saved.

Feature: Resume a game
  The player continues playing a game that was paused.

Feature: Play a game
  The player plays rounds until they lose a round or pause the game.
  There are 3 types of rounds: 5-, 6-, or 7-letter words.
  The first round of a game is always a 5-letter word.
    The number of letters of words in sequential rounds follow the pattern:
      5, 6, 7.

  Rule:

Feature: Play a round
  The player is shown the first letter of a word.

  The player has a maximum of 5 chances to guess the word.
  If the player guesses the word,
    they get points, the round ends, and a new round starts.
  If not, the game ends.


  Rule: There can only be 5 turns per round

Feature: Guess a word
  The player enters a guess.
  After each guess, the player receives feedback.

Feature: Receiving feedback
  The player gets feedback after a guess.
  A guess is valid if:
  The guess is spelled correctly and exists
  and the length of the guess matches the word.
  If it's invalid the player gets notified.
  if it's valid the player gets feedback.
