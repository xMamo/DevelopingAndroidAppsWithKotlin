package dev.mamo.guesstheword

enum class BuzzType(val pattern: LongArray) {
	NO_BUZZ(longArrayOf(0)),
	CORRECT(longArrayOf(100, 100, 100, 100, 100, 100)),
	COUNTDOWN_PANIC(longArrayOf(0, 200)),
	GAME_OVER(longArrayOf(0, 2000))
}