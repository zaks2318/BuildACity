package com.example.buildacity;

import android.widget.DatePicker;

import androidx.test.espresso.contrib.PickerActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
public class SignUpTest {
    @Rule
    public ActivityScenarioRule<SignUpActivity> activityScenarioRule
            = new ActivityScenarioRule<>(SignUpActivity.class);

    @Test
    public void canEnterNameAndSignUp() throws InterruptedException {
        onView(withId(R.id.nameInput)).perform(typeText("aaaaaaa"));
        onView(withId(R.id.emailInput)).perform(typeText("abcadw@gmail.com"));
        onView(withId(R.id.userNameInput)).perform(typeText("bbbbbbbb"));
        onView(withId(R.id.dialog)).perform((click()));
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(1900, 01, 01));
        onView(withText("OK")).perform(click());
        onView(withId(R.id.submitBut)).perform((click()));
    }

    @Test
        public void retainStateAfterRotate(){
        onView(withId(R.id.nameInput)).perform(typeText("aaaaaaa"));
        onView(withId(R.id.emailInput)).perform(typeText("abcadw@gmail.com"));
        onView(withId(R.id.userNameInput)).perform(typeText("bbbbbbbb"));

        TestUtils.rotateScreen(TestUtils.getActivity(activityScenarioRule));

        onView(withId(R.id.nameInput)).check(matches(withText("aaaaaaa")));
        onView(withId(R.id.userNameInput)).check(matches(withText("bbbbbbbb")));
        onView(withId(R.id.emailInput)).check(matches(withText("abcadw@gmail.com")));
    }

    @Test
    public void checkEmail() {
        onView(withId(R.id.emailInput)).perform(typeText("adwaqdmail.com"));
        onView(withId(R.id.dialog)).perform((click()));
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(1900, 01, 01));
        onView(withText("OK")).perform(click());
        onView(withId(R.id.submitBut)).perform((click()));

        onView((allOf(withId(R.id.emailInput), hasErrorText("Email invalid"))));

    }

    @Test
    public void checkEmptyEmail() {
        onView(withId(R.id.emailInput)).perform(typeText(""));
        onView(withId(R.id.dialog)).perform((click()));
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(1900, 01, 01));
        onView(withText("OK")).perform(click());
        onView(withId(R.id.submitBut)).perform((click()));

        onView((allOf(withId(R.id.emailInput), hasErrorText("Email can't be empty"))));

    }

    @Test
    public void checkEmptyUsername() {
        onView(withId(R.id.userNameInput)).perform(typeText(""));
        onView(withId(R.id.dialog)).perform((click()));
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(1900, 01, 01));
        onView(withText("OK")).perform(click());
        onView(withId(R.id.submitBut)).perform((click()));

        onView((allOf(withId(R.id.userNameInput), hasErrorText("enter your user name pls"))));

    }

    @Test
    public void checkUsernameTooLong() {
        onView(withId(R.id.userNameInput)).perform(typeText("abcdefghijklmnopqrst"));
        onView(withId(R.id.dialog)).perform((click()));
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(1900, 01, 01));
        onView(withText("OK")).perform(click());
        onView(withId(R.id.submitBut)).perform((click()));

        onView((allOf(withId(R.id.userNameInput), hasErrorText("username too long"))));

    }

    @Test
    public void checkEmptyName() {
        onView(withId(R.id.nameInput)).perform(typeText(""));
        onView(withId(R.id.dialog)).perform((click()));
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(1900, 01, 01));
        onView(withText("OK")).perform(click());
        onView(withId(R.id.submitBut)).perform((click()));

        onView((allOf(withId(R.id.userNameInput), hasErrorText("enter your name pls"))));

    }

    @Test
    public void checkAge() {
        onView(withId(R.id.dialog)).perform((click()));
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2020, 05, 01));
        onView(withText("OK")).perform(click());
        onView(withId(R.id.submitBut)).perform((click()));

        onView((allOf(withId(R.id.date_text), hasErrorText("you are under 18, so you can't sign up"))));

    }

}
