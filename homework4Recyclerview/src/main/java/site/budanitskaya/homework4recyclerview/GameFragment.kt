package site.budanitskaya.homework4recyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import site.budanitskaya.homework4recyclerview.DataUtils.Companion.generateColorQuestion
import site.budanitskaya.homework4recyclerview.DataUtils.Companion.score
import site.budanitskaya.homework4recyclerview.databinding.FragmentGameBinding
import java.lang.String


class GameFragment : Fragment() {

    lateinit var binding: FragmentGameBinding
    lateinit var colorQuestion: ColorQuestion

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_game, container, false
        )

        setGameScreen()

        binding.button1.setOnClickListener {

            updateScore(0)
            binding.invalidateAll()
            setGameScreen()
        }

        binding.button2.setOnClickListener {

            updateScore(1)
            binding.invalidateAll()
            setGameScreen()
        }

        binding.button3.setOnClickListener {

            updateScore(2)
            binding.invalidateAll()
            setGameScreen()
        }

        binding.button4.setOnClickListener {

            updateScore(3)
            binding.invalidateAll()
            setGameScreen()
        }

        binding.button5.setOnClickListener {

            updateScore(4)
            binding.invalidateAll()
            setGameScreen()
        }

        binding.button6.setOnClickListener {

            updateScore(5)
            binding.invalidateAll()
            setGameScreen()
        }
        return binding.root
    }

    private fun setGameScreen() {
        colorQuestion = generateColorQuestion()
        binding.button1.text = String.format("#%06X", 0xFFFFFF and colorQuestion.variants[0])
        binding.button2.text = String.format("#%06X", 0xFFFFFF and colorQuestion.variants[1])
        binding.button3.text = String.format("#%06X", 0xFFFFFF and colorQuestion.variants[2])
        binding.button4.text = String.format("#%06X", 0xFFFFFF and colorQuestion.variants[3])
        binding.button5.text = String.format("#%06X", 0xFFFFFF and colorQuestion.variants[4])
        binding.button6.text = String.format("#%06X", 0xFFFFFF and colorQuestion.variants[5])
        binding.sampleText.text = score.toString()
        binding.back.setBackgroundColor(generateColorQuestion().correctAnswer)

        binding.game = this
    }

    fun updateScore(number: Int) {
        if (colorQuestion.correctAnswer == colorQuestion.variants[number]) {
            score++
        } else {
            score--
        }
    }
}