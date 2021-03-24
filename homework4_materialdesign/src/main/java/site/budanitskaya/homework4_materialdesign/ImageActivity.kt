package site.budanitskaya.homework4_materialdesign

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


class ImageActivity : AppCompatActivity() {

    private var picture: Int? = null
    private lateinit var passedPhoto: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_figure)

        passedPhoto = findViewById(R.id.passed_photo)
        val bundle = intent.extras
        if (bundle != null) {
            picture = bundle.getInt(EXTRA_DRAWABLE)
        }
    }

    override fun onStart() {
        super.onStart()
        passedPhoto.setImageResource(picture!!)
    }

    companion object {

        private const val EXTRA_DRAWABLE = "EXTRA_DRAWABLE"

        fun getStartIntent(context: Context, figure: Int) =
            Intent(context, ImageActivity::class.java).apply {
                putExtra(EXTRA_DRAWABLE, figure)
            }
    }
}