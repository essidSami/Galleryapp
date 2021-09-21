package com.sami.linxogalleryapp.utils

import com.sami.linxogalleryapp.data.source.remote.dto.*

object MockUtil {

    private fun mockPhoto() = PhotoDto(
        albumId = 1,
        id = 1,
        title = "accusamus beatae ad facilis cum similique qui sunt",
        url = "https://via.placeholder.com/600/92c952",
        thumbnailUrl = "https://via.placeholder.com/150/92c952"
    )

    fun mockPhotoList() = listOf(mockPhoto())


    private fun mockUser() = UserDto(
        id = 1,
        name = "Leanne Graham",
        username = "Bret",
        email = "Sincere@april.biz",
        phone = "1-770-736-8031 x56442",
        website = "hildegard.org",
        address = AddressDto(
            city = "Gwenborough",
            suite = "Apt. 556",
            street = "Kulas Light",
            zipcode = "92998-3874",
            geo = GeoDto(
                lat = "-37.3159",
                lng = "81.1496"
            )
        ),
        company = CompanyDto(
            name = "Romaguera-Crona",
            catchPhrase = "Multi-layered client-server neural-net",
            domaine = "harness real-time e-markets"
        )
    )

    fun mockUserList() = listOf(mockUser())

    private fun mockAlbum() = AlbumDto(
        id = 1,
        title = "quidem molestiae enim",
        userId = 1
    )

    fun mockAlbumList() = listOf(mockAlbum())
}