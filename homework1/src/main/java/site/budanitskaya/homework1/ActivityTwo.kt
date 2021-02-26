package site.budanitskaya.homework1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatButton

class ActivityTwo : AppCompatActivity(), View.OnClickListener {
    var button21: AppCompatButton? = null
    var button23: AppCompatButton? = null
    var clickListener: View.OnClickListener? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_two)
        button21 = findViewById(R.id.button21)
        button23 = findViewById(R.id.button23)
    }

    override fun onStart() {
        super.onStart()
        clickListener = this
        button21?.setOnClickListener(clickListener)
        button23?.setOnClickListener(clickListener)
    }

    override fun onClick(p0: View?) {

        when (p0?.id) {
            R.id.button21 -> startActivity(MainActivity.createIntent())
            R.id.button23 -> startActivity(ActivityThree.createIntent())
        }
    }

    override fun onStop() {
        super.onStop()
        if (clickListener != null) clickListener = null

    }

    companion object{
        fun createIntent() : Intent = Intent ("activity2")
    }
}