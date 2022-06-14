package cn.soldat.donate_ktx.common

object Constants {
    // 支付宝包名
    const val ALIPAY_PACKAGE_NAME = "com.eg.android.AlipayGphone"

    // 旧版支付宝二维码通用 Intent Scheme URL 格式
    const val ALIPAY_INTENT_URL_FORMAT = "intent://platformapi/startapp?saId=10000007&" +
            "clientVersion=3.7.0.0718&qrcode=https%3A%2F%2Fqr.alipay.com%2F{payCode}%3F_s" +
            "%3Dweb-other&_t=1472443966571#Intent;" +
            "scheme=alipayqr;package=com.eg.android.AlipayGphone;end"
}