package com.top.compose.sample.data.repository

import androidx.paging.PagingData
import com.top.compose.sample.bean.Article
import com.top.compose.sample.bean.Banner
import kotlinx.coroutines.flow.Flow

interface WanAndroidRepository {


    fun articlePagingSource(): Flow<PagingData<Article>>


    fun article(): Flow<PagingData<Article>>

    fun banner(): Flow<List<Banner>>

    fun collect(id: Int): Flow<Boolean>

}