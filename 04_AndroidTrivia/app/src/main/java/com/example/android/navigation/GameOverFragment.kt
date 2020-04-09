package com.example.android.navigation

import android.os.*
import android.view.*
import androidx.databinding.*
import androidx.fragment.app.*
import androidx.navigation.*
import com.example.android.navigation.databinding.*

class GameOverFragment : Fragment() {
	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		val binding = DataBindingUtil.inflate<FragmentGameOverBinding>(inflater, R.layout.fragment_game_over, container, false)
		binding.tryAgainButton.setOnClickListener { it.findNavController().navigate(GameOverFragmentDirections.actionGameOverFragmentToGameFragment()) }
		return binding.root
	}
}