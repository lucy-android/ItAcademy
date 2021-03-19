package site.budanitskaya.homework4recyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import site.budanitskaya.homework4recyclerview.DataUtils.Companion.colorsList
import site.budanitskaya.homework4recyclerview.databinding.FragmentColorGridBinding


class ColorGridFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentColorGridBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_color_grid, container, false
        )

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 4)

        val adapter = ColorGridAdapter(colorsList)

        recyclerView.adapter = adapter

        return binding.root
    }
}