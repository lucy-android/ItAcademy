package site.budanitskaya.homework4_materialdesign

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CommonAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AvatarViewHolder {

        val view1 = LayoutInflater.from(parent.context)
            .inflate(R.layout.avatar, parent, false)

        val view2 = LayoutInflater.from(parent.context)
            .inflate(R.layout.followers, parent, false)


        return when (viewType) {
            0 -> AvatarViewHolder(view1)
            else -> AvatarViewHolder(view2)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            1 -> {
                var avatarViewHolder = holder as AvatarViewHolder
                avatarViewHolder.bind()
            }
            2 -> {
                var followersViewHolder = holder as FollowersViewHolder
                followersViewHolder.bind()
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

    override fun getItemCount(): Int = 2

}