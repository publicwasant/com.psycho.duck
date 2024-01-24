package com.psycho.machine.activities.implement

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.psycho.machine.CircuitKey
import com.psycho.machine.CircuitResource
import com.psycho.machine.actions.implement.param_collectors.ParamCollectors
import com.psycho.machine.activities.interfaces.IBaseActivity

abstract class BaseActivity: IBaseActivity, ComponentActivity() {
    protected val mCircuitResource: CircuitResource = CircuitResource.getInstance()

    override val importParameters: ParamCollectors = ParamCollectors()
    override val exportParameters: ParamCollectors = ParamCollectors()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val importParametersFromExtra: String? = intent.getStringExtra(CircuitKey.IMPORT_PARAMETERS)
        if (!importParametersFromExtra.isNullOrEmpty())
            importParameters.set(importParametersFromExtra)
        mCircuitResource.getCurrentAction()?.setCurrentActivity(this)
    }

    override fun finish() {
        importParameters.clear()
        mCircuitResource.getCurrentAction()?.parameters?.set(exportParameters)
        mCircuitResource.getCurrentAction()?.stop()
        super.finish()
    }
}