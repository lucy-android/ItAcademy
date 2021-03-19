package site.budanitskaya.homework4recyclerview

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import site.budanitskaya.homework4recyclerview.DataUtils.Companion.INTENT_FLAG


class ColorGridAdapter(
    private val colorList: List<Int>,
    context: Context
) :
    RecyclerView.Adapter<ColorGridAdapter.ViewHolder>() {

    val context: Context = context

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.grid_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        val colorInt = colorList[position]

        val colorString = String.format("#%06X", 0xFFFFFF and colorInt)

        viewHolder.itemView.setBackgroundColor(colorInt)

        viewHolder.itemView.setOnClickListener {
            val intentToColorActivity = Intent(context, ColorActivity::class.java)

            intentToColorActivity.putExtra(INTENT_FLAG, colorString)
            context.startActivity(intentToColorActivity)

        }
    }

    override fun getItemCount() = colorList.size

}