package com.mcarner.systemmonitortool.script.scriptrunning;

import com.mcarner.systemmonitortool.script.Script;
import com.mcarner.systemmonitortool.script.ScriptOutput;
import com.mcarner.systemmonitortool.script.ScriptRepository;
import com.mcarner.systemmonitortool.script.runners.PowershellRunner;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScriptRunnerService {

    public final ScriptRepository scriptRepo;
    public final PowershellRunner pshellRunner = new PowershellRunner();

    @Scheduled(fixedRate = 6000)
    public void runScripts(){
        //TODO: If you need performance, iterate through the table
        //Get scripts from database
        List<Script> scriptsToRun = scriptRepo.findAll();

        for (Script script :
                scriptsToRun) {
            log.info("Running script: " + script.getFilename());
            //Determine type of script (powershell, etc)
            if (script.getFilename().matches(".*?\\.ps1")){
                ScriptOutput scriptOutput = pshellRunner.run(script.getFilename());
            }

            //Execute runner
        }




    }

}
