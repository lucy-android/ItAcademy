package site.budanitskaya.backgroundwork

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import site.budanitskaya.backgroundwork.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get the ViewModel
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)


        viewModel.performWork()
    }




}