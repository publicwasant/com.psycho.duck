package com.psycho.machine.actions.implement.param_collectors

import android.util.Log
import com.google.gson.Gson
import com.psycho.machine.actions.interfaces.param_collectors.IParamCollectors

class ParamCollectors(): IParamCollectors {
    private val mParamDataSet: ArrayList<ParamData> = arrayListOf()

    constructor(vararg paramData: ParamData): this() {
        for (tempParamData in paramData) {
            set(tempParamData)
        }
    }

    override fun set(sourceFromJson: String) {
        try {
            val tempParamDataSet = jsonFactoryByGson.fromJson(sourceFromJson, ArrayList::class.java)
            for (tempParamData in tempParamDataSet) {
                val paramData: ParamData = jsonFactoryByGson.fromJson(tempParamData.toString(), ParamData::class.java)?: continue
                set(paramData)
            }
        } catch (e: Exception) {
            Log.println(Log.ERROR, Companion::class.java.simpleName, e.stackTraceToString())
        }
    }

    override fun set(paramCollectors: IParamCollectors) {
        set(paramCollectors.get())
    }

    override fun set(paramData: ParamData) {
        val index = mParamDataSet.indexOfFirst { it.key == paramData.key }
        if (index == -1) {
            mParamDataSet.add(paramData)
        } else {
            mParamDataSet[index] = paramData
        }
    }

    override fun set(paramDataList: List<ParamData>) {
        for (paramData in paramDataList) {
            set(paramData)
        }
    }

    override fun set(key: String, value: Any?) {
        val tempParamData = ParamData(key, value)
        val index = mParamDataSet.indexOfFirst { it.key == key }
        if (index == -1) {
            mParamDataSet.add(tempParamData)
        } else {
            mParamDataSet[index] = tempParamData
        }
    }

    override fun get(): ArrayList<ParamData> {
        return mParamDataSet
    }

    override fun get(key: String): Any? {
        return mParamDataSet.find { it.key == key }?.value
    }

    override fun <T> get(key: String, clazz: Class<T>): T? {
        val paramData = mParamDataSet.find { it.key == key }?: return null
        return try {
            jsonFactoryByGson.fromJson(jsonFactoryByGson.toJson(paramData.value), clazz) as T
        } catch (_: Exception) {
            null
        }
    }

    override fun clear() {
        mParamDataSet.clear()
    }

    override fun toString(): String {
        val jsonFactoryByGson = Gson()
        val paramDataList: ArrayList<String> = arrayListOf()
        for (tempParamData in mParamDataSet) {
            paramDataList.add(tempParamData.toString())
        }
        return jsonFactoryByGson.toJson(paramDataList)
    }

    companion object {
        val jsonFactoryByGson = Gson()
    }
}