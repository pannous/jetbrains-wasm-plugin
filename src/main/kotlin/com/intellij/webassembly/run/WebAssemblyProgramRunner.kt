package com.intellij.webassembly.run

import com.intellij.execution.configurations.RunProfile
import com.intellij.execution.executors.DefaultRunExecutor
import com.intellij.execution.runners.DefaultProgramRunner

class WebAssemblyProgramRunner : DefaultProgramRunner() {
    override fun getRunnerId(): String = "WebAssemblyRunner"

    override fun canRun(executorId: String, profile: RunProfile): Boolean {
        return DefaultRunExecutor.EXECUTOR_ID == executorId &&
               profile is WebAssemblyRunConfiguration
    }
}
