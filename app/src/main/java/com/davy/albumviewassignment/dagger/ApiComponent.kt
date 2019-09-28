package com.davy.albumviewassignment.dagger

import com.davy.albumviewassignment.retrofit.AlbumService
import com.davy.albumviewassignment.viewmodel.AlbumListViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(service: AlbumService)

    fun inject(viewModel: AlbumListViewModel)
}
