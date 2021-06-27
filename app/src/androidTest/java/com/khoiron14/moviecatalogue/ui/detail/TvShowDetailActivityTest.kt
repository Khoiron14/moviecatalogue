package com.khoiron14.moviecatalogue.ui.detail

import android.content.Intent
import androidx.test.espresso.Espresso.*
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
class TvShowDetailActivityTest {

    private val dummyTvShow = DataDummy().generateDummyTvShow()[0]

    @get:Rule
    var activityRule: ActivityTestRule<TvShowDetailActivity> =
        object : ActivityTestRule<TvShowDetailActivity>(TvShowDetailActivity::class.java) {
            override fun getActivityIntent(): Intent {
                val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
                val result = Intent(targetContext, TvShowDetailActivity::class.java)
                result.putExtra(TvShowDetailActivity.EXTRA_TVSHOW, dummyTvShow.id)
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
    fun loadTvShow() {
        onView(withId(R.id.tv_name)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_name)).check(matches(withText(dummyTvShow.name)))
        onView(withId(R.id.tv_first_air)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_first_air)).check(matches(withText(convertDate(dummyTvShow.firstAirDate))))
        onView(withId(R.id.tv_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview)).check(matches(withText(dummyTvShow.overview)))
    }
}