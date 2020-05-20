package dev.mamo.guesstheword

import android.os.*
import android.view.*
import androidx.databinding.*
import androidx.fragment.app.*
import androidx.navigation.fragment.*
import dev.mamo.guesstheword.databinding.*

class ScoreFragment : Fragment() {
	private val args by navArgs<ScoreFragmentArgs>()

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		val binding: ScoreFragmentBinding = DataBindingUtil.inflate(
				inflater,
				R.layout.score_fragment,
				container,
				false
		)

		binding.score = args.score
		binding.playAgainButton.setOnClickListener {
			findNavController().navigate(ScoreFragmentDirections.actionRestart())
		}

		return binding.root
	}
}