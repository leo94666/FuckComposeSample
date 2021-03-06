package com.top.compose.sample.data.repository

import com.top.compose.sample.bean.User
import com.top.compose.sample.domain.TResponse

interface AccountRepository {

    suspend fun login(username: String, password: String): TResponse<User>

    suspend fun register(username: String, password: String, repassword: String): TResponse<User>

}