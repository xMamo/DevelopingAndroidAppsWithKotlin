package dev.mamo.dessertpusher

import android.content.*
import android.os.*
import android.view.*
import android.widget.*
import androidx.appcompat.app.*
import androidx.core.app.*
import androidx.databinding.*
import androidx.lifecycle.*
import dev.mamo.dessertpusher.databinding.*
import timber.log.*

const val KEY_REVENUE = "revenue"
const val KEY_DESSERTS_SOLD = "dessertSold"
const val KEY_TIME = "time"

class MainActivity : AppCompatActivity(), LifecycleObserver {
	private lateinit var timer: Timer
	private lateinit var binding: ActivityMainBinding

	private val desserts = listOf(
			Dessert(R.drawable.cupcake, 5, 0),
			Dessert(R.drawable.donut, 10, 5),
			Dessert(R.drawable.eclair, 15, 20),
			Dessert(R.drawable.froyo, 30, 50),
			Dessert(R.drawable.gingerbread, 50, 100),
			Dessert(R.drawable.honeycomb, 100, 200),
			Dessert(R.drawable.icecreamsandwich, 500, 500),
			Dessert(R.drawable.jellybean, 1000, 1000),
			Dessert(R.drawable.kitkat, 2000, 2000),
			Dessert(R.drawable.lollipop, 3000, 4000),
			Dessert(R.drawable.marshmallow, 4000, 8000),
			Dessert(R.drawable.nougat, 5000, 16000),
			Dessert(R.drawable.oreo, 6000, 20000)
	)
	private var currentDessert = desserts[0]

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		Timber.i("onCreate Called")

		binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
		binding.dessertButton.setOnClickListener { onDessertClicked() }
		binding.revenue = 0
		binding.amountSold = 0
		binding.dessertButton.setImageResource(currentDessert.imageId)

		timer = Timer()
		lifecycle.addObserver(timer)
	}

	override fun onCreateOptionsMenu(menu: Menu): Boolean {
		menuInflater.inflate(R.menu.main_menu, menu)
		return super.onCreateOptionsMenu(menu)
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		when (R.id.shareMenuButton) {
			R.id.shareMenuButton -> onShare()
		}
		return super.onOptionsItemSelected(item)
	}

	override fun onSaveInstanceState(outState: Bundle) {
		Timber.i("onSaveInstanceState Called")
		outState.putInt(KEY_REVENUE, binding.revenue ?: 0)
		outState.putInt(KEY_DESSERTS_SOLD, binding.amountSold ?: 0)
		outState.putInt(KEY_TIME, timer.time)
		super.onSaveInstanceState(outState)
	}

	override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
		super.onRestoreInstanceState(savedInstanceState)
		Timber.i("onRestoreInstanceState Called")

		if (savedInstanceState != null) {
			binding.revenue = savedInstanceState.getInt(KEY_REVENUE, 0)
			binding.amountSold = savedInstanceState.getInt(KEY_DESSERTS_SOLD, 0)
			timer.time = savedInstanceState.getInt(KEY_TIME, 0)
			showCurrentDessert()
		}
	}

	override fun onStart() {
		super.onStart()
		Timber.i("onStart Called")
	}

	override fun onResume() {
		super.onResume()
		Timber.i("onResume Called")
	}

	override fun onPause() {
		super.onPause()
		Timber.i("onPause Called")
	}

	override fun onStop() {
		super.onStop()
		Timber.i("onStop Called")
	}

	override fun onDestroy() {
		super.onDestroy()
		Timber.i("onDestroy Called")
	}

	override fun onRestart() {
		super.onRestart()
		Timber.i("onRestart Called")
	}

	private fun onDessertClicked() {
		binding.revenue = (binding.revenue ?: 0) + currentDessert.price
		binding.amountSold = (binding.amountSold ?: 0) + 1
		showCurrentDessert()
	}

	private fun showCurrentDessert() {
		val amountSold = binding.amountSold ?: 0
		var newDessert = desserts[0]

		for (dessert in desserts) {
			if (amountSold >= dessert.startProductionAmount) {
				newDessert = dessert
			} else {
				break
			}
		}

		currentDessert = newDessert
		binding.dessertButton.setImageResource(newDessert.imageId)
	}

	private fun onShare() {
		try {
			startActivity(ShareCompat.IntentBuilder.from(this)
					.setText(getString(R.string.share_text, binding.amountSold, binding.revenue))
					.setType("text/plain")
					.intent)
		} catch (_: ActivityNotFoundException) {
			Toast.makeText(this, getString(R.string.sharing_not_available), Toast.LENGTH_LONG).show()
		}
	}
}