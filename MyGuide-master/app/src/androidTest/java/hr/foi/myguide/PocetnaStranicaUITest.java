package hr.foi.myguide;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.longClick;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isSelected;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by DudasPC on 04.02.2018..
 */
@RunWith(AndroidJUnit4.class)
public class PocetnaStranicaUITest {
    @Rule
    public ActivityTestRule<PocetnaStranica> activityActivityTestRule = new ActivityTestRule<>(PocetnaStranica.class);

    @Before
    public void init(){
        activityActivityTestRule.getActivity()
                .getSupportFragmentManager().beginTransaction();
    }

    @Test
    public void buttonIsNotSelectable() {
        onView(withId(R.id. recyclerView)).check(matches(not(isSelected())));
    }

    @Test
    public void buttonClicked() {
        onView(withId(R.id.recyclerView)).check(matches((isDisplayed())));
        onView(withId(R.id.recyclerView))
                .perform(RecyclerViewActions.actionOnItem(
                        hasDescendant(withText("Varazdin")), longClick()));
    }

}
