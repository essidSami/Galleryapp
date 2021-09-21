package com.sami.linxogalleryapp.network

import com.sami.linxogalleryapp.MainCoroutinesRule
import com.sami.linxogalleryapp.data.source.remote.GalleryApi
import com.sami.linxogalleryapp.data.source.remote.dto.*
import com.sami.linxogalleryapp.util.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

class GalleryApiTest : ApiAbstract<GalleryApi>() {

    private lateinit var api: GalleryApi

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesRule = MainCoroutinesRule()

    @Before
    fun initService() {
        api = createService(GalleryApi::class.java)
    }

    @Throws(IOException::class)
    @Test
    fun fetchAlbumListFromNetworkTest() = runBlocking {
        enqueueResponse("/AlbumsResponse.json")
        val response = api.getAlbums()
        val responseBody = requireNotNull(response)
        mockWebServer.takeRequest()

        MatcherAssert.assertThat(responseBody[0].title, CoreMatchers.`is`("quidem molestiae enim"))
        MatcherAssert.assertThat(responseBody[0].id, CoreMatchers.`is`(1))
        MatcherAssert.assertThat(responseBody[0].userId, CoreMatchers.`is`(1))
    }

    @Throws(IOException::class)
    @Test
    fun fetchUserListFromNetworkTest() = runBlocking {
        enqueueResponse("/UsersResponse.json")
        val response = api.getUsers()
        val responseBody = requireNotNull((response as List<UserDto>))
        mockWebServer.takeRequest()

        MatcherAssert.assertThat(responseBody[0].id, CoreMatchers.`is`(1))
        MatcherAssert.assertThat(responseBody[0].name, CoreMatchers.`is`("Leanne Graham"))
        MatcherAssert.assertThat(responseBody[0].username, CoreMatchers.`is`("Bret"))
        MatcherAssert.assertThat(responseBody[0].email, CoreMatchers.`is`("Sincere@april.biz"))
        MatcherAssert.assertThat(responseBody[0].phone, CoreMatchers.`is`("1-770-736-8031 x56442"))
        MatcherAssert.assertThat(responseBody[0].website, CoreMatchers.`is`("hildegard.org"))
        MatcherAssert.assertThat(
            responseBody[0].address, CoreMatchers.`is`(
                AddressDto(
                    city = "Gwenborough",
                    suite = "Apt. 556",
                    street = "Kulas Light",
                    zipcode = "92998-3874",
                    geo = GeoDto(
                        lat = "-37.3159",
                        lng = "81.1496"
                    )
                )
            )
        )
        MatcherAssert.assertThat(
            responseBody[0].company, CoreMatchers.`is`(
                CompanyDto(
                    name = "Romaguera-Crona",
                    catchPhrase = "Multi-layered client-server neural-net",
                    domaine = "harness real-time e-markets"
                )
            )
        )
    }

    @Throws(IOException::class)
    @Test
    fun fetchPhotoListFromNetworkTest() = runBlocking {
        enqueueResponse("/PhotosResponse.json")
        val response = api.getPhotoByAlbum(1)
        val responseBody = requireNotNull((response as List<PhotoDto>))
        mockWebServer.takeRequest()

        MatcherAssert.assertThat(responseBody[0].id, CoreMatchers.`is`(1))
        MatcherAssert.assertThat(responseBody[0].albumId, CoreMatchers.`is`(1))
        MatcherAssert.assertThat(responseBody[0].title, CoreMatchers.`is`("accusamus beatae ad facilis cum similique qui sunt"))
        MatcherAssert.assertThat(responseBody[0].url, CoreMatchers.`is`("https://via.placeholder.com/600/92c952"))
        MatcherAssert.assertThat(responseBody[0].thumbnailUrl, CoreMatchers.`is`("https://via.placeholder.com/150/92c952"))

    }
}