package util

import LogLevel
import Platform

class Logger(
    private val platform: Platform,
) {
    fun debug(msg: String) {
        platform.log(LogLevel.Debug, msg)
    }

    fun info(msg: String) {
        platform.log(LogLevel.Info, msg)
    }

    fun error(msg: String) {
        platform.log(LogLevel.Error, msg)
    }
}