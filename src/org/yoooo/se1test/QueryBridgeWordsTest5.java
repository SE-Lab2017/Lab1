package org.yoooo.se1test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.yoooo.se1.QueryBridgeWords;

public class QueryBridgeWordsTest5 {

    private String wordL = "the";
    private String wordR = "and";
    private String expected = "The bridge words from \"the\" to \"and\" are: environment, and rubbish.";
    private String expectedRev = "No bridge words from \"and\" to \"the\"!";

    @Test
    public void test() {
        assertEquals(expected, QueryBridgeWords.queryBridgeWords(wordL, wordR));
        assertEquals(expectedRev, QueryBridgeWords.queryBridgeWords(wordR, wordL));
    }

}
