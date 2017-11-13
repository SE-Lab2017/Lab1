package org.yoooo.se1test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Test;
import org.yoooo.se1.Application;
import org.yoooo.se1.GenerateNewText;

public class GenerateNewTextTest4 {

    private String text = "the and";
    private String preprocessedText = null;
    private String[] expected = {"the rubbish and", "the environment and"};
    
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
        boolean equal = false;
        String actual = GenerateNewText.generateNewText(preprocessedText);
        for (int i = 0; i < expected.length; ++i) {
            if (actual != null && actual.equals(expected[i])) {
                equal = true;
                break;
            }
        }
        if (equal) {
            
        } else {
            StringBuilder alert = new StringBuilder();
            alert.append(String.format("expected \"%s\"", expected[0]));
            for (int i = 1; i < expected.length; ++i) {
                alert.append(String.format(" or \"%s\"", expected[i]));
            }
            alert.append(String.format(", but got %s.", actual == null ? "null" : "\"" + actual + "\""));
            fail(alert.toString());
        }
    }
    
}
