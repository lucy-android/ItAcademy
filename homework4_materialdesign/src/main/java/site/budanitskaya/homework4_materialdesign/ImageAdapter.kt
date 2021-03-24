package site.budanitskaya.homework4_materialdesign

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class ImageAdapter(
    private val imagesList: List<Int>,
    private val onImageClick: (Int) -> Unit
) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_image, parent, false)
        return ImageViewHolder(view, onImageClick)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(imagesList[position])
    }

    override fun getItemCount(): Int = imagesList.size


    class ImageViewHolder(
        view: View,
        private val onImageClick: (Int) -> Unit
    ) : RecyclerView.ViewHolder(view) {


        private val root: View = view.rootView
        private val firstPhoto: ImageView = root.findViewById(R.id.first_photo)

        fun bind(imageDrawable: Int) {
            firstPhoto.setImageResource(imageDrawable)
            root.setOnClickListener {
                onImageClick(imageDrawable)
            }
        }
    }
}