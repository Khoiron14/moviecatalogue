package com.khoiron14.moviecatalogue.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.khoiron14.moviecatalogue.R
import com.khoiron14.moviecatalogue.utils.DataDummy
import com.khoiron14.moviecatalogue.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


/**
 * Created by khoiron14 on 11/18/2019.
 */
class MovieCatalogueTest {

    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @Test
    fun toMovieDetailActivityTest() {
        onView(withId(R.id.rv_list_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_list_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
                ViewActions.click()
            )
        )

        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title)).check(matches(withText(DataDummy().generateDummyMovie()[0].title)))
        pressBack()
    }

    @Test
    fun toTvShowDetailActivityTest() {
        onView(withId(R.id.navigation_tvshow)).perform(ViewActions.click())

        onView(withId(R.id.rv_list_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_list_tvshow)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
                ViewActions.click()
            )
        )

        onView(withId(R.id.tv_name)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_name)).check(matches(withText(DataDummy().generateDummyTvShow()[0].name)))
    }
}