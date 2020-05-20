package dev.mamo.guesstheword

import android.os.*
import android.text.format.*
import androidx.lifecycle.*

class GameViewModel : ViewModel() {
	val currentTime: LiveData<Long> get() = mutableCurrentTime
	val word: LiveData<String> get() = mutableWord
	val score: LiveData<Int> get() = mutableScore
	val eventGameFinish: LiveData<Boolean> get() = mutableEventGameFinish
	val eventBuzz: LiveData<BuzzType> get() = mutableEventBuzz

	private val timer: CountDownTimer
	private val mutableCurrentTime = MutableLiveData<Long>()
	private val mutableWord = MutableLiveData<String>()
	private val mutableScore = MutableLiveData<Int>()
	private val mutableEventGameFinish = MutableLiveData<Boolean>()
	private val mutableEventBuzz = MutableLiveData<BuzzType>()
	private lateinit var wordList: MutableList<String>

	init {
		resetList()
		nextWord()
		mutableCurrentTime.value = 60
		mutableScore.value = 0

		timer = object : CountDownTimer(60000, 1000) {
			override fun onTick(millisUntilFinished: Long) {
				mutableCurrentTime.value = (millisUntilFinished / 1000)
				if (millisUntilFinished / 1000 <= 10) {
					mutableEventBuzz.value = BuzzType.COUNTDOWN_PANIC
					mutableEventBuzz.value = BuzzType.NO_BUZZ
				}
			}

			override fun onFinish() {
				mutableCurrentTime.value = 0
				mutableEventBuzz.value = BuzzType.GAME_OVER
				mutableEventGameFinish.value = true
				mutableEventGameFinish.value = false
			}
		}

		timer.start()
	}

	override fun onCleared() {
		super.onCleared()
		timer.cancel()
	}

	fun onSkip() {
		mutableScore.value = (mutableScore.value)?.minus(1)
		nextWord()
	}

	fun onCorrect() {
		mutableScore.value = (mutableScore.value)?.plus(1)
		mutableEventBuzz.value = BuzzType.CORRECT
		nextWord()
	}

	private fun resetList() {
		wordList = mutableListOf(
				"queen",
				"hospital",
				"basketball",
				"cat",
				"change",
				"snail",
				"soup",
				"calendar",
				"sad",
				"desk",
				"guitar",
				"home",
				"railway",
				"zebra",
				"jelly",
				"car",
				"crow",
				"trade",
				"bag",
				"roll",
				"bubble"
		)
		wordList.shuffle()
	}

	private fun nextWord() {
		if (wordList.isEmpty()) {
			resetList()
		}
		mutableWord.value = wordList.removeAt(0)
	}
}