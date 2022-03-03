package com.codingtroops.repositories

import android.os.CountDownTimer
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

class CustomCountdown(
    private val onTick: ((currentValue: Int) -> Unit),
    private val onFinish: (() -> Unit),
): DefaultLifecycleObserver {
    var timer: InternalTimer = InternalTimer(
        onTick = onTick,
        onFinish = onFinish,
        millisInFuture = 60000,
        countDownInterval = 1000
    )
    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        if (timer.lastKnownTime > 0) {
            timer.cancel()
            timer = InternalTimer(
                onTick = onTick,
                onFinish = onFinish,
                millisInFuture = timer.lastKnownTime,
                countDownInterval = 1000)
        }
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        timer.cancel()
    }

    fun stop() {
        timer.cancel()
    }

    class InternalTimer(
        private val onTick: ((currentValue: Int) -> Unit),
        private val onFinish: (() -> Unit),
        millisInFuture: Long,
        countDownInterval: Long
    ) : CountDownTimer(millisInFuture, countDownInterval) {
        var lastKnownTime: Long = millisInFuture

        init {
            this.start()
        }

        override fun onFinish() {
            lastKnownTime = 0
            onFinish.invoke()
        }

        override fun onTick(millisUntilFinished: Long) {
            lastKnownTime = millisUntilFinished
            onTick(millisUntilFinished.toInt())
        }
    }
}

