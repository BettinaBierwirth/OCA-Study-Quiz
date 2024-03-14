package com.bettinabierwirth.ocacatquiz;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    /**
     *Test method to check if the application context is correct.
     * This method retrieves the context of the app under test using InstrumentationRegistry
     * and checks if its package name matches the expected package name.
     */

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.bettinabierwirth.ocacatquiz", appContext.getPackageName());
    }
}