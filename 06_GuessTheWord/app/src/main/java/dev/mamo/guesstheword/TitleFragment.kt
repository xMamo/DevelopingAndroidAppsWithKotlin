package dev.mamo.guesstheword

import android.os.*
import android.view.*
import androidx.databinding.*
import androidx.fragment.app.*
import androidx.navigation.fragment.*
import dev.mamo.guesstheword.databinding.*

class TitleFragment : Fragment() {
	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		val binding: TitleFragmentBinding = DataBindingUtil.inflate(
				inflater,
				R.layout.title_fragment,
				container,
				false
		)

		binding.playGameButton.setOnClickListener {
			findNavController().navigate(TitleFragmentDirections.actionTitleToGame())
		}

		return binding.root
	}
}