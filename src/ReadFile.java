import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadFile {

    public static String randomWord(String fileName)  {
        URL filepath = ReadFile.class.getResource(fileName);
        File file = null;
        try {
            file = new File(filepath.toURI());
        } catch (URISyntaxException e) {
            System.out.println(e);
        }
        List<String> words = new ArrayList<>();
        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                words.add(reader.nextLine());
            }

            int max = words.size() - 1;
            int min = 0;
            int index = (int)(Math.random() * ((max - min) + 1)) + min;
            return words.get(index);
       } catch (FileNotFoundException e) {
            System.out.println("error");
        }
        return null;
    }

    public static GameState loadGame(File file) {
        try {
            Scanner reader = new Scanner(file);
            String s = reader.nextLine();
            if (s.equals("Valid Hangman Game File")) {
                int guessesLeft;
                List<Character> charactersLeft = new ArrayList<>();
                String wordToGuess;
                int lettersCorrect;
                List<Integer> correctIndexes = new ArrayList<>();
                guessesLeft = reader.nextInt();
                reader.nextLine();
                String str = reader.nextLine();
                for (int i = 0; i < str.length(); i++) {
                    charactersLeft.add(str.charAt(i));
                }
                wordToGuess = reader.nextLine();
                lettersCorrect = reader.nextInt();
                reader.nextLine();
                while(reader.hasNextInt()) {
                    correctIndexes.add(reader.nextInt());
                }
                return new GameState(guessesLeft, charactersLeft, wordToGuess, lettersCorrect, correctIndexes);
            } else {
                return Hangman.gameControl.getGameState();
            }
        } catch (FileNotFoundException e) {
            System.out.println("error");
        }
        return Hangman.gameControl.getGameState();
    }

}
