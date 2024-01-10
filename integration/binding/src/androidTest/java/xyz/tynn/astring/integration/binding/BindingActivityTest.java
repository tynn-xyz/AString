//  Copyright 2024 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.integration.binding;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.SmallTest;

import org.junit.Rule;
import org.junit.Test;

@SmallTest
public class BindingActivityTest {

    @Rule
    public final ActivityScenarioRule<BindingActivity> activityRule =
            new ActivityScenarioRule<>(BindingActivity.class);

    @Test
    public void bindingActivityTest() {
        onView(withId(R.id.astring_binding_converter))
                .perform(replaceText("AString"),
                        closeSoftKeyboard());

        onView(withId(R.id.astring_binding_string))
                .check(matches(withText("AString")));

        onView(withId(R.id.astring_binding_adapter))
                .check(matches(withText("AString")));

        onView(withId(R.id.astring_binding_converter))
                .perform(replaceText(""));

        onView(withId(R.id.astring_binding_string))
                .check(matches(withText("")));

        onView(withId(R.id.astring_binding_adapter))
                .check(matches(withText("")));
    }
}
