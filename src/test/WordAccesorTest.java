package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import edu.drexel.cs451.hangman.accessor.WordAccessor;

public class WordAccesorTest {

	@Test
	public void getRandomWord() {
		final WordAccessor accessor = WordAccessor.getInstance();
		final List<String> dictionary = accessor.getDictionary();
		final String word = accessor.getRandomWord();
		assertTrue(dictionary.contains(word));
	}
}
