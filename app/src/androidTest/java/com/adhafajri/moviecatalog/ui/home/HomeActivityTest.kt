package com.adhafajri.moviecatalog.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.adhafajri.moviecatalog.R
import com.adhafajri.moviecatalog.data.source.local.entity.CatalogEntity
import com.adhafajri.moviecatalog.utils.Constant
import com.adhafajri.moviecatalog.utils.api.APIClient
import com.adhafajri.moviecatalog.utils.api.APIHelper
import com.adhafajri.moviecatalog.utils.api.APIInterface
import org.junit.Before
import org.junit.Test

class HomeActivityTest {
    private lateinit var apiHelper: APIHelper

    @Before
    fun setup() {
        apiHelper = APIHelper(APIClient().getClient().create(
            APIInterface::class.java))
        ActivityScenario.launch(HomeActivity::class.java)
    }

    @Test
    fun loadMovieCatalogs() {
        val popularMovies = apiHelper.getPopularMovies()
        onView(withId(R.id.rv_movie_popular)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie_popular)).perform(
            popularMovies?.let {
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                    it.size
                )
            }
        )

        val upcomingMovies = apiHelper.getUpcomingMovies()
        onView(withId(R.id.rv_movie_upcoming)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie_upcoming)).perform(
            upcomingMovies?.let {
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                    it.size
                )
            }
        )
    }

    @Test
    fun loadTvShowCatalogs() {
        val popularTvShows = apiHelper.getPopularTvShows()
        onView(withText(R.string.tv_shows)).perform(click())
        onView(withId(R.id.rv_tv_show_popular)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show_popular)).perform(
            popularTvShows?.let {
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                    it.size
                )
            }
        )

        val todayAiringTvShows = apiHelper.getTodayAiringTvShows()
        onView(withId(R.id.rv_tv_show_airing)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show_airing)).perform(
            todayAiringTvShows?.let {
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                    it.size
                )
            }
        )
    }

    @Test
    fun loadDetailMovieCatalog() {
        val movies = apiHelper.getPopularMovies()
        onView(withId(R.id.rv_movie_popular)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )

        val dummyMovieCatalog = ArrayList<CatalogEntity>()
        movies?.forEach {
            with(it) {
                dummyMovieCatalog.add(
                    CatalogEntity(
                        id,
                        Constant.MOVIE,
                        title,
                        posterPath,
                        overview
                    )
                )
            }
        }
        val moviePerson = apiHelper.getMovieCredits(dummyMovieCatalog.first().catalogId)

        onView(withId(R.id.tv_catalog_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_catalog_title)).check(matches(withText(dummyMovieCatalog.first().title)))

        onView(withId(R.id.img_poster)).check(matches(isDisplayed()))

        onView(withId(R.id.tv_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview_text)).check(matches(withText(dummyMovieCatalog.first().overview)))

        onView(withId(R.id.scrollView)).perform(swipeUp())

        onView(withId(R.id.rv_person)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_person)).perform(
            moviePerson?.let {
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                    it.size
                )
            }
        )

        onView(withId(R.id.scrollView)).perform(swipeDown())
        onView(withId(R.id.btn_trailer)).perform(click())
    }

    @Test
    fun loadDetailTvShowCatalog() {
        val tvShows = apiHelper.getPopularTvShows()
        onView(withText(R.string.tv_shows)).perform(click())
        onView(withId(R.id.rv_tv_show_popular)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )

        val dummyTvShowCatalog = ArrayList<CatalogEntity>()
        tvShows?.forEach {
            with(it) {
                dummyTvShowCatalog.add(
                    CatalogEntity(
                        id,
                        Constant.MOVIE,
                        name,
                        posterPath,
                        overview
                    )
                )
            }
        }
        val tvShowPerson = apiHelper.getMovieCredits(dummyTvShowCatalog.first().catalogId)

        onView(withId(R.id.tv_catalog_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_catalog_title)).check(matches(withText(dummyTvShowCatalog.first().title)))

        onView(withId(R.id.img_poster)).check(matches(isDisplayed()))

        onView(withId(R.id.tv_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview_text)).check(matches(withText(dummyTvShowCatalog.first().overview)))

        onView(withId(R.id.scrollView)).perform(swipeUp())

        onView(withId(R.id.rv_person)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_person)).perform(
            tvShowPerson?.let {
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                    it.size
                )
            }
        )

        onView(withId(R.id.scrollView)).perform(swipeDown())
        onView(withId(R.id.btn_trailer)).perform(click())
    }
}