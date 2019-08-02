package io.github.louistsaitszho.stand_up.core;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.github.louistsaitszho.stand_up.R;
import io.github.louistsaitszho.stand_up.core.ui.MainActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void When_AppStarts_Then_FabShouldBeVisible() {
        onView(withId(R.id.fab_add_task)).check(matches(isDisplayed()));
    }

    @Test
    public void When_ActivityStarts_Then_ListShouldBeVisible() {
        onView(withId(R.id.recycler_view_task_list)).check(matches(isDisplayed()));
    }

}