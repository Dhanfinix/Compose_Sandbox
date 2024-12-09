package edts.android.composesandbox.component.search

interface SearchDelegate {
    fun onChange(value: String)
    fun onClose()
}