package com.example.android.navigation

import android.os.*
import android.view.*
import androidx.fragment.app.*

class AboutFragment : Fragment() {
	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		return inflater.inflate(R.layout.fragment_about, container, false)
	}
}