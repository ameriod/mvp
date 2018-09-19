package me.ameriod.lib.error

class Tester {

    private var error: MvpError<*>? = null

    init {
        error = MvpError(Error.ToastMessage(Error.Text("")))
    }

    fun remove() {
        error?.remove()
    }
}