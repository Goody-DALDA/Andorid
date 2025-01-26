package com.goody.dalda

import com.goody.dalda.data.converter.DynamicConverterFactory
import com.goody.dalda.data.local.BookmarkLocalDataSource
import com.goody.dalda.data.remote.home.AlcoholDataRemoteDataSource
import com.goody.dalda.data.remote.home.AlcoholDataRemoteDataSourceImpl
import com.goody.dalda.data.repository.home.AlcoholRepositoryImpl
import com.goody.dalda.data.repository.search.SearchRepositoryImpl
import com.goody.dalda.network.RetrofitService
import com.goody.dalda.ui.home.FakeBookmarkLocalDataSourceImpl
import com.goody.dalda.ui.search.SearchViewModel
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.create
import java.io.File

class SearchViewModelTest {
    private val server = MockWebServer()
    private lateinit var service: RetrofitService
    private lateinit var alcoholDataRemoteDataSource: AlcoholDataRemoteDataSource
    private lateinit var bookmarkLocalDataSource: BookmarkLocalDataSource
    private lateinit var alcoholRepositoryImpl: AlcoholRepositoryImpl
    private lateinit var searchRepositoryImpl: SearchRepositoryImpl
    private lateinit var viewModel: SearchViewModel

    @Before
    fun init() {
        val baseUrl = server.url("")

        service =
            Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(DynamicConverterFactory())
                .build()
                .create()

        alcoholDataRemoteDataSource =
            AlcoholDataRemoteDataSourceImpl(
                service = service,
            )
        bookmarkLocalDataSource = FakeBookmarkLocalDataSourceImpl()
        alcoholRepositoryImpl =
            AlcoholRepositoryImpl(
                alcoholDataRemoteDataSource = alcoholDataRemoteDataSource,
                bookmarkLocalDataSource = bookmarkLocalDataSource,
            )
        viewModel =
            SearchViewModel(
                alcoholRepository = alcoholRepositoryImpl,
                searchRepository = searchRepositoryImpl,
            )
    }

    @Test
    fun `검색 결과 출력 API 테스트`() {
        // given : MockResponse을 활용해 서버 응답을 세팅해둔다.
        val response =
            MockResponse()
                .setBody(File("src/test/java/com/goody/dalda/resources/소searchResponseData.json").readText())
                .setResponseCode(200)

        server.enqueue(response)

        // when :
        val actual =
            runCatching {
                runTest {
                    viewModel.searchAlcoholData("소")
                }
            }

        // then :
        assertThat(actual.isSuccess).isTrue()
    }
}
