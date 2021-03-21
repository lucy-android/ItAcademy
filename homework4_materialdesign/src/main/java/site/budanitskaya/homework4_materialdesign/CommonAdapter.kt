package site.budanitskaya.homework4_materialdesign

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class CommonAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

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
            0 -> AvatarViewHolder(view1)
            1 -> FollowersViewHolder(view2)
            2 -> RecyclerViewHolder(view3)
            else -> RecyclerViewHolder(view3)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            0 -> {
                var avatarViewHolder = holder as AvatarViewHolder
                avatarViewHolder.bind()
            }
            1 -> {
                var followersViewHolder = holder as FollowersViewHolder
                followersViewHolder.bind()
            }
            2 -> {
                var recyclerViewHolder = holder as RecyclerViewHolder
                recyclerViewHolder.bind()
            }
        }
    }

    class AvatarViewHolder(
        view: View
    ) : RecyclerView.ViewHolder(view) {


        private val root: View = view.rootView

        fun bind() {
        }

    }


    class FollowersViewHolder(
        view: View
    ) : RecyclerView.ViewHolder(view) {


        private val root: View = view.rootView

        fun bind() {
        }

    }

    class RecyclerViewHolder(
        view: View
    ) : RecyclerView.ViewHolder(view) {


        private val root: View = view.rootView

        fun bind() {
            /*root.background = root.findViewById(R.id.child_recyclerview)*/
        }

    }

    override fun getItemCount(): Int = 3

}