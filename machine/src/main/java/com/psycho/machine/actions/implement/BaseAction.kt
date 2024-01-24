package com.psycho.machine.actions.implement

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.psycho.machine.CircuitKey
import com.psycho.machine.CircuitResource
import com.psycho.machine.actions.implement.param_collectors.ParamCollectors
import com.psycho.machine.actions.interfaces.IBaseAction
import com.psycho.machine.actions.interfaces.IActionListener
import com.psycho.machine.activities.implement.BaseActivity

abstract class BaseAction(clazz: Class<out Activity>): IBaseAction {
    private val mClazz: Class<out Activity> = clazz
    private var mCurrentActivity: BaseActivity? = null
    private var mListener: IActionListener? = null

    protected val mCircuitResource: CircuitResource = CircuitResource.getInstance()

    val parameters: ParamCollectors = ParamCollectors()

    override fun setCurrentActivity(currentActivity: BaseActivity?) {
        mCurrentActivity = currentActivity
    }

    override fun setActionListener(onIActionListener: IActionListener) {
        mListener = onIActionListener
    }

    override fun terminate() {
        mListener = null
        parameters.clear()
        if (mCurrentActivity?.isFinishing == false) {
            mCurrentActivity?.finish()
            mCurrentActivity = null
        }
    }

    override fun start(context: Context) {
        mCircuitResource.setCurrentAction(this)
        mListener?.onActionStart(this)
        Intent(context, mClazz).also { target ->
            target.putExtra(CircuitKey.IMPORT_PARAMETERS, parameters.toString())
            context.startActivity(target)
        }
    }

    override fun stop() {
        mCircuitResource.setCurrentAction(null)
        mListener?.onActionStop(this)
    }
}