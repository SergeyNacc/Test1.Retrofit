package nacc.sergey.test1retrofit

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_recycler.view.*
import nacc.sergey.test1retrofit.data.Data

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {

    val firsName = view.textView_first_name
    val lastName = view.textView_last_name

    fun bind(data: Data) {
        firsName.text = data.firstName
        lastName.text = data.lastName
    }
}