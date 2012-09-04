/**
 * Main source to search word. Similar to Resource.
 * This is a singleton pattern. There will be only 
 * one wordAccessor instance in the whole program.
 * @see ResourceManager
 */

package edu.drexel.cs451.hangman.accessor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class WordAccessor implements ProvidingWord {
    private static String filename = "HangmanWords.txt";
    private final String DictionaryFile;
    private List<String> dictionary;
    private static WordAccessor singleInstance = null;

    public static WordAccessor getInstance() {
        if (singleInstance == null)
            singleInstance = new WordAccessor();
        return singleInstance;
    }

    private WordAccessor() {
        this(filename);
    }

    private WordAccessor(String filename) {
        this.DictionaryFile = filename;
        loadDictionary();
    }

    private void loadDictionary() {
        // open the dictionary file and read dictionary into an ArrayList
        Scanner input;
        input = new Scanner(this.getClass().getResourceAsStream(DictionaryFile));
        dictionary = new ArrayList<String>();
        while (input.hasNext())
            dictionary.add(input.next().toLowerCase());
        input.close();

    }
    
    public List<String> getDictionary()
    {
    	return Collections.unmodifiableList(dictionary);
    }

    public String getRandomWord() {
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(dictionary.size());

        return dictionary.get(randomInt);

    }

}
