package id.modana.com.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.Menu
import id.modana.com.R
import id.modana.com.adapter.MenuListAdapter
import id.modana.com.database.AttandanceTable
import id.modana.com.helpers.database
import id.modana.com.helpers.dateFormat
import id.modana.com.model.MenuList
import id.modana.com.network.ModanaRepository
import id.modana.com.presenter.AttendancePresenter
import id.modana.com.view.AttandanceView
import kotlinx.android.synthetic.main.menu_layout.*
import kotlinx.android.synthetic.main.swipe_attandance_layout.*
import kotlinx.android.synthetic.main.toolbar.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class MainActivity : AppCompatActivity(), AttandanceView {


    private lateinit var menuAdapter: MenuListAdapter
    private lateinit var attandacePresenter: AttendancePresenter
    lateinit var view: AttandanceView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar_actionbar)
        val repository = ModanaRepository()
        attandacePresenter = AttendancePresenter(this, repository)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        setCategoryMenu()
        onSwipeButton()
    }

    private fun setCategoryMenu() {
        val channelList = arrayListOf(
            MenuList(resources.getString(R.string.title_payroll), R.drawable.ic_payroll),
            MenuList(
                resources.getString(R.string.title_attandance),
                R.drawable.ic_attendance
            ),
            MenuList(resources.getString(R.string.title_lending), R.drawable.ic_lending),
            MenuList(resources.getString(R.string.title_leave), R.drawable.ic_leave),
            MenuList(resources.getString(R.string.title_employee), R.drawable.ic_employees),
            MenuList(
                resources.getString(R.string.title_more),
                R.drawable.ic_others_menu_item
            )

        )

        menuAdapter = MenuListAdapter(this@MainActivity, channelList)
        menuAdapter.setOnMenuClickListener(object : MenuListAdapter.OnMenuClickListener {
            override fun onChannelClick(position: Int) {

            }
        })
        recycle_menu.layoutManager = GridLayoutManager(this@MainActivity, 3)
        recycle_menu.adapter = menuAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_task, menu)
        return super.onCreateOptionsMenu(menu)
    }

    fun onSwipeButton() {
        swipe_attandance.onSwipedOnListener = {
            time_attendance.text = "Shift ends at "
            time.text = this@MainActivity.dateFormat()
            attandacePresenter.actionPostAttendance(this@MainActivity, this@MainActivity.dateFormat(), 1)
        }

        swipe_attandance.onSwipedOffListener = {
            time_attendance.text = "Shift starts at "
            time.text = this@MainActivity.dateFormat()
            attandacePresenter.actionPostAttendance(this@MainActivity, this@MainActivity.dateFormat(), 0)
        }
    }

    override fun onStart() {
        super.onStart()
        lookDatabase()
    }

    private fun lookDatabase() {
        this@MainActivity.database.use {
            val result = select(AttandanceTable.TABLE_ATTANDANE)
            val attandance = result.parseList(classParser<AttandanceTable>())
            if (attandance.isNotEmpty()) {
                if (attandance[0].isChecked == 1) {
                    time_attendance.text = "Shift ends at "
                } else {
                    time_attendance.text = "Shift starts at "

                }
                time.text = attandance[0].time
                swipe_attandance.isChecked = attandance[0].isChecked == 1
            } else {
                swipe_attandance.isChecked = false
            }

        }
    }

    override fun showLoading() {}

    override fun hideLoading() {}

    override fun showTeamDetail(data: List<AttandanceTable>) {}

}
