package site.budanitskaya.homework4_materialdesign

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        val drawableList = generateDrawableList()

        val recyclerView = view.findViewById<RecyclerView>(R.id.first_screen_recyclerview)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val urlTextView = view.findViewById<TextView>(R.id.url_textview)

        recyclerView.adapter = FirstScreenAdapter(drawableList,
            {
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(urlTextView.text.toString())
                )
                startActivity(intent)
            },
            {
                startActivity(
                    ImageActivity.getStartIntent(
                        context = requireContext(),
                        figure = it
                    )
                )
            }
        )

        return view
    }
}