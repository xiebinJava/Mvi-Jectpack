package com.fs.compose.login.data.repository

import com.fs.compose.login.data.model.User
import com.fs.compose.login.ui.LoginActivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import okhttp3.Dispatcher
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class LoginRepository @Inject constructor(
    private val loginRemoteDataSource: LoginRemoteDataSource,
//     private val externalScope: CoroutineScope
) {


//    constructor(loginRemoteDataSource,CoroutineScope(Dispatchers.Default))

    /**
     * 携程作用域
     *  Dispatchers.Main：Android 主线程。用于调用 suspend 函数，UI 框架操作，及更新 LiveData 对象。
    Dispatchers.IO：非主线程。用于磁盘操作（例如，Room 操作），及网络 I/O 请求（例如，调用服务器 API）。
    Dispatchers.Default：非主线程，用于 CPU 密集型操作。例如，list 排序，及 JSON 解析。
     *
     */
    private val  externalScope: CoroutineScope  by lazy {
        CoroutineScope(Dispatchers.Default)
    }
    private val latestNewsMutex = Mutex()
    private var userBean: User? = null

    suspend fun getLogin(): User {
        externalScope.async {
            loginRemoteDataSource.getLogin().also {
                // Thread-safe write to userBean
                latestNewsMutex.withLock {
                    userBean = it
                }
            }

        }
        return loginRemoteDataSource.getLogin()
    }

    suspend fun getCreateAccount(): User {
        return loginRemoteDataSource.getCreateAccount()
    }
}