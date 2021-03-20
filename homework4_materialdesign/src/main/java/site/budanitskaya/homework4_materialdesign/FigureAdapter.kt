package site.budanitskaya.homework4_materialdesign

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FigureAdapter(
    private val figures: List<Int>,
    private val onClick: (Int) -> Unit
) : RecyclerView.Adapter<FigureAdapter.FigureViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FigureViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_figure, parent, false)
        return FigureViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: FigureViewHolder, position: Int) {
        holder.bind(figures[position])
    }

    override fun getItemCount(): Int = figures.size


    class FigureViewHolder(
        view: View,
        private val onClick: (Int) -> Unit
    ) : RecyclerView.ViewHolder(view) {

        private val root: View = view.rootView
        private val name: TextView = root.findViewById(R.id.figure)

        fun bind(figure: Int) {
            name.text = figure.toString()
            root.setOnClickListener {
                onClick(figure)
            }
        }

    }
}