package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Score {
    private int mistakes;
    private String word;
    private ArrayList<Character> score;

    public int getMistakes() {
        return mistakes;
    }

    public void setMistakes(int mistakes) {
        this.mistakes = mistakes;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
    public ArrayList<Character> getScore() {
        return score;
    }

    public void setScore(ArrayList<Character> score) {
        this.score = score;
    }

    public Score(String word) {
        this.mistakes = 0;
        this.word = word;
        this.score = initialazeScore();
    }

    private ArrayList<Character> initialazeScore() {
        return new ArrayList<>(Collections.nCopies(word.length(), '_'));
    }

    public void changeScore(Character letter) {
        List<Integer> indexes = findOccurrencesOfCharacter(letter);
        indexes.stream().forEach(index -> score.set(index, letter));
        if (indexes.isEmpty()) {
            mistakes++;
        }
    }

    public void displayScore() {
        // выводит количество отгаданых букв
        score.stream().forEach(c -> System.out.print(c + " "));
        System.out.println();

    }

    //Ищет вхождения буквы в загаданое слово
    private List<Integer> findOccurrencesOfCharacter(Character letter) {
        return IntStream
                .iterate(word.indexOf(letter), index -> index >= 0, index -> word.indexOf(letter, index + 1))
                .boxed()
                .collect(Collectors.toList());
    }
}
