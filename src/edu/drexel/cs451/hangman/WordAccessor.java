/**
 * Main source to search word. Similar to Resource.
 * This is a singleton pattern. There will be only 
 * one wordAccessor instance in the whole program.
 * @see ResourceManager
 */

package edu.drexel.cs451.hangman;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
	
	private WordAccessor(String filename)
	{
		this.DictionaryFile = filename;
	}
	
	public void loadDictionary()
	{
	 // open the dictionary file and read dictionary into an ArrayList
		Scanner input;
		try {
			input = new Scanner(new File(DictionaryFile));
			dictionary = new ArrayList<String>();
			while (input.hasNext())
				dictionary.add(input.next().toLowerCase());
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
	
	public String getRandomWord()
	{
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(dictionary.size());
		
		return dictionary.get(randomInt);
		
	}
	

}
