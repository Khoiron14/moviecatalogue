package com.khoiron14.moviecatalogue.ui.detail

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.khoiron14.moviecatalogue.R
import com.khoiron14.moviecatalogue.utils.DataDummy
import com.khoiron14.moviecatalogue.utils.EspressoIdlingResource
import com.khoiron14.moviecatalogue.utils.convertDate
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by khoiron14 on 11/18/2019.
 */
class MovieDetailActivityTest {

    private val dummyMovie = DataDummy().generateDummyMovie()[0]

    @get:Rule
    var activityRule: ActivityTestRule<MovieDetailActivity> =
        object : ActivityTestRule<MovieDetailActivity>(MovieDetailActivity::class.java) {
            override fun getActivityIntent(): Intent {
                val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
                val result = Intent(targetContext, MovieDetailActivity::class.java)
                result.putExtra(MovieDetailActivity.EXTRA_MOVIE, dummyMovie.id)
                return result
            }
        }

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @Test
    fun loadMovie() {
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title)).check(matches(withText(dummyMovie.title)))
        onView(withId(R.id.tv_release)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_release)).check(matches(withText(convertDate(dummyMovie.releaseDate))))
        onView(withId(R.id.tv_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview)).check(matches(withText(dummyMovie.overview)))
    }
}