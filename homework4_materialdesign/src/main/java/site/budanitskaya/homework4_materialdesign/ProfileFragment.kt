package site.budanitskaya.homework4_materialdesign

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.SimpleOnItemTouchListener


class ProfileFragment : Fragment() {

    private val drawableList = mutableListOf<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_profile, container, false)


        val avatarRecyclerView = view.findViewById<RecyclerView>(R.id.avatarRecyclerView)
        avatarRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        avatarRecyclerView.adapter = CommonAdapter()


        drawableList.add(0, R.drawable.fight)
        drawableList.add(1, R.drawable.ginger)

        drawableList.add(2, R.drawable.mastered_dzen)
        drawableList.add(3, R.drawable.on_the_floor)
        drawableList.add(4, R.drawable.pumik_plus_stasenka)
        drawableList.add(5, R.drawable.slippers)

        drawableList.add(6, R.drawable.sweet_boy)

        drawableList.add(7, R.drawable.dangerous)
        drawableList.add(8, R.drawable.awesome)
        drawableList.add(9, R.drawable.kanapka_angry)
        drawableList.add(10, R.drawable.my_cats)
        drawableList.add(11, R.drawable.pumik_and_vaksa)


        val recyclerView = view.findViewById<RecyclerView>(R.id.figure_recyclerView)

        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        recyclerView.adapter = FigureAdapter(drawableList,
            onClick = {
                startActivity(
                    FigureActivity.getStartIntent(
                        context = requireContext(),
                        figure = it
                    )
                )
            }
        )

        return view
    }
}