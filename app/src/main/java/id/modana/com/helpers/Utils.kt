package id.modana.com.helpers

import android.content.Context
import java.util.*

val Context.database: DatabaseOpenHelper
    get() = DatabaseOpenHelper.getInstance(applicationContext)

fun Context.dateFormat(): String {
    val calendar = Calendar.getInstance()
    val h = calendar.get(Calendar.HOUR_OF_DAY)
    val m = calendar.get(Calendar.MINUTE)
    return h.toString() + ":" + m.toString()
}