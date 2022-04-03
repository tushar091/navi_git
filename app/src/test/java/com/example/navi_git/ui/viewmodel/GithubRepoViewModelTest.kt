package com.example.navi_git.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.navi_git.models.GithubPullRequest
import com.example.navi_git.network.GithubPRNetworkRepository
import com.example.navi_git.repository.GitPullRequestListRepository
import com.example.navi_git.repository.PagedDataSourceFactory
import com.google.gson.Gson
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

class GithubRepoViewModelTest {

    val dispatcher = TestCoroutineDispatcher()
    private val networkRepo = mockk<GithubPRNetworkRepository>(relaxed = true)
    private val gson = Gson()
    private lateinit var listRepo: GitPullRequestListRepository
    private lateinit var githubRepoFactory: PagedDataSourceFactory<Int, GithubPullRequest>
    private lateinit var githubViewModel: GithubRepoViewModel

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        listRepo = GitPullRequestListRepository(networkRepo, gson)
        githubRepoFactory = PagedDataSourceFactory(listRepo)
        githubViewModel = GithubRepoViewModel(githubRepoFactory)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test init`() = runBlockingTest {
        val listItem1 = mockk<GithubPullRequest>(relaxed = true)
        val listItem2 = mockk<GithubPullRequest>(relaxed = true)
        val listItem3 = mockk<GithubPullRequest>(relaxed = true)
        val responseBody = listOf(listItem1,listItem2,listItem3)
        val response = mockk<Response<List<GithubPullRequest>>>()
        every { response.body() } returns responseBody
        every { response.isSuccessful } returns true
        coEvery { networkRepo.getClosedPullRequests(any()) } returns response
        githubViewModel.fetchData()
        advanceUntilIdle()
        assert(githubViewModel.prData.getOrAwaitValue().size == 3)
    }
}

fun <T> LiveData<T>.getOrAwaitValue(
    time: Long = 5,
    delay:Long = 1000,
    timeUnit: TimeUnit = TimeUnit.SECONDS
): T {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = Observer<T> { o ->
        runBlocking {
            delay(delay)
            data = o
            latch.countDown()
        }
    }

    this.observeForever(observer)

    // Don't wait indefinitely if the LiveData is not set.
    if (!latch.await(time, timeUnit)) {
        throw TimeoutException("LiveData value was never set.")
    }

    @Suppress("UNCHECKED_CAST")
    return data as T
}