package com.example.simpletelephone

import android.telecom.Call
import android.telecom.InCallService

class CallService : InCallService() {

    //receving a call
    override fun onCallAdded(call: Call?) {
        OnGoingCall.call = call
        CallActivity.start(this, call)
    }

    //finish a call
    override fun onCallRemoved(call: Call?) {
        OnGoingCall.call = null
    }
}