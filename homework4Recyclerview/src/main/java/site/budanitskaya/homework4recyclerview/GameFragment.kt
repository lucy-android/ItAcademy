package site.budanitskaya.homework4recyclerview

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
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
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_game, container, false
        )


/*        callMe()*/

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
        Log.d(
            "1234567890",
            "onCreateView: ${colorQuestion.correctAnswer}, ${colorQuestion.variants}"
        )

        binding.button1.setOnClickListener {

            if (colorQuestion.correctAnswer == colorQuestion.variants[0]) {
                score++
            } else {
                score--
            }


            binding.invalidateAll()

            callMe()
            Log.d(
                "1234567890",
                "onCreateView: ${colorQuestion.correctAnswer}, ${colorQuestion.variants}"
            )


        }

        binding.button2.setOnClickListener {

            if (colorQuestion.correctAnswer == colorQuestion.variants[1]) {
                score++
            } else {
                score--
            }


            binding.invalidateAll()

            callMe()
            Log.d(
                "1234567890",
                "onCreateView: ${colorQuestion.correctAnswer}, ${colorQuestion.variants}"
            )


        }

        binding.button3.setOnClickListener {

            if (colorQuestion.correctAnswer == colorQuestion.variants[2]) {
                score++
            } else {
                score--
            }


            binding.invalidateAll()

            callMe()
            Log.d(
                "1234567890",
                "onCreateView: ${colorQuestion.correctAnswer}, ${colorQuestion.variants}"
            )


        }

        binding.button4.setOnClickListener {

            if (colorQuestion.correctAnswer == colorQuestion.variants[3]) {
                score++
            } else {
                score--
            }


            binding.invalidateAll()

            callMe()
            Log.d(
                "1234567890",
                "onCreateView: ${colorQuestion.correctAnswer}, ${colorQuestion.variants}"
            )


        }

        binding.button5.setOnClickListener {

            if (colorQuestion.correctAnswer == colorQuestion.variants[4]) {
                score++
            } else {
                score--
            }


            binding.invalidateAll()

            callMe()
            Log.d(
                "1234567890",
                "onCreateView: ${colorQuestion.correctAnswer}, ${colorQuestion.variants}"
            )


        }

        binding.button6.setOnClickListener {

            if (colorQuestion.correctAnswer == colorQuestion.variants[5]) {
                score++
            } else {
                score--
            }


            binding.invalidateAll()

            callMe()
            Log.d(
                "1234567890",
                "onCreateView: ${colorQuestion.correctAnswer}, ${colorQuestion.variants}"
            )


        }






        return binding.root
    }

    fun callMe() {
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
}