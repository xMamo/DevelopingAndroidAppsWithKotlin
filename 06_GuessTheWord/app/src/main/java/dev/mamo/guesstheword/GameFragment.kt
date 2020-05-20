package dev.mamo.guesstheword

import android.os.*
import android.view.*
import androidx.databinding.*
import androidx.fragment.app.*
import androidx.lifecycle.*
import androidx.navigation.fragment.NavHostFragment.*
import dev.mamo.guesstheword.databinding.*

class GameFragment : Fragment() {
	private lateinit var binding: GameFragmentBinding

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		binding = DataBindingUtil.inflate(
				inflater,
				R.layout.game_fragment,
				container,
				false
		)

		val viewModel = ViewModelProvider(this).get(GameViewModel::class.java)
		binding.gameViewModel = viewModel
		binding.lifecycleOwner = this

		viewModel.eventGameFinish.observe(viewLifecycleOwner, Observer {
			if (it) {
				val action = GameFragmentDirections.actionGameToScore(viewModel.score.value ?: 0)
				findNavController(this).navigate(action)
			}
		})

		viewModel.eventBuzz.observe(viewLifecycleOwner, Observer {
			if (it != BuzzType.NO_BUZZ) {
				val buzzer = activity?.getSystemService(Vibrator::class.java)
				if (buzzer !== null) {
					buzzer.vibrate(it.pattern, -1)
				}
			}
		})

		return binding.root
	}
}