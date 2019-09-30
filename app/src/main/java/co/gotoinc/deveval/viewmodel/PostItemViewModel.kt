package co.gotoinc.deveval.viewmodel

import android.widget.CompoundButton
import androidx.appcompat.widget.SwitchCompat
import androidx.databinding.BaseObservable
import androidx.databinding.BindingAdapter
import co.gotoinc.deveval.model.Post
import java.text.SimpleDateFormat
import java.util.*

class PostItemViewModel(
    val item: Post,
    val selected: Boolean?,
    val onItemSwitch: (Boolean, Post) -> Unit
) : BaseObservable() {

    fun getDate() =
        SimpleDateFormat("MMMM dd, yyyy h:mm:ss a", Locale.CANADA).format(item.getCreatedAtDate())

}


@BindingAdapter("onCheckedListener")
fun setOnCheckedListener(switchCompat: SwitchCompat, vm: PostItemViewModel) {
    switchCompat.setOnCheckedChangeListener { _, isChecked -> vm.onItemSwitch(isChecked, vm.item) }
}