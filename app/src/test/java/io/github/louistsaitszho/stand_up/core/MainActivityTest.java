package io.github.louistsaitszho.stand_up.core;

import android.app.Activity;
import android.view.View;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;

import io.github.louistsaitszho.stand_up.R;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Test
    public void test() throws Exception {
        Activity activity = Robolectric.setupActivity(MainActivity.class);

        FloatingActionButton fab = activity.findViewById(R.id.fab_add_task);

        assertNotNull(fab);
        assertEquals(fab.getVisibility(), View.VISIBLE);
    }

}