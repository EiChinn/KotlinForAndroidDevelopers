package com.grgt.learnkotlin.extensions

import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.SelectQueryBuilder

/**
 * Created by JDRJ on 2018/3/7.
 */
fun <T : Any> SelectQueryBuilder.parseList(parser: (Map<String, Any?>) -> T): List<T> = parseList (object : MapRowParser<T>{
    override fun parseRow(columns: Map<String, Any?>): T = parser(columns)
})

fun <T : Any> SelectQueryBuilder.parseOpt(parser: (Map<String, Any?>) -> T): T? = parseOpt(object : MapRowParser<T> {
    override fun parseRow(columns: Map<String, Any?>): T = parser(columns)
})

fun SelectQueryBuilder.byId(id: Long): SelectQueryBuilder = whereSimple("_id= ?", id.toString())

fun SQLiteDatabase.clear(tableName: String) {
    execSQL("delete from $tableName")
}
