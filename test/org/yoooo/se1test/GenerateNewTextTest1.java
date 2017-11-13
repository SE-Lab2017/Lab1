package org.yoooo.se1test;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Test;
import org.yoooo.se1.Application;
import org.yoooo.se1.GenerateNewText;

public class GenerateNewTextTest1 {

    private String text = "zoo";
    private String preprocessedText = null;
    private String expected = "zoo";
    
    @Before
    public void setUp() throws Exception {
        Application obj = Application.getInstance();
        Class<Application> cls = Application.class;
        Method cifc = cls.getDeclaredMethod("convertInputFileContent", String.class);
        cifc.setAccessible(true);
        preprocessedText = (String) cifc.invoke(obj, text);
    }
    
    @Test
    public void test() {
        assertEquals(expected, GenerateNewText.generateNewText(preprocessedText));
    }
    
}
