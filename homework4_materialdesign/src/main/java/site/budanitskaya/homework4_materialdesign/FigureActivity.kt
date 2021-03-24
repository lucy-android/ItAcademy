package site.budanitskaya.homework4_materialdesign


import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat


class FigureActivity : AppCompatActivity() {

    private lateinit var like:ImageView
    private var likeFlag = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_figure)
        like = findViewById(R.id.like)

        like.setOnClickListener{

            like.invalidate()
            like.setBackgroundResource(R.drawable.ic_baseline_favorite_24)

           /* likeFlag = if(!likeFlag){

                true
            } else {
                like.invalidate()
                like.setBackgroundResource(R.drawable.ic_baseline_favorite_border_24)
                false
            }*/

        }

        val transferredPhoto = findViewById<ImageView>(R.id.passed_photo)
        val bundle = intent.extras
        if (bundle != null) {
            val picture = bundle.getInt(EXTRA_COLOR)
            val drawable: Drawable = ResourcesCompat.getDrawable(resources, picture, null)!!

            transferredPhoto.setImageResource(picture)
        }
    }

    override fun onStart() {
        super.onStart()


    }

    companion object {

        private const val EXTRA_COLOR = "EXTRA_COLOR"

        fun getStartIntent(context: Context, figure: Int) =
            Intent(context, FigureActivity::class.java).apply {
                putExtra(EXTRA_COLOR, figure)
            }
    }
}