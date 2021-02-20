package com.adhafajri.moviecatalog.ui.home

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.adhafajri.moviecatalog.R
import com.adhafajri.moviecatalog.data.CatalogEntity
import com.adhafajri.moviecatalog.utils.Constant
import com.adhafajri.moviecatalog.utils.Data
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Before
import org.junit.Test

class HomeActivityTest {
    private val dummyCatalog = Data.generateCatalogs()
    private val dummyPersons = Data.generatePersons()
    private val dummyTitles = Data.generateTitles()

    @Before
    fun setup() {
        ActivityScenario.launch(HomeActivity::class.java)
    }

    @Test
    fun loadMovieCatalogs() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyCatalog.size
            )
        )
    }

    @Test
    fun loadTvShowCatalogs() {
        onView(withText(R.string.tv_shows)).perform(click())
        onView(withId(R.id.rv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_show)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyCatalog.size
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

        val dummyMovieCatalog = ArrayList<CatalogEntity>()
        for (catalog in dummyCatalog) {
            if (catalog.type == Constant.MOVIE) {
                dummyMovieCatalog.add(catalog)
            }
        }

        onView(withId(R.id.tv_catalog_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_catalog_title)).check(matches(withText(dummyMovieCatalog[0].title)))

        onView(withId(R.id.tv_year)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_year)).check(matches(withText(dummyMovieCatalog[0].year)))

        onView(withId(R.id.img_poster)).check(matches(isDisplayed()))

        onView(withId(R.id.tv_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview_text)).check(matches(withText(dummyMovieCatalog[0].overview)))

        onView(withId(R.id.scrollView)).perform(swipeUp())

        onView(withId(R.id.rv_person)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_person)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyPersons.size
            )
        )

        onView(withIndex(withId(R.id.rv_title), 0)).check(matches(isDisplayed()))
        onView(withIndex(withId(R.id.rv_title), 0)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyTitles.size
            )
        )

        onView(withId(R.id.btn_trailer)).perform(click())
    }

    @Test
    fun loadDetailTvShowCatalog() {
        onView(withText(R.string.tv_shows)).perform(click())
        onView(withId(R.id.rv_show)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )

        val dummyTvShowCatalog = ArrayList<CatalogEntity>()
        for (catalog in dummyCatalog) {
            if (catalog.type == Constant.TV_SHOW) {
                dummyTvShowCatalog.add(catalog)
            }
        }

        onView(withId(R.id.tv_catalog_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_catalog_title)).check(matches(withText(dummyTvShowCatalog[0].title)))

        onView(withId(R.id.tv_year)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_year)).check(matches(withText(dummyTvShowCatalog[0].year)))

        onView(withId(R.id.img_poster)).check(matches(isDisplayed()))

        onView(withId(R.id.tv_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview_text)).check(matches(withText(dummyTvShowCatalog[0].overview)))

        onView(withId(R.id.scrollView)).perform(swipeUp())

        onView(withId(R.id.rv_person)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_person)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyPersons.size
            )
        )

        onView(withIndex(withId(R.id.rv_title), 0)).check(matches(isDisplayed()))
        onView(withIndex(withId(R.id.rv_title), 0)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyTitles.size
            )
        )

        onView(withId(R.id.btn_trailer)).perform(click())
    }

    private fun withIndex(matcher: Matcher<View?>, index: Int): Matcher<View?> {
        return object : TypeSafeMatcher<View?>() {
            var currentIndex = 0
            override fun describeTo(description: Description) {
                description.appendText("with index: ")
                description.appendValue(index)
                matcher.describeTo(description)
            }

            override fun matchesSafely(view: View?): Boolean {
                return matcher.matches(view) && currentIndex++ == index
            }
        }
    }
}