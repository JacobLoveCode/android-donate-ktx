package cn.soldat.donate_ktx

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.service.quicksettings.TileService
import cn.soldat.donate_ktx.common.Constants
import java.lang.Exception
import java.net.URISyntaxException

object AlipayDonate {

    fun startAlipayClient(activity: Activity, payCode: String): Boolean {
        return startIntentUrl(
            activity,
            Constants.ALIPAY_INTENT_URL_FORMAT.replace("{payCode}", payCode)
        )
    }

    /**
     * 打开 Intent Scheme Url
     */
    fun startIntentUrl(activity: Activity, intentFullUrl: String): Boolean {
        return try {
            val intent = Intent.parseUri(intentFullUrl, Intent.URI_INTENT_SCHEME)
            activity.startActivity(intent)
            true
        } catch (e: URISyntaxException) {
            e.printStackTrace()
            false
        } catch (e: ActivityNotFoundException) {
            e.printStackTrace()
            false
        }
    }

    /**
     * 判断支付宝客户端是否已安装， 建议调用前检查
     */
    fun hasInstalledAlipayClient(context: Context): Boolean {
        val pm = context.packageManager
        return try {
            val info = pm.getPackageInfo(Constants.ALIPAY_PACKAGE_NAME, 0)
            info != null
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            false
        }
    }

    /**
     * 获取支付宝客户端版本名称
     */
    fun getAlipayClientVersion(context: Context): String? {
        val pm = context.packageManager
        return try {
            val info = pm.getPackageInfo(Constants.ALIPAY_PACKAGE_NAME, 0)
            info.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            null
        }
    }

    /**
     * 打开支付宝扫一扫界面
     */
    fun openAlipayScan(context: Context): Boolean {
        return try {
            val uri = Uri.parse("alipayqr://platformapi/startapp?saId=10000007")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            (context as TileService).startActivityAndCollapse(intent)
//            if (context is TileService){
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
//                    context.startActivityAndCollapse(intent)
//                }
//            }else{
//                context.startActivity(intent)
//            }
            true
        } catch (e: Exception) {
            false
        }
    }

    /**
     * 打开支付宝付款码
     */
    fun openAlipayBarCode(context: Context): Boolean{
        return try {
            val uri = Uri.parse("alipayqr://platformapi/startapp?saId=20000056")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            (context as TileService).startActivityAndCollapse(intent)
            true
        }catch (e: Exception){
            false
        }
    }
}