package dev.mamo.dessertpusher

import timber.log.*

class Application : android.app.Application() {
	override fun onCreate() {
		super.onCreate()
		Timber.plant(Timber.DebugTree())
	}
}