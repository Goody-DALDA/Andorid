package com.goody.dalda

import com.goody.dalda.data.AlcoholInfo
import com.goody.dalda.data.AlcoholType
import com.goody.dalda.data.converter.DynamicConverterFactory
import com.goody.dalda.data.remote.home.AlcoholInfoRemoteDataSource
import com.goody.dalda.data.remote.home.AlcoholInfoRemoteDataSourceImpl
import com.goody.dalda.data.repository.home.AlcoholRepositoryImpl
import com.goody.dalda.network.RetrofitService
import com.goody.dalda.ui.category.CategoryViewModel
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
    private lateinit var alcoholInfoRemoteDataSource: AlcoholInfoRemoteDataSource
    private lateinit var alcoholRepositoryImpl: AlcoholRepositoryImpl
    private lateinit var viewModel: CategoryViewModel


    @Before
    fun init() {
        val baseUrl = server.url("")

        service = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(DynamicConverterFactory())
            .build()
            .create()

        alcoholInfoRemoteDataSource = AlcoholInfoRemoteDataSourceImpl(
            service = service
        )
        alcoholRepositoryImpl = AlcoholRepositoryImpl(
            alcoholInfoRemoteDataSource = alcoholInfoRemoteDataSource
        )
        viewModel = CategoryViewModel(
            alcoholRepository = alcoholRepositoryImpl
        )
    }

    @Test
    fun `Beer카테고리를_호출했을 때, DTO에서 Data Beer로 변환되어 반환한다`() {
        // given :  MockResponse을 활용해 서버 응답을 세팅해둔다.
        val response = MockResponse()
            .setBody(File("src/test/java/com/goody/dalda/resources/beerResponseData.json").readText())
            .setResponseCode(200)

        server.enqueue(response)

        // when : Beer 카테고리를 호출한다.
        runTest {
            viewModel.fetchAlcoholInfo("Beer")
        }
        val actual = viewModel.alcoholInfoList.value.subList(0, 4)

        // then : Beer 카테고리에 해당하는 AlcoholInfo 리스트가 반환된다.
        val expect = listOf(
            AlcoholInfo(
                id = 1,
                name = "카스 프레시",
                imgUrl = "https://user-images.githubusercontent.com/76477531/228704017-e8142b5c-3e5a-4175-a46a-a7b6866e4c54.png",
                type = AlcoholType.BEER,
                abv = "4.5%"
            ),
            AlcoholInfo(
                id = 2,
                name = "필라이트",
                imgUrl = "https://user-images.githubusercontent.com/76477531/228704047-8bff0794-6daf-4ecb-9439-34bcbcb3cd57.png",
                type = AlcoholType.BEER,
                abv = "4.5%"
            ),
            AlcoholInfo(
                id = 3,
                name = "하이트 디",
                imgUrl = "https://user-images.githubusercontent.com/76477531/228704055-c3569c21-0929-499c-8337-b3012143acf3.png",
                type = AlcoholType.BEER,
                abv = "4.8%"
            ),
            AlcoholInfo(
                id = 4,
                name = "하이트 엑스트라 콜드",
                imgUrl = "https://user-images.githubusercontent.com/76477531/228704058-772a263b-21bd-49e8-bf29-62c78dbe4464.png",
                type = AlcoholType.BEER,
                abv = "4.3%"
            )
        )

        assertThat(actual).isEqualTo(expect)
    }

    @Test
    fun `Wine카테고리를_호출했을 때, DTO에서 Data Wine로 변환되어 반환한다`() {
        // given :  MockResponse을 활용해 서버 응답을 세팅해둔다.
        val response = MockResponse()
            .setBody(File("src/test/java/com/goody/dalda/resources/wineResponseData.json").readText())
            .setResponseCode(200)

        server.enqueue(response)

        // when : Beer 카테고리를 호출한다.
        runTest {
            viewModel.fetchAlcoholInfo("Wine")
        }
        val actual = viewModel.alcoholInfoList.value.subList(0, 4)

        // then : Beer 카테고리에 해당하는 AlcoholInfo 리스트가 반환된다.
        val expect = listOf(
            AlcoholInfo(
                id = 1,
                name = "이기갈 꼬뜨 뒤 론 레드",
                imgUrl = "https://www.shinsegae-lnb.com/upload/product/wine/wine/images/W_005_E.GuigalCotesduRhoneRouge.jpg",
                type = AlcoholType.WINE,
                abv = "%"
            ),
            AlcoholInfo(
                id = 2,
                name = "이기갈 꼬뜨 뒤 론 화이트",
                imgUrl = "https://www.shinsegae-lnb.com/upload/product/wine/wine/images/W_006_E.GuigalCotesduRhoneBlanc.jpg",
                type = AlcoholType.WINE,
                abv = "%"
            ),
            AlcoholInfo(
                id = 3,
                name = "이기갈 꼬뜨 로띠 ‘브륀 & 블롱드’",
                imgUrl = "https://www.shinsegae-lnb.com/upload/product/wine/wine/images/W_007_E.GuigalCotRotieBrune&BlondedeGuigal.jpg",
                type = AlcoholType.WINE,
                abv = "%"
            ),
            AlcoholInfo(
                id = 4,
                name = "이기갈 꼬뜨 로띠 ‘샤또 당퓌’",
                imgUrl = "https://www.shinsegae-lnb.com/upload/product/wine/wine/images/W_008_E.GuigalCoteRotieChateaudAmpuis.jpg",
                type = AlcoholType.WINE,
                abv = "%"
            )
        )

        assertThat(actual).isEqualTo(expect)
    }

    @Test
    fun `Sake 카테고리를_호출했을 때, DTO에서 Data Sake로 변환에 에러가 없다`() {
        // given :  MockResponse을 활용해 서버 응답을 세팅해둔다.
        val response = MockResponse()
            .setBody(File("src/test/java/com/goody/dalda/resources/sakeResponseData.json").readText())
            .setResponseCode(200)

        server.enqueue(response)

        // when : Beer 카테고리를 호출한다.
        val actual = runCatching {
            runTest {
                viewModel.fetchAlcoholInfo("sake")
            }
        }
        // then : Beer 카테고리에 해당하는 AlcoholInfo 리스트가 반환된다.
        println(actual.exceptionOrNull())
        assertThat(actual.isSuccess).isEqualTo(true)
    }

    @Test
    fun `soju 카테고리를_호출했을 때, DTO에서 Data Soju 변환에 에러가 없다`() {
        // given :  MockResponse을 활용해 서버 응답을 세팅해둔다.
        val response = MockResponse()
            .setBody(File("src/test/java/com/goody/dalda/resources/sojuResponseData.json").readText())
            .setResponseCode(200)

        server.enqueue(response)

        // when : Beer 카테고리를 호출한다.
        val actual = runCatching {
            runTest {
                viewModel.fetchAlcoholInfo("soju")
            }
        }
        // then : Beer 카테고리에 해당하는 AlcoholInfo 리스트가 반환된다.
        println(actual.exceptionOrNull())
        assertThat(actual.isSuccess).isEqualTo(true)
    }

    @Test
    fun `전통주 카테고리를_호출했을 때, DTO에서 Data TraditionalLiquor로 변환에 에러가 없다`() {
        // given :  MockResponse을 활용해 서버 응답을 세팅해둔다.
        val response = MockResponse()
            .setBody(File("src/test/java/com/goody/dalda/resources/TraditionalLiquorResponseData.json").readText())
            .setResponseCode(200)

        server.enqueue(response)

        // when : Beer 카테고리를 호출한다.
        val actual = runCatching {
            runTest {
                viewModel.fetchAlcoholInfo("Traditional")
            }
        }
        // then : Beer 카테고리에 해당하는 AlcoholInfo 리스트가 반환된다.
        println(actual.exceptionOrNull())
        assertThat(actual.isSuccess).isEqualTo(true)
    }

    @Test
    fun `위스키 카테고리를_호출했을 때, DTO에서 Data Whisky로 변환에 에러가 없다`() {
        // given :  MockResponse을 활용해 서버 응답을 세팅해둔다.
        val response = MockResponse()
            .setBody(File("src/test/java/com/goody/dalda/resources/whiskyResponseData.json").readText())
            .setResponseCode(200)

        server.enqueue(response)

        // when : Beer 카테고리를 호출한다.
        val actual = runCatching {
            runTest {
                viewModel.fetchAlcoholInfo("wisky")
            }
        }
        // then : Beer 카테고리에 해당하는 AlcoholInfo 리스트가 반환된다.
        println(actual.exceptionOrNull())
        assertThat(actual.isSuccess).isEqualTo(true)
    }
}
