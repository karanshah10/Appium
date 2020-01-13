package com.example.apple.appiumtesting;

import android.util.Log;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Apple on 5/2/2018.
 */

public class TestNg {

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
        Log.d("LOG", "testcase");
    }
}
