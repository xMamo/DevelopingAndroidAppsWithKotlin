package dev.mamo.diceroller

import android.os.*
import androidx.appcompat.app.*
import dev.mamo.diceroller.databinding.*
import kotlin.random.*

class MainActivity : AppCompatActivity() {
	private lateinit var binding: ActivityMainBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)

		rollDice()
		binding.rollButton.setOnClickListener { rollDice() }
	}

	private fun rollDice() {
		binding.diceImage.setImageResource(when (Random.nextInt(1..6)) {
			1 -> R.drawable.dice_1
			2 -> R.drawable.dice_2
			3 -> R.drawable.dice_3
			4 -> R.drawable.dice_4
			5 -> R.drawable.dice_5
			6 -> R.drawable.dice_6
			else -> R.drawable.empty_dice
		})
	}
}