package site.budanitskaya.homework4recyclerview

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import site.budanitskaya.homework4recyclerview.DataUtils.Companion.EXTRA_MESSAGE
import site.budanitskaya.homework4recyclerview.databinding.ActivityColorBinding
import site.budanitskaya.homework4recyclerview.databinding.ActivityMainBinding

class ColorActivity : AppCompatActivity() {

    var pasteData: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityColorBinding>(this, R.layout.activity_color)
        val message = intent.getStringExtra(EXTRA_MESSAGE)
        val colorView = binding.colorView
        colorView.setBackgroundColor(Color.parseColor(message))

        binding.copyColor.setOnClickListener {
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip: ClipData = ClipData.newPlainText("simple text", message)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(this, "Copied to clipboard!", Toast.LENGTH_LONG).show()
        }

        binding.pasteColor.setOnClickListener {
            var clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

            val item = clipboard.primaryClip?.getItemAt(0)

            // Gets the clipboard as text.
            pasteData = item?.text as String

            binding.textField.setText(pasteData)

            Toast.makeText(this, "Fetched from clipboard!", Toast.LENGTH_LONG).show()
        }

    }
}