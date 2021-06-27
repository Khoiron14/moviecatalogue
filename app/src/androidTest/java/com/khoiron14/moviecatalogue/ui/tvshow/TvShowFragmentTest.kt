package com.khoiron14.moviecatalogue.ui.tvshow

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.khoiron14.moviecatalogue.R
import com.khoiron14.moviecatalogue.testing.SingleFragmentActivity
import com.khoiron14.moviecatalogue.utils.EspressoIdlingResource
import com.khoiron14.moviecatalogue.utils.RecyclerViewItemCountAssertion
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by khoiron14 on 11/18/2019.
 */
class TvShowFragmentTest {

    @get:Rule
    var activityRule = ActivityTestRule(SingleFragmentActivity::class.java)
    private val tvShowFragment = TvShowFragment()

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource())
        activityRule.activity.setFragment(tvShowFragment)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @Test
    fun loadTvShows() {
        onView(withId(R.id.rv_list_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_list_tvshow)).check(RecyclerViewItemCountAssertion(10))
    }
}