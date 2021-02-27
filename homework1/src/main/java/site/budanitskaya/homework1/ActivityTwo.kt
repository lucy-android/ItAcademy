package site.budanitskaya.homework1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.AppCompatButton

class ActivityTwo : AppCompatActivity() {
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
        clickListener = View.OnClickListener { p0: View? ->

            when (p0?.id) {
                R.id.button21 -> startActivity(MainActivity.createIntent())
                R.id.button23 -> startActivity(ActivityThree.createIntent())
            }
        }

        button21?.setOnClickListener(clickListener)
        button23?.setOnClickListener(clickListener)
    }


    override fun onStop() {
        super.onStop()
        if (clickListener != null) clickListener = null

    }

    companion object {
        fun createIntent(): Intent = Intent("activity2")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        super.onCreateOptionsMenu(menu)

        val inflater = menuInflater
        inflater.inflate(R.menu.menu_items, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        super.onOptionsItemSelected(item)

        when (item.itemId) {
            R.id.share -> startActivity(Intent.createChooser(Intent(Intent.ACTION_SEND), null))
        }

        return true
    }
}