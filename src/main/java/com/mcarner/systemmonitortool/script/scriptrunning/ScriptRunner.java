package com.mcarner.systemmonitortool.script.scriptrunning;


import com.mcarner.systemmonitortool.script.ScriptOutput;
import org.springframework.scheduling.annotation.Async;

public interface ScriptRunner {
//    @Async
    ScriptOutput run(String scriptPath);
}
