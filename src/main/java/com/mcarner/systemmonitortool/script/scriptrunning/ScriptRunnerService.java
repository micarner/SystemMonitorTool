package com.mcarner.systemmonitortool.script.scriptrunning;

import com.mcarner.systemmonitortool.script.Script;
import com.mcarner.systemmonitortool.script.scriptoutput.ScriptOutput;
import com.mcarner.systemmonitortool.script.ScriptRepository;
import com.mcarner.systemmonitortool.script.parsers.ScriptOutputParser;
import com.mcarner.systemmonitortool.script.runners.PowershellRunner;
import com.mcarner.systemmonitortool.script.scriptoutput.ScriptOutputRepository;
import com.mcarner.systemmonitortool.system.System;
import com.mcarner.systemmonitortool.system.SystemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Future;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScriptRunnerService {

    public final ScriptRepository scriptRepo;
    public final ScriptOutputRepository scriptOutputRepo;
    public final SystemRepository systemRepo;
    public final PowershellRunner pshellRunner = new PowershellRunner();


    @Scheduled(fixedRate = 6000)
    public void runScripts(){
        //TODO: If you need performance, iterate through the table
        //Get scripts from database
        List<Script> scriptsToRun = scriptRepo.findAll();

        for (Script script :
                scriptsToRun) {

            //Should script be ran?
            if (script.getLastRan() != null && !LocalDateTime.now().isAfter(script.getLastRan().plusNanos(script.getFrequencyToCheck() * 1000000))){
                continue;
            }

            log.info("Running script: " + script.getFilename());
            Future<ScriptOutput> rawScriptOutputFuture = null;
            ScriptOutput scriptOutput = null;
            //Determine type of script (powershell, etc)
            if (script.getFilename().matches(".*?\\.ps1")){
                rawScriptOutputFuture = pshellRunner.run(script.getFilename());
            }


            //Did something go wrong?
            try {
                scriptOutput = rawScriptOutputFuture.get();
            } catch (Exception e) {
//                throw new RuntimeException(e);
                log.error(e.getLocalizedMessage());
            }

            //Parse
            ScriptOutputParser scriptOutputParser = new ScriptOutputParser();
            scriptOutput = scriptOutputParser.parse(scriptOutput);

            //Set Script info
            //If System is null, the systemId isn't set right.
            script.setName(scriptOutput.getScriptName());
            script.getScriptOutputs().add(scriptOutput);
            script.setSystem(systemRepo.findSystemById(scriptOutput.getSystemId()));

            scriptOutputRepo.save(scriptOutput);
            script.setLastRan(scriptOutput.getRanAt());
            scriptRepo.save(script);

        }




    }

}
