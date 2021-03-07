package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.myapplication.DataUtils.Companion.counter

interface MyInterface {

    fun loadNextQuestionOrResult(position: Int)
}

class MainActivity : AppCompatActivity(), MyInterface {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFragment()
    }

    private fun initFragment() {
        replaceFragment(FirstFragment.newInstance())
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }

    override fun loadNextQuestionOrResult(position: Int) {

        if (position < DataUtils.generateQuizItems().size) {
            replaceFragment(QuestionsFragment.newInstance("$position"))
            counter++
        } else if (position == DataUtils.generateQuizItems().size) {
            replaceFragment(ResultFragment.newInstance("", ""))
        }
    }
}