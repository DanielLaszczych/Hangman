import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameState {

    private boolean gameActive;
    private int guessLeft;
    private int lettersCorrect;
    private Character[] alphabetArray = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
        'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    private List<Character> alphabet;
    private List<Character> charactersLeft;
    private List<Integer> correctIndexes = new ArrayList<>();
    private String wordToGuess;
    Label guesses;

    public GameState() {
        gameActive = false;
        guessLeft = 10;
        lettersCorrect = 0;
        alphabet = new ArrayList<>(Arrays.asList(alphabetArray));
        charactersLeft = new ArrayList<>(Arrays.asList(alphabetArray));
        wordToGuess = ReadFile.randomWord("words.txt");
        guesses = new Label("Guesses left: " + guessLeft);
    }

    public GameState(int guessLeft, List<Character> charactersLeft, String wordToGuess, int lettersCorrect, List<Integer> correctIndexes) {
        this.gameActive = true;
        this.guessLeft = guessLeft;
        this.charactersLeft = charactersLeft;
        this.wordToGuess = wordToGuess;
        this.lettersCorrect = lettersCorrect;
        this.correctIndexes = correctIndexes;
        alphabet = new ArrayList<>(Arrays.asList(alphabetArray));
        guesses = new Label("Guesses left: " + guessLeft);
    }

    public boolean getGameActive() {
        return gameActive;
    }

    public int getGuessLeft() {
        return guessLeft;
    }

    public int getLettersCorrect() { return lettersCorrect; }

    public void setGameActive(boolean gameActive) {
        this.gameActive = gameActive;
    }

    public List<Integer> getCorrectIndexes() { return correctIndexes; }

    public void addCorrectIndexes(List<Integer> appendList) {
        correctIndexes.addAll(appendList);
    }

    public void decrementGuesses() {
        if (guessLeft > 0) {
            guessLeft--;
        }
    }

    public void incrementLettersCorrect() {
        lettersCorrect++;
    }

    public void checkGameState() {
        if (lettersCorrect == lengthOfWord()) {
            setGameActive(false);
            Hangman.topBar.disableSave();
            AlertBox.display("Winner", "You won", 250, 240);
        } else if (guessLeft == 0) {
            Hangman.guessGrid.revealRemainingCharacters();
            setGameActive(false);
            Hangman.topBar.disableSave();
            AlertBox.display("Loser", "You lost (the word was \"" + wordToGuess + "\")", 250, 400);
        }
    }

    public int lengthOfWord() {
        return wordToGuess.length();
    }

    public List<Character> getCharacterLeft() {
        return charactersLeft;
    }

    public List<Character> getAlphabet() {
        return alphabet;
    }

    public void useCharacter(Character c) {
        charactersLeft.remove(c);
        Hangman.gameControl.checkCharacter(c);
        if(guessLeft > 0 && getGameActive()) {
            Hangman.topBar.enableSave();
        }
    }

    public String getWordToGuess() { return wordToGuess; }

    public List<Character> getWordsToGuessAsList() {
        List<Character> characters = new ArrayList<>();
        for(char c : wordToGuess.toCharArray()) {
            characters.add(Character.toUpperCase(c));
        }
        return characters;
    }

    public void displayGuesses() {
        GridPane gridPane = (GridPane)Hangman.layout.getCenter();
        gridPane.add(guesses, 1, 0, 4, 1);
        guesses.setScaleX(3);
        guesses.setScaleY(3);
    }

    public void updateGuesses() {
        guesses.setText("Guesses left: " + guessLeft);
    }


}
