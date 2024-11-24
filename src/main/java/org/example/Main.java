package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static final String PATH_NAME = "src/main/resources/words.txt";

    public static void main(String[] args) {


        List<String> words = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(PATH_NAME));

            while (scanner.hasNext()) {
                words.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found exception");
        }
        Scanner keyboard = new Scanner(System.in);


        Random random = new Random();
        String word = words.get(random.nextInt(words.size()));

        System.out.println(word);//чтобы легче было тестировать
        List<Character> playerLetters = new ArrayList<>();
        printWordStars(word, playerLetters);
        int wrongCount = 0;

        while (true) {


            if (!getPlayerLetter(keyboard, word, playerLetters)) {
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

    private static boolean getPlayerLetter(Scanner keyboard, String word, List<Character> playerGuesses) {
        System.out.println("Enter a letter:");

        String letterGuess = keyboard.nextLine();
        playerGuesses.add(letterGuess.charAt(0));

        return (word.contains(letterGuess));
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

    private static void printMen(Integer wrongCount) {
        System.out.println("This word does not contain such a letter");
        System.out.println("------");
        System.out.println("  |   ");
        if (wrongCount >= 1) {
            System.out.println("  O  ");
        }
        if (wrongCount >= 2) {
            System.out.print("/");
            if (wrongCount >= 3) {
                System.out.println("   \\");
            } else {
                System.out.println("");
            }
        }
        if (wrongCount >= 4) {
            System.out.println("  |  ");
        }
        if (wrongCount >= 5) {
            System.out.print("/");
            if (wrongCount >= 6) {
                System.out.println("   \\");
            } else {
                System.out.println("");
            }
        }

        System.out.println();
    }
}