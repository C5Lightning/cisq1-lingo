Feature: Start a game
  The player starts a new game.
  A new game is added to the list of saved games.

  Rule: A player can have multiple games
  Rule: The points start at 0

  As a player,
  I want to start a game,
  So that I can play a game of Lingo

  Scenario: A game is started
  Given I am on lingotrainer page
  When I click the play a game button
  Then a game is being build
  And the player can play the game

Feature: Pause a game
  The player pauses and exits the game.
  The game state is saved.

  As a player,
  I want to pause a game,
  So that I can continue a game whenever I want

  Scenario: A game is being paused
  Given A game is in the progress of being played
  When I click the pause button
  Then the game saves and is being paused

Feature: Resume a game
  The player continues playing a game that was paused.

  As a player,
  I want to resume a game,
  So that I can start a paused game whenever I want

  Scenario: A game is being resumed
  Given there is a paused game
  When I click on the resume button
  Then the paused games continues

Feature: Play a game
  The player plays rounds until they lose a round or pause the game.
  There are 3 types of rounds: 5-, 6-, or 7-letter words.
  The first round of a game is always a 5-letter word.
    The number of letters of words in sequential rounds follow the pattern:
      5, 6, 7.

  As a player,
  I want to Play a game,
  So that I can train myself to be better at lingo

  Scenario: a game is being played
  Given A game has been started
  When I play a round
  Then the word had "<previous length>" letters
  And the next round has "<next length>" letters

    #Example
    | previous length | next length |
    | 5               | 6           |
    | 6               | 7           |
    | 7               | 5           |

Feature: Play a round
  The player is shown the first letter of a word.

  The player has a maximum of 5 chances to guess the word.
  If the player guesses the word,
    they get points, the round ends, and a new round starts.
  If not, the game ends.

  Rule: There can only be 5 turns per round

  As a player,
  I want to play a round,
  So that I can succesfully guess a word


  Scenario: Plays a round succesfully
    Given I correctly guess the word before the 5 turns
    When the game verifies the guess word
    Then I continue to the next round

  #Failure path
    Given I didn't correctly guess the word before the 5 turns
    When the game rejects the word
    Then The game stops


Feature: Guess a word
  The player enters a guess.
  After each guess, the player receives feedback.

  As a player,
  I want to guess a word,
  So that I can get more points

  Scenario: A word is being guessed correctly
    Given I am playing a round
    When I guess "<Guess Word>"
    And The "<guessed word>" is the same as "<Word>"
    Then I get points

    #Failure Path
    Given I am playing a round
    When I guess "<Guessed Word>"
    And The "<Guessed Word>" is not equal to "<Word>"
    Then I get "<Feedback>"
    And I get another guess

      #Example
      | Guessed Word    | Word        | Feedback |
      | Plate           |  Plato      | P L A T  |
      | Druif           | Doelen      | D        |
      | Angst           | Angst       |          |

Feature: Receiving feedback
  The player gets feedback after a guess.
  A guess is valid if:
  The guess is spelled correctly and exists
  and the length of the guess matches the word.
  If it's invalid the player gets notified.
  if it's valid the player gets feedback.

  As a player,
  I want to receive feedback,
  So that I can correctly guess the word in the future

  Scenario: Feedback is received
      Given I Guess "<Guessed Word>"
      And "<Guessed Word>" is not equal to "<Word>"
      When the game verifies "<Guessed Word>"
      Then I get "<Feedback>"
      And the game tells me which letters are right
      And the game tells me which letters are correct but on the wrong spot
      And the game tells me which letters are incorrect

        #Example
        | Guessed Word    | Word  | Feedback  |
        | Plate           | Plato | P L A T   |
        | Appel           | Aapje | A   P     |
        | Angst           | Angst |           |