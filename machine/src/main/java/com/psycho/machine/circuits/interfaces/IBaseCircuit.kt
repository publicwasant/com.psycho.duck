package com.psycho.machine.circuits.interfaces

import com.psycho.machine.actions.implement.param_collectors.ParamCollectors
import com.psycho.machine.actions.interfaces.IBaseAction

interface IBaseCircuit {
    interface IStateBinder {
        val state: String
        val action: IBaseAction
    }

    fun execute()
    fun terminate()
    fun results(task: (ParamCollectors) -> Unit)
    fun setStartState(state: String)
}