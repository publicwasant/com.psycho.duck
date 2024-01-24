package com.psycho.machine.circuits.implement

import android.content.Context
import com.psycho.machine.CircuitCode
import com.psycho.machine.CircuitKey
import com.psycho.machine.actions.implement.BaseAction
import com.psycho.machine.actions.implement.param_collectors.ParamCollectors
import com.psycho.machine.actions.interfaces.IActionListener
import com.psycho.machine.CircuitResource
import com.psycho.machine.circuits.interfaces.IBaseCircuit

abstract class BaseCircuits(val context: Context): IBaseCircuit, IActionListener {
    private val mStateBinderSet: ArrayList<StateBinder> = arrayListOf()
    private var mStartState: String? = null
    private var mCurrentState: String? = null
    private var mResults: ((ParamCollectors) -> Unit)? = null

    protected val mCircuitResource: CircuitResource = CircuitResource.getInstance()

    val currentState: String? get() = mCurrentState
    val parameters: ParamCollectors = ParamCollectors()

    abstract fun onBindAction()
    abstract fun onStart()

    protected fun bind(state: String, action: BaseAction) {
        mStateBinderSet.add(StateBinder(state, action))
    }

    protected fun goto(state: String?) {
        val binder: StateBinder = mStateBinderSet.find { it.state == state }?: return
        mCurrentState = binder.state
        binder.action.parameters.set(CircuitKey.FROM_STATE, state)
        binder.action.parameters.set(CircuitKey.CIRCUIT_CODE, CircuitCode.EMPTY)
        binder.action.start(context)
    }

    override fun execute() {
        onBindAction()
        onStart()
    }

    override fun terminate() {
        mCircuitResource.setCurrentAction(null)
        mStateBinderSet.forEach { it.action.terminate() }
        mStateBinderSet.clear()
        mStartState = null
        mCurrentState = null
        mResults?.let { it(parameters) }
    }

    override fun results(task: (ParamCollectors) -> Unit) {
        mResults = task
    }

    override fun setStartState(state: String) {
        mStartState = state
    }

    private data class StateBinder(
        override val state: String,
        override val action: BaseAction
    ): IBaseCircuit.IStateBinder
}