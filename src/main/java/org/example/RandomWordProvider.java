package org.example;

import java.io.IOException;
import java.io.RandomAccessFile;

import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static java.nio.charset.StandardCharsets.UTF_8;

public class RandomWordProvider {

    public String getRandomWord(GameTheme gameTheme) {
        String fileName;
        switch (gameTheme) {
            case FOOD -> fileName = "russian_nouns_food.txt";
            case ANIMALS -> fileName = "russian_nouns_animals.txt";
            case CITIES -> fileName = "russian_nouns_cities.txt";
            default -> fileName = "russian_nouns_animals.txt";
        }
        try (RandomAccessFile raf = new RandomAccessFile("src/main/resources/" + fileName, "r");) {
            int fileSize = (int) raf.length();
            raf.seek(getRandomNumber(fileSize)); //перемещаем «курсор»
            raf.readLine();
            String line = raf.readLine();
            if (line != null) {//
                return new String(line.getBytes(ISO_8859_1), UTF_8);
            } else {
                return getRandomWord(gameTheme);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private int getRandomNumber(int max) {
        return (int) (Math.random() * ++max);
    }
}
