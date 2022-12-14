package com.fs.compose.login.domain

import com.fs.compose.cart.data.repository.CartRepository
import com.fs.compose.cart.domain.FormatDataCartUseCase
import com.fs.compose.login.data.repository.LoginRepository
import java.util.*
import javax.inject.Inject


/**
 *网域层负责封装复杂的业务逻辑，或者由多个 ViewModel 重复使用的简单业务逻辑。此层是可选的，因为并非所有应用都有这类需求。因此，您应仅在需要时使用该层，例如处理复杂逻辑或支持可重用性。

网域层具有以下优势：

避免代码重复。
改善使用网域层类的类的可读性。
改善应用的可测试性。
让您能够划分好职责，从而避免出现大型类。
为了使这些类保持简单轻量化，每个用例都应仅负责单个功能，且不应包含可变数据。您应在界面或数据层中处理可变的数据。
 *
 *
 */
class FormatDataUseCase @Inject constructor(
    private val loginRepository: LoginRepository,
    private val cartRepository: CartRepository,
    private val formatDataCartUseCase: FormatDataCartUseCase
) {

    /**
     * kotlin类默认含有invoke()方法，并且可以通过operator关键字重载，
     * 可以采用原始调用方式：class.invoke(···)；kotlin允许简易调用：class()
     */
    suspend operator fun invoke(date: Calendar): String {
        val name = loginRepository.getCreateAccount().data[0].first_name
        val email = cartRepository.getCart().data[0].email
        return "$name  $email"
    }
}