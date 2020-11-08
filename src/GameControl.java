import java.util.ArrayList;
import java.util.List;

public class GameControl {

    private GameState gameState;

    public GameControl() {
        gameState = new GameState();
    }

    public GameState getGameState() {
        return gameState;
    }

    public void reset() {
        gameState = new GameState();
    }

    public void loadGame(GameState gameState) {
        this.gameState = gameState;
    }

    public void checkCharacter(Character c) {
        List<Character> word = gameState.getWordsToGuessAsList();
        if(!word.contains(c)) {
            gameState.decrementGuesses();
            gameState.updateGuesses();
            Hangman.stickFigure.updateStickFigure();
            gameState.checkGameState();
        } else {
            int x = 0;
            List<Integer> indexes = new ArrayList<>();
            for(Character check : word) {
                if(check == c) {
                    indexes.add(x);
                }
                x++;
            }
            Hangman.guessGrid.revealCharacters(indexes);
            gameState.addCorrectIndexes(indexes);
            gameState.checkGameState();
        }
    }

}
