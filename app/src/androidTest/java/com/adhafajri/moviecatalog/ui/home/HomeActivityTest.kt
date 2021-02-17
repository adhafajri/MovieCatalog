package com.adhafajri.moviecatalog.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.adhafajri.moviecatalog.R
import com.adhafajri.moviecatalog.utils.Constant
import com.adhafajri.moviecatalog.utils.Data
import org.junit.Before
import org.junit.Test

class HomeActivityTest {
    private val dummyMovieCatalog = Data.generateCatalogs(Constant.MOVIES)
    private val dummyShowCatalog = Data.generateCatalogs(Constant.SHOWS)

    @Before
    fun setup() {
        ActivityScenario.launch(HomeActivity::class.java)
    }

    @Test
    fun loadMovieCatalogs() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyMovieCatalog.size
            )
        )
    }

    @Test
    fun loadShowCatalogs() {
        onView(withText("SHOWS")).perform(click())
        onView(withId(R.id.rv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_show)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyShowCatalog.size
            )
        )
    }

    @Test
    fun loadDetailMovieCatalog() {
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )

        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title)).check(matches(withText(dummyMovieCatalog[0].title)))

        onView(withId(R.id.tv_year)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_year)).check(matches(withText(dummyMovieCatalog[0].year)))

        onView(withId(R.id.img_poster)).check(matches(isDisplayed()))

        onView(withId(R.id.tv_synopsis_text)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_synopsis_text)).check(matches(withText(dummyMovieCatalog[0].synopsis)))

        onView(withId(R.id.scrollView)).perform(swipeUp())

        onView(withId(R.id.tv_directors_text)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_directors_text)).check(matches(withText(dummyMovieCatalog[0].directors)))

        onView(withId(R.id.tv_writers_text)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_writers_text)).check(matches(withText(dummyMovieCatalog[0].writers)))

        onView(withId(R.id.tv_stars_text)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_stars_text)).check(matches(withText(dummyMovieCatalog[0].stars)))

        onView(withId(R.id.btn_trailer)).perform(click())
    }
}