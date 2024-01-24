package com.psycho.machine.actions.interfaces

import android.content.Context
import com.psycho.machine.activities.implement.BaseActivity

interface IBaseAction {
    fun setCurrentActivity(currentActivity: BaseActivity?)
    fun setActionListener(onIActionListener: IActionListener)
    fun terminate()
    fun start(context: Context)
    fun stop()
}