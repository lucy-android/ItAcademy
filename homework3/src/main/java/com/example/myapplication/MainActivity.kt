package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment

interface MyInterface {
    fun startQuestions()
    fun finishQuestions()
    fun answerQuestion(position: Int, result: Boolean)
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

    override fun finishQuestions() {
        replaceFragment(ResultFragment())
    }

    override fun startQuestions() {
        replaceFragment(QuestionsFragment.newInstance("First"))
    }

    override fun answerQuestion(position: Int, result: Boolean) {
        replaceFragment(QuestionsFragment.newInstance("Second"))
    }
}