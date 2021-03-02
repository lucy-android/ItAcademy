package site.budanitskaya.homework1.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import site.budanitskaya.homework1.R

class MainActivity : AppCompatActivity() {

    lateinit var button12: AppCompatButton
    lateinit var button13: AppCompatButton
    lateinit var clickListener: View.OnClickListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button12 = findViewById(R.id.button12)
        button13 = findViewById(R.id.button13)
    }

    override fun onStart() {
        super.onStart()
        clickListener = View.OnClickListener { p0: View? ->
            when (p0?.id) {
                R.id.button12 -> startActivity(ActivityTwo.createIntent())
                R.id.button13 -> startActivity(ActivityThree.createIntent())
            }
        }
        button12?.setOnClickListener(clickListener)
        button13?.setOnClickListener(clickListener)
    }

    companion object {
        fun createIntent(): Intent = Intent("activity1")
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
            R.id.share -> startActivity(Intent.createChooser(Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "Message to share")
                type = "text/plain"
            }, null))
        }

        return true
    }
}