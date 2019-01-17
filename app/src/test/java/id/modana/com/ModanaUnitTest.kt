package id.modana.com

import android.content.Context
import id.modana.com.database.AttandanceTable
import id.modana.com.helpers.database
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@RunWith(RobolectricTestRunner::class)
class ModanaUnitTest {

    private lateinit var context: Context

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        context = RuntimeEnvironment.application
    }


    @Test
    fun insertAttendanceOk() {

        val time = "123:123"
        val isChecked = 1
        Mockito.spy(resultQuery())
        attemptAttendance(time, isChecked)
        Assert.assertEquals(resultQuery()[0].time, time)

    }


    private fun resultQuery(): List<AttandanceTable> {

        var data: List<AttandanceTable> = mutableListOf()
        context.database.use {
            val result = select(AttandanceTable.TABLE_ATTANDANE)
            data = result.parseList(classParser())
        }
        return data
    }

    private fun attemptAttendance(time: String, isChecked: Int) {
        context.database.use {
            insert(
                AttandanceTable.TABLE_ATTANDANE,
                AttandanceTable.TIME to time,
                AttandanceTable.ISCHECKED to isChecked
            )
        }
    }

}
