package site.budanitskaya.homework4recyclerview

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import site.budanitskaya.homework4recyclerview.DataUtils.Companion.EXTRA_MESSAGE
import java.lang.String


class ColorGridAdapter(
    private val dataSet: List<Int>,
    context: Context
) :
    RecyclerView.Adapter<ColorGridAdapter.ViewHolder>() {

    var context: Context = context

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val view_view: View = view.findViewById(R.id.view_view)

        init {
            // Define click listener for the ViewHolder's View.
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.grid_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        var item = dataSet[position]

        val hexColor = String.format("#%06X", 0xFFFFFF and item)

        viewHolder.itemView.setBackgroundColor(item)

        viewHolder.itemView.setOnClickListener {
            var i = Intent(context, ColorActivity::class.java)

            i.putExtra(EXTRA_MESSAGE,hexColor)
            context.startActivity(i)
        }
    }

    override fun getItemCount() = dataSet.size

}