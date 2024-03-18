package org.example;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Game {

    private String word;
    private RandomWordProvider randomWordProvider;
    private Score score;

    private GameTheme gameTheme;
    private UnusedLetters unusedLetters;

    public String getWord() {
        return word;

    }

    public void setWord(String word) {
        this.word = word;
    }

    public RandomWordProvider getRandomWordProvider() {
        return randomWordProvider;
    }

    public void setRandomWordProvider(RandomWordProvider randomWordProvider) {
        this.randomWordProvider = randomWordProvider;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }


    public GameTheme getGameTheme() {
        return gameTheme;
    }

    public void setGameTheme(GameTheme gameTheme) {
        this.gameTheme = gameTheme;
    }

    public UnusedLetters getUnusedLetters() {
        return unusedLetters;
    }

    public void setUnusedLetters(UnusedLetters unusedLetters) {
        this.unusedLetters = unusedLetters;
    }

    public void startGame() {
        while (tryAgain().equals("да")) {
            this.randomWordProvider = new RandomWordProvider();
            this.gameTheme = chooseGameTheme();
            this.word = randomWordProvider.getRandomWord(gameTheme);
            this.score = new Score(word);
            this.unusedLetters = new UnusedLetters();
            while (!(isLose() | isWin())) {
                HangmanDraw.drawHangman(score.getMistakes());
                score.displayScore();
                unusedLetters.displayUnusedLetters();
                System.out.println();
                Character letter = enterLetter();
                if (!isValid(letter)) {
                    System.out.println("Можно вводить только буквы русского алфавита!");
                }
                if (isValid(letter) && !unusedLetters.isUsed(letter)) {
                    score.changeScore(letter);
                    unusedLetters.markUsedLetter(letter);
                }

            }
            finishGame();
        }
    }


    private GameTheme chooseGameTheme() {
        int themeNumber = 1;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите тему игры \n1 - Животные\n2 - Еда\n3 - Города");
        if (scanner.hasNextInt()) {
            themeNumber = scanner.nextInt();
        }
        switch (themeNumber) {
            case 2 -> {
                return GameTheme.FOOD;
            }
            case 3 -> {
                return GameTheme.CITIES;
            }
            default -> {
                return GameTheme.ANIMALS;
            }
        }
    }

    private Character enterLetter() {
        System.out.println("Введите букву: ");
        Scanner sc = new Scanner(System.in);
        return sc.next().toLowerCase().charAt(0);
    }

    private boolean isValid(Character letter) {
        return Pattern.matches("[а-яё]", letter.toString());
    }

    private boolean isWin() {
        return !score.getScore().contains('_');
    }

    private boolean isLose() {
        return score.getMistakes() >= 7;
    }

    private void finishGame() {
        if (isWin()) {
            score.displayScore();
            System.out.println("Победа!");
        }
        if (isLose()) {
            HangmanDraw.drawHangman(score.getMistakes());
            System.out.println("Вы проиграли");
        }
        System.out.println("Загаданное слово: " + word);
    }

    private String tryAgain() {
        System.out.println("Хотите начать игру? да/нет");
        Scanner sc = new Scanner(System.in);
        return sc.next().toLowerCase();
    }

}
