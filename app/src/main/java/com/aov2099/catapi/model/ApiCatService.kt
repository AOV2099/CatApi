package com.aov2099.catapi.model

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiCatService {

    @GET
    suspend fun getCats( @Url URL: String ): Response< List<CatResponse> >

}