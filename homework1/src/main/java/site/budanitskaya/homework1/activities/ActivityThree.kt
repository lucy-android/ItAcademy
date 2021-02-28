package site.budanitskaya.homework1.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import site.budanitskaya.homework1.R

class ActivityThree : AppCompatActivity() {
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
        clickListener = View.OnClickListener { p0: View? ->

            when (p0?.id) {
                R.id.button31 -> startActivity(MainActivity.createIntent())
                R.id.button32 -> startActivity(ActivityTwo.createIntent())
            }
        }

        button31?.setOnClickListener(clickListener)
        button32?.setOnClickListener(clickListener)
    }

    override fun onStop() {
        super.onStop()
        if (clickListener != null) clickListener = null

    }

    companion object {
        fun createIntent(): Intent = Intent("activity3")
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