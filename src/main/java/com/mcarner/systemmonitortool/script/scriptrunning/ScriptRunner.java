package com.mcarner.systemmonitortool.script.scriptrunning;


import com.mcarner.systemmonitortool.script.scriptoutput.ScriptOutput;
import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.Future;

public interface ScriptRunner {

    @Async
    Future<ScriptOutput> run(String scriptPath);
}
