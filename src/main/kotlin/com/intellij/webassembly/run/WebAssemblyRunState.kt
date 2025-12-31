package com.intellij.webassembly.run

import com.intellij.execution.DefaultExecutionResult
import com.intellij.execution.ExecutionResult
import com.intellij.execution.Executor
import com.intellij.execution.configurations.CommandLineState
import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.execution.process.OSProcessHandler
import com.intellij.execution.process.ProcessHandler
import com.intellij.execution.process.ProcessHandlerFactory
import com.intellij.execution.process.ProcessTerminatedListener
import com.intellij.execution.runners.ExecutionEnvironment
import com.intellij.execution.runners.ProgramRunner
import com.intellij.execution.ui.ConsoleView
import com.intellij.execution.ui.ConsoleViewContentType
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.util.io.FileUtil
import java.io.File

class WebAssemblyRunState(
    environment: ExecutionEnvironment,
    private val configuration: WebAssemblyRunConfiguration
) : CommandLineState(environment) {

    override fun startProcess(): ProcessHandler {
        val watFile = File(configuration.watFilePath)
        if (!watFile.exists()) {
            throw IllegalStateException("WAT file not found: ${configuration.watFilePath}")
        }

        // Compile WAT to WASM
        val wasmFile = File(watFile.parentFile, watFile.nameWithoutExtension + ".wasm")
        val compileResult = compileWatToWasm(watFile, wasmFile)

        if (!compileResult.success) {
            throw IllegalStateException("Failed to compile WAT to WASM: ${compileResult.error}")
        }

        // Execute the WASM file
        return executeWasm(wasmFile)
    }

    private fun compileWatToWasm(watFile: File, wasmFile: File): CompileResult {
        val result = com.intellij.webassembly.lang.WebAssemblyTools.compileWatToWasm(watFile, wasmFile)
        return CompileResult(result.success, result.error)
    }

    private fun executeWasm(wasmFile: File): ProcessHandler {
        // Create a Node.js script to execute the WASM file
        val nodeScript = createNodeExecutionScript(wasmFile)
        val commandLine = GeneralCommandLine("node", nodeScript.absolutePath)
        commandLine.withWorkDirectory(wasmFile.parentFile)

        val processHandler = ProcessHandlerFactory.getInstance().createColoredProcessHandler(commandLine)
        ProcessTerminatedListener.attach(processHandler)
        return processHandler
    }

    private fun createNodeExecutionScript(wasmFile: File): File {
        val script = FileUtil.createTempFile("wasm-runner", ".js", true)
        script.writeText("""
            const fs = require('fs');
            const path = require('path');

            async function runWasm() {
                try {
                    const wasmPath = '${wasmFile.absolutePath.replace("\\", "\\\\")}';
                    const wasmBuffer = fs.readFileSync(wasmPath);
                    const wasmModule = await WebAssembly.compile(wasmBuffer);

                    // Create imports object (empty for now, can be extended)
                    const imports = {
                        env: {
                            // Common WASI-like imports
                            abort: () => { throw new Error('abort called'); },
                        }
                    };

                    const instance = await WebAssembly.instantiate(wasmModule, imports);

                    // Try to call main() function
                    if (instance.exports.main) {
                        console.log('Calling main()...');
                        const result = instance.exports.main();
                        console.log('Result:', result);
                        console.log('main() executed successfully');
                    } else {
                        console.log('Available exports:', Object.keys(instance.exports).join(', '));
                        console.error('No main() function found in WASM module');
                        process.exit(1);
                    }
                } catch (error) {
                    console.error('Error executing WASM:', error.message);
                    if (error.stack) console.error(error.stack);
                    process.exit(1);
                }
            }

            runWasm();
        """.trimIndent())
        return script
    }

    override fun execute(executor: Executor, runner: ProgramRunner<*>): ExecutionResult {
        val processHandler = startProcess()
        val console = createConsole(executor)
        console?.attachToProcess(processHandler)
        return DefaultExecutionResult(console, processHandler)
    }

    private data class CompileResult(val success: Boolean, val error: String?)
}
