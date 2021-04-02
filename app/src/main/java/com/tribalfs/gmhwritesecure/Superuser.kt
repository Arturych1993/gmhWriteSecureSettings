package com.tribalfs.gmhwritesecure

import eu.chainfire.libsuperuser.Shell
import eu.chainfire.libsuperuser.Shell.ShellDiedException
import java.util.*

object Superuser {
    fun executeCommandSU(command: String?): String? {
        val stdout: List<String> = ArrayList()
        val stderr: List<String> = ArrayList()
        try {
            Shell.Pool.SU.run(command!!, stdout, stderr, true)
        } catch (e: ShellDiedException) {
            e.printStackTrace()
        }
        if (stdout.isEmpty()) return null
        val stringBuilder = StringBuilder()
        for (line in stdout) {
            stringBuilder.append(line).append("\n")
        }
        return stringBuilder.toString()
    }
}