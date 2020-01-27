package com.graphtech.l_footballsub2

import android.widget.SearchView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.graphtech.l_footballsub2.activity.SearchResultActivity
import kotlinx.coroutines.delay
import org.jetbrains.anko.Android
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SearchResultActivityTest {
    @Rule
    @JvmField var activityRule = ActivityTestRule(SearchResultActivity::class.java)

    @Test
    fun testSearchBehavior() {
        Espresso.onView(ViewMatchers.withId(R.id.searchMatch)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        delay(2)
        Espresso.onView(ViewMatchers.withId(R.id.searchMatch)).perform(ViewActions.typeText("Ars"))
        //tahan 1 detik
        delay(2)
        //lalu setelah delay 1 detik akan dilanjutkan dengan "enal"
        Espresso.onView(ViewMatchers.withId(R.id.searchMatch)).perform(ViewActions.typeText("enal"))
        delay(2)

        //memastikan rv result
        Espresso.onView(ViewMatchers.withId(R.id.rvResult)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        delay(2)

        //scroll rv ke list 6 lalu ke 1
        Espresso.onView(ViewMatchers.withId(R.id.rvResult))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(6))
        delay(2)

        Espresso.onView(ViewMatchers.withId(R.id.rvResult))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(1))
        delay(2)

        Espresso.onView(ViewMatchers.withId(R.id.rvResult))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, ViewActions.click()))
        delay(3)

        Espresso.pressBack()
        delay(3)

    }

    private fun delay(second: Long = 1) {
        Thread.sleep(1000 * second)
    }
}