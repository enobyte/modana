package id.modana.com.database

data class AttandanceTable(val id: Long?, val time: String?, val isChecked: Int?) {
    companion object {
        const val TABLE_ATTANDANE: String = "TABLE_ATTANDANCE"
        const val ID: String = "ID_"
        const val TIME: String = "TIME"
        const val ISCHECKED: String = "ISCHECKED"
    }
}