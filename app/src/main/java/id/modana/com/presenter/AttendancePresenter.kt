package id.modana.com.presenter

import android.content.Context
import id.modana.com.callback.OnDataRequest
import id.modana.com.network.ModanaRepository
import id.modana.com.view.AttandanceView

class AttendancePresenter(private val view: AttandanceView, private val repository: ModanaRepository) {

    fun actionPostAttendance(context: Context, time: String, isChecked: Int) {
        view.showLoading()
        repository.postAttandance(context, time, isChecked, object : OnDataRequest{
            override fun onLoading(isLoading: Boolean) {
                view.showLoading()
            }

            override fun onSuccess() {
                view.hideLoading()
            }

            override fun onError() {
                view.hideLoading()
            }
        })
        view.hideLoading()
    }
}