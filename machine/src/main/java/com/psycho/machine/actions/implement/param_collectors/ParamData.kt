package com.psycho.machine.actions.implement.param_collectors

import com.psycho.machine.actions.interfaces.param_collectors.IParamData

class ParamData(override val key: String, override val value: Any?): IParamData {
    override fun toString(): String {
        return ParamCollectors.jsonFactoryByGson.toJson(this)
    }
}