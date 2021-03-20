package site.budanitskaya.homework4_materialdesign



import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat


class FigureActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_figure)

        val transferredPhoto = findViewById<ImageView>(R.id.transferredPhoto)
        val bundle = intent.extras
        if (bundle != null) {
            val picture = bundle.getInt(EXTRA_COLOR)
            val drawable: Drawable = ResourcesCompat.getDrawable(resources, picture, null)!!

            transferredPhoto.setImageResource(picture)
        }
    }

    companion object {

        private const val EXTRA_COLOR = "EXTRA_COLOR"

        fun getStartIntent(context: Context, figure: Int) =
            Intent(context, FigureActivity::class.java).apply {
                putExtra(EXTRA_COLOR, figure)
            }
    }
}