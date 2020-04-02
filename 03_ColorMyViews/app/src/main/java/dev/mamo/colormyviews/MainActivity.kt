package dev.mamo.colormyviews

import android.graphics.*
import android.os.*
import androidx.appcompat.app.*
import dev.mamo.colormyviews.databinding.*

class MainActivity : AppCompatActivity() {
	private lateinit var binding: ActivityMainBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)

		binding.apply {
			root.setOnClickListener { it.setBackgroundColor(Color.LTGRAY) }
			boxOneText.setOnClickListener { it.setBackgroundColor(Color.DKGRAY) }
			boxTwoText.setOnClickListener { it.setBackgroundColor(Color.GRAY) }
			boxThreeText.setOnClickListener { it.setBackgroundResource(android.R.color.holo_green_light) }
			boxFourText.setOnClickListener { it.setBackgroundResource(android.R.color.holo_green_dark) }
			boxFiveText.setOnClickListener { it.setBackgroundResource(android.R.color.holo_green_light) }
			redButton.setOnClickListener { boxThreeText.setBackgroundResource(R.color.red) }
			yellowButton.setOnClickListener { boxFourText.setBackgroundResource(R.color.yellow) }
			greenButton.setOnClickListener { boxFiveText.setBackgroundResource(R.color.green) }
		}
	}
}