package id.modana.com.helpers

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import id.modana.com.database.AttandanceTable
import org.jetbrains.anko.db.*

class DatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "modana.db", null, 1) {
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.createTable(AttandanceTable.TABLE_ATTANDANE, true,
            AttandanceTable.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            AttandanceTable.TIME to TEXT,
            AttandanceTable.ISCHECKED to INTEGER
        )

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.dropTable(AttandanceTable.TABLE_ATTANDANE, true)
    }

    companion object {
        private var instance: DatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): DatabaseOpenHelper {
            if (instance == null) {
                instance = DatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as DatabaseOpenHelper
        }
    }
}