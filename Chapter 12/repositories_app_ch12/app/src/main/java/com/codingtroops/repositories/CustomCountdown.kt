package com.codingtroops.repositories

import android.os.CountDownTimer

class CustomCountdown(
    private val onTick: ((currentValue: Int) -> Unit),
    private val onFinish: (() -> Unit),
) {
    private val timer: InternalTimer = InternalTimer(
        onTick = onTick,
        onFinish = onFinish,
        millisInFuture = 60000,
        countDownInterval = 1000
    )

    fun stop() {
        timer.cancel()
    }

    private class InternalTimer(
        private val onTick: ((currentValue: Int) -> Unit),
        private val onFinish: (() -> Unit),
        millisInFuture: Long,
        countDownInterval: Long
    ) : CountDownTimer(millisInFuture, countDownInterval) {

        init {
            this.start()
        }

        override fun onFinish() {
            onFinish.invoke()
        }

        override fun onTick(millisUntilFinished: Long) {
            onTick(millisUntilFinished.toInt())
        }
    }
}

