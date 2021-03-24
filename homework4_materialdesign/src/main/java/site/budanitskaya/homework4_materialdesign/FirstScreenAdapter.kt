package site.budanitskaya.homework4_materialdesign

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.lang.Exception


class FirstScreenAdapter(

    private val figures: List<Int>,
    private val onUrlClick: () -> Unit,
    private val onPhotoClick: (Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val profileDescriptionLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.profile_description_layout, parent, false)

        val subScriptingSection = LayoutInflater.from(parent.context)
            .inflate(R.layout.subscription_section, parent, false)

        val followButtonSection = LayoutInflater.from(parent.context)
            .inflate(R.layout.follow_button_section, parent, false)

        val childRecyclerView = LayoutInflater.from(parent.context)
            .inflate(R.layout.child_recycler_view, parent, false)

        return when (viewType) {
            0 -> ProfileDescViewHolder(profileDescriptionLayout, onUrlClick)
            1 -> FollowersViewHolder(subScriptingSection)
            2 -> ButtonsViewHolder(followButtonSection)
            3 -> RecyclerViewHolder(childRecyclerView, figures, onPhotoClick)
            else -> throw Exception()
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {
            is ProfileDescViewHolder -> holder.bind()
            is FollowersViewHolder -> holder.bind()
            is ButtonsViewHolder -> holder.bind()
            is RecyclerViewHolder -> holder.bind()
        }
    }

    override fun getItemViewType(position: Int): Int = position

    override fun getItemCount(): Int = 4


    class ProfileDescViewHolder(
        view: View,
        private val onUrlClick: () -> Unit
    ) : RecyclerView.ViewHolder(view) {

        private val root: View = view.rootView
        private val urlTextView: TextView = root.findViewById(R.id.url_textview)

        fun bind() {

            urlTextView.setOnClickListener {
                onUrlClick()
            }
        }
    }

    class FollowersViewHolder(
        view: View
    ) : RecyclerView.ViewHolder(view) {

        fun bind() {}

    }

    class ButtonsViewHolder(
        view: View
    ) : RecyclerView.ViewHolder(view) {

        fun bind() {}

    }

    class RecyclerViewHolder(
        view: View,
        private val figures: List<Int>,
        private val onPhotoClick: (Int) -> Unit
    ) : RecyclerView.ViewHolder(view) {

        private val root: View = view.rootView
        private val childRecyclerView = root.findViewById<RecyclerView>(R.id.child_recyclerview)

        fun bind() {

            val adapter = ImageAdapter(figures, onPhotoClick)

            childRecyclerView.adapter = adapter
            childRecyclerView.layoutManager = GridLayoutManager(this.itemView.context, 2)
        }

    }
}