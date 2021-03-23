package site.budanitskaya.homework4_materialdesign

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class CommonAdapter(

    private val onClick: () -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(


) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {

        val view1 = LayoutInflater.from(parent.context)
            .inflate(R.layout.avatar, parent, false)

        val view2 = LayoutInflater.from(parent.context)
            .inflate(R.layout.followers, parent, false)

        val view3 = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler, parent, false)

        return when (viewType) {
            0 -> AvatarViewHolder(view1, onClick)
            1 -> ViewHolder(view2)
            2 -> ViewHolder(view3)
            else -> ViewHolder(view3)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

/*        holder.bind()*/
    }

    class ViewHolder(
        view: View
    ) : RecyclerView.ViewHolder(view) {



        private val root: View = view.rootView

        fun bind() {

        }

    }

    class AvatarViewHolder(
        view: View,
        private val onClick: () -> Unit
    ) : RecyclerView.ViewHolder(view) {



        private val root: View = view.rootView

        fun bind() {
            root.findViewById<TextView>(R.id.followers)

        }

    }

    override fun getItemCount(): Int = 2

}