package com.grgt.learnkotlin.domain.commands

/**
 * Created by JDRJ on 2017/12/11.
 */
interface Command<out T> {
    fun execute(): T
}