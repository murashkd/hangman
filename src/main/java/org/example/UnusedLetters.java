package org.example;

import java.util.LinkedHashMap;
import java.util.stream.IntStream;

public class UnusedLetters {
    private LinkedHashMap<Character, Boolean> alphabetLetters;

    public static void main(String[] args) {
        new UnusedLetters().displayUnusedLetters();
    }

    public UnusedLetters() {
        this.alphabetLetters = new LinkedHashMap<Character, Boolean>();
        initializeLettersMap();
    }

    public LinkedHashMap<Character, Boolean> getAlphabetLettersSet() {
        return alphabetLetters;
    }

    public void setAlphabetLettersSet(LinkedHashMap<Character, Boolean> alphabetLetters) {
        this.alphabetLetters = alphabetLetters;
    }

    public void initializeLettersMap() {
        IntStream.rangeClosed('а', 'я').forEach(ch -> alphabetLetters.put((char) ch, false));
        alphabetLetters.put('ё', false);

    }

    public void markUsedLetter(Character letter) {
        alphabetLetters.put(letter, true);
    }

    public void displayUnusedLetters() {
        System.out.println("Не использованые буквы");
        alphabetLetters.keySet().stream().forEach(key -> System.out.print(alphabetLetters.get(key) ? "" : key + " "));
    }


    public boolean isUsed(Character letter) {
        if (alphabetLetters.get(letter) == null) {
            return false;
        }
        return alphabetLetters.get(letter);
    }

}
