package com.psycho.machine.actions.interfaces.param_collectors

import com.psycho.machine.actions.implement.param_collectors.ParamData

interface IParamCollectors {
    fun set(sourceFromJson: String)
    fun set(paramCollectors: IParamCollectors)
    fun set(paramData: ParamData)
    fun set(paramDataList: List<ParamData>)
    fun set(key: String, value: Any?)
    fun get(): ArrayList<ParamData>
    fun get(key: String): Any?
    fun <T> get(key: String, clazz: Class<T>): T?
    fun clear()
}