package com.psycho.machine.activities.interfaces

import com.psycho.machine.actions.implement.param_collectors.ParamCollectors

interface IBaseActivity  {
    val importParameters: ParamCollectors
    val exportParameters: ParamCollectors
}