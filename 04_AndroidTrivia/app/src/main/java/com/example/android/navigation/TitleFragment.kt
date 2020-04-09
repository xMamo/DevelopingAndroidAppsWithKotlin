package com.example.android.navigation

import android.os.*
import android.view.*
import androidx.databinding.*
import androidx.fragment.app.*
import androidx.navigation.*
import androidx.navigation.fragment.*
import androidx.navigation.ui.*
import com.example.android.navigation.databinding.*

class TitleFragment : Fragment() {
	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		val binding: FragmentTitleBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_title, container, false)
		binding.playButton.setOnClickListener { it.findNavController().navigate(TitleFragmentDirections.actionTitleFragmentToGameFragment()) }
		setHasOptionsMenu(true)
		return binding.root
	}

	override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
		super.onCreateOptionsMenu(menu, inflater)
		inflater.inflate(R.menu.overflow_menu, menu)
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		return NavigationUI.onNavDestinationSelected(item, findNavController()) || super.onOptionsItemSelected(item)
	}
}