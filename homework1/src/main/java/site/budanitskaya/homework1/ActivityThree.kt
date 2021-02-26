package site.budanitskaya.homework1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatButton

class ActivityThree : AppCompatActivity(), View.OnClickListener {
    var button31: AppCompatButton? = null
    var button32: AppCompatButton? = null
    var clickListener: View.OnClickListener? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_three)
        button31 = findViewById(R.id.button31)
        button32 = findViewById(R.id.button32)
    }

    override fun onStart() {
        super.onStart()
        clickListener = this
        button31?.setOnClickListener(clickListener)
        button32?.setOnClickListener(clickListener)
    }

    override fun onClick(p0: View?) {

        when (p0?.id) {
            R.id.button31 -> startActivity(MainActivity.createIntent())
            R.id.button32 -> startActivity(ActivityTwo.createIntent())
        }
    }

    override fun onStop() {
        super.onStop()
        if (clickListener != null) clickListener = null

    }

    companion object{
        fun createIntent() : Intent = Intent ("activity3")
    }
}