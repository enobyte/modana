package id.modana.com.view

import id.modana.com.database.AttandanceTable

interface AttandanceView {
    fun showLoading()
    fun hideLoading()
    fun showTeamDetail(data: List<AttandanceTable>)
}