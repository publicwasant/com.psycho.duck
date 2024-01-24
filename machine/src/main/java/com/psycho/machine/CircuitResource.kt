package com.psycho.machine

import com.psycho.machine.actions.implement.BaseAction

class CircuitResource {
    private var mCurrentAction: BaseAction? = null

    fun setCurrentAction(currentAction: BaseAction?) {
        mCurrentAction = currentAction
    }

    fun getCurrentAction(): BaseAction? {
        return mCurrentAction
    }

    companion object {
        private var mInstance: CircuitResource? = null

        fun getInstance(): CircuitResource {
            if (mInstance == null)
                mInstance = CircuitResource()
            return mInstance!!
        }
    }
}