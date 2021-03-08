package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.myapplication.DataUtils.Companion.generateQuiz
import com.example.myapplication.DataUtils.Companion.resultScore


class ResultFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_result, container, false)
    }

    override fun onStart() {
        super.onStart()
        val txt: TextView = view?.findViewById<View>(R.id.text_res) as TextView
        var text = "Вы правильно ответили на $resultScore вопросов из ${generateQuiz().size}.\nПравильные ответы: \n"

        for (entrySet in generateQuiz()){

            val answer = if (entrySet.value.answer) "да" else "нет"
            val exp = "Вопрос ${entrySet.key}. ${entrySet.value.question} Ответ: $answer \n"
            text += exp
        }

        txt.text = text
    }

    companion object {

        @JvmStatic
        fun newInstance() = ResultFragment()
    }
}