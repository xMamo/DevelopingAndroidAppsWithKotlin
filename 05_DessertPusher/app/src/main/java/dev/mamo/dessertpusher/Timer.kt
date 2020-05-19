package dev.mamo.dessertpusher

import android.os.*
import androidx.lifecycle.*
import timber.log.*
import java.util.concurrent.atomic.*

class Timer : LifecycleObserver {
	var time: Int
		get() = atomicTime.get()
		set(value) = atomicTime.set(value)

	private var atomicTime = AtomicInteger()
	private var handler = Handler()
	private lateinit var runnable: Runnable

	@OnLifecycleEvent(Lifecycle.Event.ON_START)
	fun start() {
		runnable = Runnable {
			val time = atomicTime.incrementAndGet()
			Timber.i("Timer is at : $time")
			handler.postDelayed(runnable, 1000)
		}

		handler.postDelayed(runnable, 1000)
	}

	@OnLifecycleEvent(Lifecycle.Event.ON_STOP)
	fun stop() {
		handler.removeCallbacks(runnable)
	}
}