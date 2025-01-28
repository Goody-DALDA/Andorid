package com.goody.dalda

import com.goody.dalda.data.converter.DynamicConverterFactory
import com.goody.dalda.data.local.BookmarkLocalDataSource
import com.goody.dalda.data.remote.home.AlcoholDataRemoteDataSource
import com.goody.dalda.data.remote.home.AlcoholDataRemoteDataSourceImpl
import com.goody.dalda.data.repository.alcohol.AlcoholRepositoryImpl
import com.goody.dalda.network.RetrofitService
import com.goody.dalda.ui.category.CategoryViewModel
import com.goody.dalda.ui.home.FakeBookmarkLocalDataSourceImpl
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.create
import java.io.File

class CategoryViewModelTest {
    private val server = MockWebServer()
    private lateinit var service: RetrofitService
    private lateinit var alcoholDataRemoteDataSource: AlcoholDataRemoteDataSource
    private lateinit var bookmarkLocalDataSource: BookmarkLocalDataSource
    private lateinit var alcoholRepositoryImpl: AlcoholRepositoryImpl
    private lateinit var viewModel: CategoryViewModel

    @Before
    fun init() {
        val baseUrl = server.url("")

        service =
            Retrofit
                .Builder()
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
            CategoryViewModel(
                alcoholRepository = alcoholRepositoryImpl,
            )
    }

    @Test
    fun `Beer카테고리를_호출했을 때, DTO에서 Data Beer로 변환되어 반환한다`() {
        // given :  MockResponse을 활용해 서버 응답을 세팅해둔다.
        val response =
            MockResponse()
                .setBody(File("src/test/java/com/goody/dalda/resources/beerResponseData.json").readText())
                .setResponseCode(200)

        server.enqueue(response)

        // when : Beer 카테고리를 호출한다.
        val actual =
            runCatching {
                runTest {
                    viewModel.fetchAlcoholData("Beer")
                }
            }

        // then : 오류가 없다.
        assertThat(actual.isSuccess).isEqualTo(true)
    }

    @Test
    fun `Wine카테고리를_호출했을 때, DTO에서 Data Wine로 변환되어 반환한다`() {
        // given :  MockResponse을 활용해 서버 응답을 세팅해둔다.
        val response =
            MockResponse()
                .setBody(File("src/test/java/com/goody/dalda/resources/wineResponseData.json").readText())
                .setResponseCode(200)

        server.enqueue(response)

        // when : Beer 카테고리를 호출한다.
        val actual =
            runCatching {
                runTest {
                    viewModel.fetchAlcoholData("Wine")
                }
            }

        // then : 오류가 없다.
        assertThat(actual.isSuccess).isEqualTo(true)
    }

    @Test
    fun `Sake 카테고리를_호출했을 때, DTO에서 Data Sake로 변환에 에러가 없다`() {
        // given :  MockResponse을 활용해 서버 응답을 세팅해둔다.
        val response =
            MockResponse()
                .setBody(File("src/test/java/com/goody/dalda/resources/sakeResponseData.json").readText())
                .setResponseCode(200)

        server.enqueue(response)

        // when : Beer 카테고리를 호출한다.
        val actual =
            runCatching {
                runTest {
                    viewModel.fetchAlcoholData("sake")
                }
            }
        // then : 오류가 없다.
        assertThat(actual.isSuccess).isEqualTo(true)
    }

    @Test
    fun `soju 카테고리를_호출했을 때, DTO에서 Data Soju 변환에 에러가 없다`() {
        // given :  MockResponse을 활용해 서버 응답을 세팅해둔다.
        val response =
            MockResponse()
                .setBody(File("src/test/java/com/goody/dalda/resources/sojuResponseData.json").readText())
                .setResponseCode(200)

        server.enqueue(response)

        // when : Beer 카테고리를 호출한다.
        val actual =
            runCatching {
                runTest {
                    viewModel.fetchAlcoholData("soju")
                }
            }
        // then : 오류가 없다.
        assertThat(actual.isSuccess).isEqualTo(true)
    }

    @Test
    fun `전통주 카테고리를_호출했을 때, DTO에서 Data TraditionalLiquor로 변환에 에러가 없다`() {
        // given :  MockResponse을 활용해 서버 응답을 세팅해둔다.
        val response =
            MockResponse()
                .setBody(File("src/test/java/com/goody/dalda/resources/TraditionalLiquorResponseData.json").readText())
                .setResponseCode(200)

        server.enqueue(response)

        // when : Beer 카테고리를 호출한다.
        val actual =
            runCatching {
                runTest {
                    viewModel.fetchAlcoholData("TraditionalLiquor")
                }
            }
        // then : 오류가 없다.
        assertThat(actual.isSuccess).isEqualTo(true)
    }

    @Test
    fun `위스키 카테고리를_호출했을 때, DTO에서 Data Whisky로 변환에 에러가 없다`() {
        // given :  MockResponse을 활용해 서버 응답을 세팅해둔다.
        val response =
            MockResponse()
                .setBody(File("src/test/java/com/goody/dalda/resources/whiskyResponseData.json").readText())
                .setResponseCode(200)

        server.enqueue(response)

        // when : Beer 카테고리를 호출한다.
        val actual =
            runCatching {
                runTest {
                    viewModel.fetchAlcoholData("wisky")
                }
            }
        // then : 오류가 없다.
        assertThat(actual.isSuccess).isEqualTo(true)
    }
}
