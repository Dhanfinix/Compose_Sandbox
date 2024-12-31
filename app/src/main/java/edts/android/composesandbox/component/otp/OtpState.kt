package edts.base.android.core_resource.component.otp

import edts.android.composesandbox.component.otp.OtpType

data class OtpState(
    val otpText: String = "",
    val otpType: OtpType = OtpType.NORMAL,
    val alertMsg: String? = null,
    val resendInterval: Int? = null, // dalam detik
    val isExpired: Boolean = false,
)
