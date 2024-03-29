package site.budanitskaya.homework4recyclerview

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import site.budanitskaya.homework4recyclerview.DataUtils.Companion.INTENT_FLAG
import site.budanitskaya.homework4recyclerview.databinding.ActivityColorBinding


class ColorActivity : AppCompatActivity() {

    private var pasteData: String = ""
    private lateinit var clipboard: ClipboardManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding =
            DataBindingUtil.setContentView<ActivityColorBinding>(this, R.layout.activity_color)

        val stringForColorFromRecyclerView = intent.getStringExtra(INTENT_FLAG)
        val colorBackground = binding.colorBackground

        colorBackground.setBackgroundColor(Color.parseColor(stringForColorFromRecyclerView))

        // button to copy color data to clipboard

        binding.copyColorButton.setOnClickListener {

            clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip: ClipData =
                ClipData.newPlainText("Color Code", stringForColorFromRecyclerView)
            clipboard.setPrimaryClip(clip)

            Toast.makeText(this, getString(R.string.copied), Toast.LENGTH_LONG).show()
        }

        // button to paste color data from clip

        binding.pasteColorButton.setOnClickListener {

            clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipBoardItem = clipboard.primaryClip?.getItemAt(0)
            pasteData = clipBoardItem?.text as String

            binding.textField.setText(pasteData)

            Toast.makeText(this, getString(R.string.fetched), Toast.LENGTH_LONG).show()
        }
    }
}