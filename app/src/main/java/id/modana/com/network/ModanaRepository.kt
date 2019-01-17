package id.modana.com.network

import android.content.Context
import id.modana.com.callback.OnDataRequest
import id.modana.com.database.AttandanceTable
import id.modana.com.helpers.database
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.update

class ModanaRepository {

    fun postAttandance(context: Context, time: String, isChecked: Int, callback: OnDataRequest) {
        callback.onLoading(true)
        try {
            when (isChecked) {
                1 -> {
                    context.database.use {
                        insert(
                            AttandanceTable.TABLE_ATTANDANE,
                            AttandanceTable.TIME to time,
                            AttandanceTable.ISCHECKED to isChecked
                        )
                    }
                }
                0 -> {
                    context.database.use {
                        update(
                            AttandanceTable.TABLE_ATTANDANE,
                            AttandanceTable.ISCHECKED to isChecked,
                            AttandanceTable.TIME to time
                        ).exec()
                    }
                }
            }

            callback.onLoading(false)
            callback.onSuccess()
        } catch (e: Exception) {
            callback.onLoading(false)
            callback.onError()
        }

    }
}