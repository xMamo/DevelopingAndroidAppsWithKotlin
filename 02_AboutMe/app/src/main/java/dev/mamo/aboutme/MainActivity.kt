package dev.mamo.aboutme

import android.content.*
import android.os.*
import android.view.inputmethod.*
import android.widget.*
import androidx.appcompat.app.*
import androidx.databinding.*
import dev.mamo.aboutme.databinding.*

class MainActivity : AppCompatActivity() {
	private lateinit var binding: ActivityMainBinding
	private lateinit var inputMethodManager: InputMethodManager

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
		binding.executePendingBindings()
		inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

		binding.doneButton.setOnClickListener { addNickName() }
	}

	private fun addNickName() {
		if (!Regex("""^[\s\p{C}]*$""").matches(binding.nicknameEdit.text)) {
			inputMethodManager.hideSoftInputFromWindow(binding.root.windowToken, 0)
			binding.nickname?.set(binding.nicknameEdit.text.toString())
		} else {
			Toast.makeText(this, R.string.empty_nickname_message, Toast.LENGTH_SHORT).show()
		}
	}
}