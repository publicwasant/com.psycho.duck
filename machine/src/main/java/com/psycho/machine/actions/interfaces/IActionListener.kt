package com.psycho.machine.actions.interfaces

import com.psycho.machine.actions.implement.BaseAction

interface IActionListener {
    fun onActionStart(action: BaseAction)
    fun onActionStop(action: BaseAction)
}