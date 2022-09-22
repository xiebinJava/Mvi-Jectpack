package com.fs.compose.mvi.data.repository

import com.fs.compose.mvi.data.api.LoginApiService
import com.fs.compose.net.HttpRequest


/**
 * 存储库类负责以下任务：

向应用的其余部分公开数据。
集中处理数据变化。
解决多个数据源之间的冲突。
对应用其余部分的数据源进行抽象化处理。
包含业务逻辑。

每个数据源类应仅负责处理一个数据源，相应数据源可以是文件、网络来源或本地数据库。数据源类是应用与负责数据操作的系统之间的桥梁。

层次结构中的其他层绝不能直接访问数据源；数据层的入口点始终是存储库类。状态容器类或用例类
绝不能将数据源作为直接依赖项。如果使用存储库类作为入口点，架构的不同层便可以独立扩缩。

该层公开的数据应该是不可变的
 */

object LoginRepository {

    private val loginApiService: LoginApiService by lazy {
        HttpRequest.getService()
    }

    suspend fun getLogin() = loginApiService.getUsers()
    suspend fun getCreateAccount() = loginApiService.getCreateAccount()


}