package com.zak.datauses.utilities;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;

import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class UtilsTest {

    Context context;

    @Before
    public void setUp() {
        ActivityScenario.launch(MobileDataUsageActivity.class);
        context = ApplicationProvider.getApplicationContext();
        assertNotNull(context);
    }

    @Test
    public void checkInternetConnection() {
        assertTrue(Utils.checkInternetConnection(context));
    }
}