package org.yoooo.se1test;

import org.junit.Before;
import org.junit.Test;
import org.yoooo.se1.Application;
import org.yoooo.se1.GenerateNewText;

import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;

public class GenerateNewTextTest3 {

    private String text = "zoo,  bad . Some  throw away  rubbish and";
    private String preprocessedText = null;
    private String expected = "zoo bad behaviors some tourists throw away the rubbish and";

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
        assertEquals(expected, new GenerateNewText().generateNewText(preprocessedText));
    }

}
