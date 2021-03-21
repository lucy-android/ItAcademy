package site.budanitskaya.homework4_materialdesign

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class CommonAdapter(
) : RecyclerView.Adapter<CommonAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CommonAdapter.ViewHolder {

        val view1 = LayoutInflater.from(parent.context)
            .inflate(R.layout.avatar, parent, false)

        val view2 = LayoutInflater.from(parent.context)
            .inflate(R.layout.followers, parent, false)

        val view3 = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler, parent, false)

        return when (viewType) {
            0 -> ViewHolder(view1)
            1 -> ViewHolder(view2)
            2 -> ViewHolder(view3)
            else -> ViewHolder(view3)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind()
    }

    class ViewHolder(
        view: View
    ) : RecyclerView.ViewHolder(view) {



        private val root: View = view.rootView

        fun bind() {

        }

    }

    override fun getItemCount(): Int = 3

}