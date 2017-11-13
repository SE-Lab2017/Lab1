package org.yoooo.se1test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.yoooo.se1.Application;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    SuiteB.class
})
public class TestSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
        String text = "Panda is favored by people, because they are so lovely. Many people come to Sichuan to see the pandas in the zoo,  bad behaviors. Some tourists throw away the rubbish and some adults let their kids stand in the handrail, just to get closer to pandas. We need to act politely, for protecting the environment and animals. ";
        Application obj = Application.getInstance();
        Class<Application> cls = Application.class;
        Field mg = cls.getDeclaredField("mGraph");
        Method cifc = cls.getDeclaredMethod("convertInputFileContent", String.class);
        cifc.setAccessible(true);
        String preprocessedText = (String) cifc.invoke(obj, text);
        Method stg = cls.getDeclaredMethod("stringToGraph", String.class);
        stg.setAccessible(true);
        Object g = stg.invoke(obj, preprocessedText);
        mg.setAccessible(true);
        mg.set(obj, g);
    }

}
