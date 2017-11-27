package org.yoooo.se1test;

import org.junit.Test;
import org.yoooo.se1.QueryBridgeWords;

import static org.junit.Assert.assertEquals;

public class QueryBridgeWordsTest3 {

    private String wordL = "to";
    private String wordR = "come";
    private String expected = "No bridge words from \"to\" to \"come\"!";
    private String expectedRev = "No bridge words from \"come\" to \"to\"!";

    @Test
    public void test() {
        assertEquals(expected, new QueryBridgeWords().queryBridgeWords(wordL, wordR));
        assertEquals(expectedRev, new QueryBridgeWords().queryBridgeWords(wordR, wordL));
    }

}
