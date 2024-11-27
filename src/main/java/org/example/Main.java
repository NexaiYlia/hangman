package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.IntStream;

public class Main {

    public static final String GAME_WORDS_PATH_NAME = "src/main/resources/words.txt";

    public static void main(String[] args) {

        List<String> words = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(GAME_WORDS_PATH_NAME));

            while (scanner.hasNext()) {
                words.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found exception");
        }
        Scanner keyboard = new Scanner(System.in);

        Random random = new Random();
        String word = words.get(random.nextInt(words.size()));

        System.out.println(word);
        List<Character> playerLetters = new ArrayList<>();
        printWordStars(word, playerLetters);
        int wrongCount = 0;

        while (true) {

            if (!isLetterInWord(keyboard, word, playerLetters)) {
                wrongCount++;
            }

            printMen(wrongCount);

            if (wrongCount >= 6) {
                System.out.println("You are a loser! Game over!");
                break;
            }
            if (printWordStars(word, playerLetters)) {
                System.out.println("You win!");
                break;
            }
        }
    }

    private static boolean isLetterInWord(Scanner keyboard, String word, List<Character> playerGuesses) {
        System.out.println("Enter a letter:");

        String letterGuess = keyboard.nextLine();
        playerGuesses.add(letterGuess.charAt(0));

        return word.contains(letterGuess);
    }


    private static boolean printWordStars(String word, List<Character> playerGuesses) {
        int correctCount = 0;
        for (int i = 0; i < word.length(); i++) {
            if (playerGuesses.contains(word.charAt(i))) {
                System.out.print(word.charAt(i));
                correctCount++;
            } else {
                System.out.print("*");
            }
        }
        System.out.println();
        return (word.length() == correctCount);
    }

    private static final Map<Integer, String> HANGMAN_PARTS = Map.of(
            1, "  o  \n",
            2, "/",
            3, " \\ \n",
            4, " |  \n",
            5, "/",
            6, " \\ \n"
    );

    private static void printMen(Integer wrongCount) {
        System.out.println("This word does not contain such a letter");
        StringBuilder hangmanDrawing = new StringBuilder();
        IntStream.rangeClosed(1, wrongCount).forEach(i -> hangmanDrawing.append(HANGMAN_PARTS.get(i)));
        System.out.println(hangmanDrawing);
    }
}