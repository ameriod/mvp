package me.ameriod.lib.error

import android.annotation.SuppressLint
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar

sealed class Error<T> {

    abstract fun show(view: View): T

    abstract fun remove(item: T?)

    data class Text internal constructor(internal val text: CharSequence? = null,
                                         @StringRes internal val textResId: Int = 0) {

        constructor(text: CharSequence) : this(text, 0)

        constructor(@StringRes text: Int) : this(null, text)
    }

    data class Action internal constructor(internal val label: Text,
                                           internal val listener: OnInteractedListener? = null) {

        interface OnInteractedListener {
            fun onInteraction()
        }

    }

    data class ToastMessage internal constructor(private val message: Text,
                                                 private val duration: Int = Toast.LENGTH_SHORT) : Error<Toast>() {

        constructor(message: CharSequence) : this(Text(message))

        constructor(@StringRes messageResId: Int) : this(Text(messageResId))

        @SuppressLint("ShowToast")
        override fun show(view: View): Toast =
                message.let {
                    if (it.text != null) {
                        Toast.makeText(view.context, it.text, duration)
                    } else {
                        Toast.makeText(view.context, it.textResId, duration)
                    }
                }.apply { show() }

        override fun remove(item: Toast?) {
            item?.cancel()
        }

    }

    data class SnackbarMessage internal constructor(private val message: Text,
                                                    private val duration: Int = Snackbar.LENGTH_SHORT,
                                                    private val action: Action? = null) : Error<Snackbar>() {

        constructor(message: CharSequence) : this(Text(message))

        constructor(@StringRes messageResId: Int) : this(Text(messageResId))

        override fun show(view: View): Snackbar {
            val snackbar = if (message.text != null) {
                Snackbar.make(view, message.text, duration)
            } else {
                Snackbar.make(view, message.textResId, duration)
            }

            action?.apply {
                if (label.text != null) {
                    snackbar.setAction(label.text) { action.listener?.onInteraction() }
                } else {
                    snackbar.setAction(label.textResId) { action.listener?.onInteraction() }
                }
            }

            return snackbar.apply { show() }
        }

        override fun remove(item: Snackbar?) {
            if (item?.isShownOrQueued == true) item.dismiss()
        }

    }

    data class DialogMessage internal constructor(private val title: Text?,
                                                  private val message: Text?,
                                                  private val positiveAction: Action?,
                                                  private val neutralAction: Action?,
                                                  private val negativeAction: Action?,
                                                  private val cancellable: Boolean = true) : Error<AlertDialog>() {

        override fun show(view: View): AlertDialog {
            val builder = AlertDialog.Builder(view.context)
                    .setCancelable(cancellable)

            title?.apply {
                if (text != null) {
                    builder.setTitle(title.text)
                } else
                    builder.setTitle(title.textResId)
            }

            message?.apply {
                if (text != null) {
                    builder.setMessage(text)
                } else {
                    builder.setMessage(textResId)
                }
            }
            positiveAction?.apply {
                if (label.text != null) {
                    builder.setPositiveButton(label.text) { _, _ -> listener?.onInteraction() }
                } else {
                    builder.setPositiveButton(label.textResId) { _, _ -> listener?.onInteraction() }
                }
            }

            neutralAction?.apply {
                if (label.text != null) {
                    builder.setNeutralButton(label.text) { _, _ -> listener?.onInteraction() }
                } else {
                    builder.setNeutralButton(label.textResId) { _, _ -> listener?.onInteraction() }
                }
            }

            negativeAction?.apply {
                if (label.text != null) {
                    builder.setNegativeButton(label.text) { _, _ -> listener?.onInteraction() }
                } else {
                    builder.setNegativeButton(label.textResId) { _, _ -> listener?.onInteraction() }
                }
            }

            return builder.show()
        }

        override fun remove(item: AlertDialog?) {
            item?.dismiss()
        }

    }

    abstract class ViewMessage() : Error<View>()

}
