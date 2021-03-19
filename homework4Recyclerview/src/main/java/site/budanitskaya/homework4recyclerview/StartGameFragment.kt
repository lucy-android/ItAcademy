package site.budanitskaya.homework4recyclerview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import site.budanitskaya.homework4recyclerview.databinding.FragmentStartGameBinding


class StartGameFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: FragmentStartGameBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_start_game, container, false
        )

        binding.startGame.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_startGameFragment_to_gameFragment)
        }

        return binding.root
    }
}