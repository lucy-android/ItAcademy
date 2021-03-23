package site.budanitskaya.homework4_materialdesign

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
import java.lang.Exception

class CommonTwoAdapter(

    private val figures: List<Int>,

    private val onClick: () -> Unit,
    private val onPhotoClick: (Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view1 = LayoutInflater.from(parent.context)
            .inflate(R.layout.avatar, parent, false)

        val view2 = LayoutInflater.from(parent.context)
            .inflate(R.layout.followers, parent, false)

        val view3 = LayoutInflater.from(parent.context)
            .inflate(R.layout.follow_button_section, parent, false)

        val view4 = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler, parent, false)

        return when (viewType) {
            0 -> AvatarViewHolder(view1, onClick)
            1 -> FollowersViewHolder(view2, onClick)
            2 -> ButtonsViewHolder(view3, onClick)
            3 -> RecyclerViewHolder(view4, figures, onPhotoClick)
            else -> throw Exception()
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is AvatarViewHolder) {
            val holder1 = holder as AvatarViewHolder
            holder1.bind()
        }

        if (holder is FollowersViewHolder) {
            val holder1 = holder as FollowersViewHolder
            holder1.bind()
        }

        if (holder is ButtonsViewHolder) {
            val holder1 = holder as ButtonsViewHolder
            holder1.bind()
        }

        if (holder is RecyclerViewHolder) {
            val holder1 = holder as RecyclerViewHolder
            holder1.bind()
        }


    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int = 4


    class AvatarViewHolder(
        view: View,
        private val onClick: () -> Unit
    ) : RecyclerView.ViewHolder(view) {


        private val root: View = view.rootView
        private val internetAddress: TextView = root.findViewById(R.id.internet_address)

        fun bind() {

            internetAddress.setOnClickListener {
                onClick()
            }
        }

    }

    class FollowersViewHolder(
        view: View,
        private val onClick: () -> Unit
    ) : RecyclerView.ViewHolder(view) {


        private val root: View = view.rootView
/*        private val internetAddress: TextView = root.findViewById(R.id.internet_address)*/

        fun bind() {

            root.setOnClickListener {
                onClick()
            }
        }

    }

    class ButtonsViewHolder(
        view: View,
        private val onClick: () -> Unit
    ) : RecyclerView.ViewHolder(view) {


        private val root: View = view.rootView
/*        private val internetAddress: TextView = root.findViewById(R.id.internet_address)*/

        fun bind() {

/*            root.setOnClickListener {
                onClick()
            }*/
        }

    }

    class RecyclerViewHolder(
        view: View,
        private val figures: List<Int>,
        private val onPhotoClick: (Int) -> Unit
    ) : RecyclerView.ViewHolder(view) {


        private val root: View = view.rootView
        /*private val firstPhoto: ImageView = root.findViewById(R.id.first_photo)*/

        private val reCycler = root.findViewById<RecyclerView>(R.id.figure_recyclerView)


        fun bind() {

            val adapter = FigureAdapter(figures, onPhotoClick)

            reCycler.adapter = adapter
            reCycler.layoutManager = GridLayoutManager(this.itemView.context, 2)
/*            (reCycler.layoutManager as StaggeredGridLayoutManager).gapStrategy =
                GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS*/
        }

    }
}