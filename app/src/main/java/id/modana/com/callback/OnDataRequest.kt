package id.modana.com.callback

interface OnDataRequest {
    fun onSuccess()
    fun onError()
    fun onLoading(isLoading: Boolean)
}