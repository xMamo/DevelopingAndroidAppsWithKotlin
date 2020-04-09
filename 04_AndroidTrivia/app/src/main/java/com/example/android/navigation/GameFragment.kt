package com.example.android.navigation

import android.os.*
import android.view.*
import androidx.appcompat.app.*
import androidx.databinding.*
import androidx.fragment.app.*
import androidx.navigation.*
import com.example.android.navigation.databinding.*
import kotlin.math.*

class GameFragment : Fragment() {
	data class Question(val text: String, val answers: List<String>)

	lateinit var question: ObservableField<Question>
	lateinit var answers: ObservableField<List<String>>

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		val questions = listOf(
				Question(
						"What is Android Jetpack?",
						listOf("all of these", "tools", "documentation", "libraries")
				),
				Question(
						"Base class for Layout?",
						listOf("ViewGroup", "ViewSet", "ViewCollection", "ViewRoot")
				),
				Question(
						"Layout for complex Screens?",
						listOf("ConstraintLayout", "GridLayout", "LinearLayout", "FrameLayout")
				),
				Question(
						"Pushing structured data into a Layout?",
						listOf("Data Binding", "Data Pushing", "Set Text", "OnClick")
				),
				Question(
						"Inflate layout in fragments?",
						listOf("onCreateView", "onActivityCreated", "onCreateLayout", "onInflateLayout")
				),
				Question(
						"Build system for Android?",
						listOf("Gradle", "Graddle", "Grodle", "Groyle")
				),
				Question(
						"Android vector format?",
						listOf("VectorDrawable", "AndroidVectorDrawable", "DrawableVector", "AndroidVector")
				),
				Question(
						"Android Navigation Component?",
						listOf("NavController", "NavCentral", "NavMaster", "NavSwitcher")
				),
				Question(
						"Registers app with launcher?",
						listOf("intent-filter", "app-registry", "launcher-registry", "app-launcher")
				),
				Question(
						"Mark a layout for Data Binding?",
						listOf("<layout>", "<binding>", "<data-binding>", "<dbinding>")
				)
		).shuffled()

		val count = min((questions.size + 1) / 2, 3)
		var index = 0
		question = ObservableField(questions[0])
		answers = ObservableField(questions[0].answers.shuffled())
		(activity as AppCompatActivity).supportActionBar?.title = getString(R.string.title_android_trivia_question, 1, count)

		val binding = DataBindingUtil.inflate<FragmentGameBinding>(inflater, R.layout.fragment_game, container, false)
		binding.game = this

		binding.submitButton.setOnClickListener {
			val checkedId = binding.questionRadioGroup.checkedRadioButtonId

			if (checkedId != -1) {
				val answerIndex = when (checkedId) {
					R.id.firstAnswerRadioButton -> 0
					R.id.secondAnswerRadioButton -> 1
					R.id.thirdAnswerRadioButton -> 2
					R.id.fourthAnswerRadioButton -> 3
					else -> -1
				}

				if (answers.get()!![answerIndex] == question.get()!!.answers[0]) {
					if (++index < count) {
						question.set(questions[index])
						answers.set(questions[index].answers.shuffled())
						(activity as AppCompatActivity).supportActionBar?.title = getString(R.string.title_android_trivia_question, index + 1, count)
					} else {
						it.findNavController().navigate(GameFragmentDirections.actionGameFragmentToGameWonFragment(count, index))
					}
				} else {
					it.findNavController().navigate(GameFragmentDirections.actionGameFragmentToGameOverFragment())
				}
			}
		}

		return binding.root
	}
}