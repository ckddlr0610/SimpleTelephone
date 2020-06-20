package com.example.simpletelephone

import android.telecom.Call
import android.telecom.VideoProfile
import io.reactivex.subjects.BehaviorSubject
import timber.log.Timber

class OnGoingCall {
    val state : BehaviorSubject<Int> = BehaviorSubject.create()

    private val callback = object : Call.Callback() {
        override fun onStateChanged(call: Call?, newstate: Int) {
            Timber.d(call.toString())
            state.onNext(newstate)
        }
    }

    var call: Call? = null
    set(value) {
        field?.unregisterCallback(callback)
        value?.let {
            it.registerCallback(callback)
            state.onNext(it.state)
        }
        field = value
    }

    fun answer() {
        call!!.answer(VideoProfile.STATE_AUDIO_ONLY)
    }

    fun hangup() {
        call!!.disconnect()
    }
}