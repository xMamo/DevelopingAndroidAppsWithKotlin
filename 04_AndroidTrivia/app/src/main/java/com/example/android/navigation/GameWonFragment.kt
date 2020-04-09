package com.example.android.navigation

import android.content.*
import android.os.*
import android.view.*
import androidx.core.app.*
import androidx.databinding.*
import androidx.fragment.app.*
import androidx.navigation.*
import com.example.android.navigation.databinding.*

class GameWonFragment : Fragment() {
	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		val binding = DataBindingUtil.inflate<FragmentGameWonBinding>(inflater, R.layout.fragment_game_won, container, false)
		binding.nextMatchButton.setOnClickListener { it.findNavController().navigate(GameWonFragmentDirections.actionGameWonFragmentToGameFragment()) }
		setHasOptionsMenu(true)
		return binding.root
	}

	override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
		super.onCreateOptionsMenu(menu, inflater)
		inflater.inflate(R.menu.winner_menu, menu)

		if (activity?.also { getShareIntent()?.resolveActivity(it.packageManager) } === null) {
			menu.findItem(R.id.share)?.isVisible = false
		}
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		if (item.itemId == R.id.share) {
			shareSuccess()
			return true
		} else {
			return super.onOptionsItemSelected(item)
		}
	}

	private fun shareSuccess() {
		startActivity(getShareIntent())
	}

	private fun getShareIntent(): Intent? {
		return arguments?.let {
			val args = GameWonFragmentArgs.fromBundle(it)
			ShareCompat.IntentBuilder.from(activity)
					.setText(getString(R.string.share_success_text, args.numCorrect, args.numQuestions))
					.setType("text/plain")
					.intent
		}
	}
}