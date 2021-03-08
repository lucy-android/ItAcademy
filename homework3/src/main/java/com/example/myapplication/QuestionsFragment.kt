package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.myapplication.DataUtils.Companion.counter
import com.example.myapplication.DataUtils.Companion.resultScore

private const val TEXT = "param1"

class QuestionsFragment : Fragment() {
    private var text: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            text = it.getString(TEXT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(
            R.layout.fragment_questions,
            container,
            false
        )
        root.findViewById<TextView>(R.id.question1).text = text
        return root
    }

    override fun onStart() {
        super.onStart()
        view?.findViewById<Button>(R.id.button_yes)?.setOnClickListener {

            when (DataUtils.generateQuiz()[counter]?.answer) {
                false -> {
                    moveToNextScreen()
                }
                true -> {
                    resultScore++
                    moveToNextScreen()
                }
            }
        }

        view?.findViewById<Button>(R.id.button_no)?.setOnClickListener {

            when (DataUtils.generateQuiz()[counter]?.answer) {
                false -> {
                    resultScore++
                    moveToNextScreen()
                }
                true -> {
                    moveToNextScreen()
                }
            }
        }
    }

    private fun moveToNextScreen() {
        counter++
        (requireActivity() as MyInterface).loadNextQuestionOrResult(counter)
    }

    companion object {
        @JvmStatic
        fun newInstance(text: String?) =
            QuestionsFragment().apply {
                arguments = Bundle().apply {
                    putString(TEXT, text)
                }
            }
    }
}