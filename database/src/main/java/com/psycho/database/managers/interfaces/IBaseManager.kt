package com.psycho.database.managers.interfaces

interface IBaseManager<T> {
    fun get(): List<T>
    fun get(id: Int?): T?
    fun getLast(): T?
    fun insert(objList: List<T>)
    fun insert(obj: T)
    fun update(objList: List<T>)
    fun update(obj: T)
    fun delete(objList: List<T>)
    fun delete(obj: T)
}