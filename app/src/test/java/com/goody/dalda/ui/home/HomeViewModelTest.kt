package com.goody.dalda.ui.home

import com.goody.dalda.data.converter.DynamicConverterFactory
import com.goody.dalda.data.local.BookmarkLocalDataSource
import com.goody.dalda.data.remote.UserRemoteDataSource
import com.goody.dalda.data.remote.UserRemoteDataSourceImpl
import com.goody.dalda.data.remote.home.AlcoholDataRemoteDataSource
import com.goody.dalda.data.repository.LoginRepository
import com.goody.dalda.data.repository.alcohol.AlcoholRepository
import com.goody.dalda.network.RetrofitService
import com.goody.dalda.repository.FakeAlcoholDataRemoteDataSourceImpl
import com.goody.dalda.repository.FakeAlcoholRepositoryImpl
import com.goody.dalda.repository.FakeBookmarkLocalDataSource
import com.goody.dalda.repository.FakeLoginRepositoryImpl
import com.goody.dalda.ui.home.data.UserProfile
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.create
import java.io.File

class HomeViewModelTest {
    private val server = MockWebServer()
    private lateinit var service: RetrofitService

    private lateinit var alcoholRepository: AlcoholRepository
    private lateinit var alcoholDataRemoteDataSource: AlcoholDataRemoteDataSource
    private lateinit var bookmarkLocalDataSource: BookmarkLocalDataSource
    private lateinit var loginRepository: LoginRepository
    private lateinit var userRemoteDataSource: UserRemoteDataSource

    private lateinit var viewModel: HomeViewModel

    @Before
    fun init() {
        val baseUrl = server.url("")

        service = Retrofit
            .Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(DynamicConverterFactory())
            .build()
            .create()

        // 주류 Romote 데이터 소스
        alcoholDataRemoteDataSource = FakeAlcoholDataRemoteDataSourceImpl(
            service = service
        )

        // 북마크 로컬 데이터 소스
        bookmarkLocalDataSource = FakeBookmarkLocalDataSource()

        // 주류 데이터 Repository
        alcoholRepository = FakeAlcoholRepositoryImpl(
            alcoholDataRemoteDataSource = alcoholDataRemoteDataSource,
            bookmarkLocalDataSource = bookmarkLocalDataSource
        )

        // 유저 데이터 Romote 데이터 소스
        userRemoteDataSource = UserRemoteDataSourceImpl(
            service = service
        )

        // 유저 데이터 Repository
        loginRepository = FakeLoginRepositoryImpl(
            userRemoteDataSource = userRemoteDataSource
        )

        viewModel = HomeViewModel(
            alcoholRepository = alcoholRepository,
            profileRepository = loginRepository
        )
    }

    /**
     * fetchProfile()
     * profileRepository.getProfile()를 호출
     * 성공시 _userProfile에 저장
     * 실패시 _homeUiState에 ErrorState 저장
     */
    @Test
    fun `fetchProfile()_정상_테스트`() {
        runCatching {
            runTest {
                // Given : 응답 데이터를 세팅한다.
                val response =
                    MockResponse()
                        .setBody(File("src/test/java/com/goody/dalda/resources/profileResponseData.json").readText())
                        .setResponseCode(200)
                server.enqueue(response)

                // When : fetchProfile()를 실행한다.
                viewModel.fetchProfile()

                val expect = UserProfile(
                    name = "오영재",
                    email = "ho7677@daum.net",
                    img = "https://k.kakaocdn.net/dn/ZJMs4/btsGdZ1uK3a/txQWQK1vLvvZqAZS6D9OlK/img_110x110.jpg"

                )
                // Then : _userProfile에 UserProfile이 저장된다.
                assertThat(viewModel.userProfile.value).isEqualTo(expect)
            }
        }
    }

    /**
     * fetchBookmarkAlcoholList()
     * alcoholRepository.getBookmarkAlcoholList()를 호출
     * 성공시 _bookmarkAlcoholDataList.value에 저장
     * 실패시 _homeUiState에 ErrorState 저장
     * */


}