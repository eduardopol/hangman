package com.assessment.hangman.entity.factory;

import org.junit.Assert;
import org.junit.Test;

public class WordFactoryTest {

	@Test
	public void mustReturnRandomWord() {
		String word = new WordFactory().generateWord();

		Assert.assertNotNull(word);
	}

}
