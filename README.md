# Galleryapp
<p align="center">  
 Galleryapp is a small demo application based on modern Android application tech-stacks and clean architecture.
This project is for focusing especially on the new library Hilt of implementing dependency injection.
Also fetching data from the network.
 </p>
 <P>🕛 I spent 12 hours developing this application. </p>

<p align="center">
<img src="/previews/screenshot.png"/>
</p>

## Tech stack & Open-source libraries
- Minimum SDK level 23
- - [Kotlin](https://kotlinlang.org/) based, [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous.
- [Hilt](https://dagger.dev/hilt/) for dependency injection.
- JetPack
    - Lifecycle - dispose of observing data when lifecycle state changes.
    - ViewModel - UI related data holder, lifecycle aware.
- Architecture
    - MVVM Architecture (View - View Binding - ViewModel - Model)
    - [View Binding](https://developer.android.com/topic/libraries/view-binding) - Android DataBinding kit for notifying data changes to UI layers.
    - Repository pattern
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit) - construct the REST APIs and paging network data.
- [Picasso](https://square.github.io/picasso/) - loading images.

## Architecture
GalleryApp is based on clean architecture and a repository pattern.
![architecture](https://miro.medium.com/max/875/1*wOmAHDN_zKZJns9YDjtrMw.jpeg)

## Improvements
- Working with Jetpack Compose for UI.
- Create Room database for offline mode.
- Implementation UnitTest.