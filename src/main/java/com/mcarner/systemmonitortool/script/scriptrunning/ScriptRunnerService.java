package com.mcarner.systemmonitortool.script.scriptrunning;

import com.mcarner.systemmonitortool.script.Script;
import com.mcarner.systemmonitortool.script.Status;
import com.mcarner.systemmonitortool.script.scriptoutput.ScriptOutput;
import com.mcarner.systemmonitortool.script.ScriptRepository;
import com.mcarner.systemmonitortool.script.parsers.ScriptOutputParser;
import com.mcarner.systemmonitortool.script.runners.PowershellRunner;
import com.mcarner.systemmonitortool.script.scriptoutput.ScriptOutputRepository;
import com.mcarner.systemmonitortool.system.SystemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
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
    @Transactional
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

            //TODO: Since this is transactional, we need to make sure error handling is ok here.

            //Parse
//            ScriptOutputParser scriptOutputParser = new ScriptOutputParser(systemRepo,scriptOutputRepo);
            assert scriptOutput != null;
            scriptOutput = parse(scriptOutput);


            //Set Script info
            //If System is null, the systemId isn't set right.
            script.setName(scriptOutput.getScriptName());
            script.getScriptOutputs().add(scriptOutput);
            script.setSystem(scriptOutput.getSystem());




            script.setLastRan(scriptOutput.getRanAt());
            scriptRepo.save(script);

        }


    }

    public ScriptOutput parse(ScriptOutput scriptOutput) {

        //Parse stdOutArrayList
        ArrayList<String> rawScriptOutput = scriptOutput.getRawScriptOutput();
        try {
            List<String> outputString = Arrays.stream(rawScriptOutput.get(0).split("_")).toList();
            //Metric or issue
            if (outputString.get(0).equalsIgnoreCase("M")){
                scriptOutput.setScriptType(ScriptType.METRIC);
            } else if (outputString.get(0).equalsIgnoreCase("I")){
                scriptOutput.setScriptType(ScriptType.ISSUE);
            } else {
                throw new Exception("ScriptType not valid, value found: \"" + outputString.get(0) + "\"");
            }

            //Status
            if (outputString.get(1).equalsIgnoreCase(Status.OK.name())){
                scriptOutput.setStatus(Status.OK);
            } else if (outputString.get(1).equalsIgnoreCase(Status.WARN.name())){
                scriptOutput.setStatus(Status.WARN);
            } else if (outputString.get(1).equalsIgnoreCase(Status.CRIT.name())){
                scriptOutput.setStatus(Status.CRIT);
            } else if (outputString.get(1).equalsIgnoreCase(Status.UNKNOWN.name())){
                scriptOutput.setStatus(Status.UNKNOWN);
            } else {
                throw new Exception("Status was not valid: \"" + outputString.get(1) + "\"" );
            }

            //System id
            scriptOutput.setSystem(systemRepo.findSystemById(Long.parseLong(outputString.get(2))));

            //Script name
            scriptOutput.setScriptName(outputString.get(3));

            //Values
            scriptOutput.setValues(outputString.get(4));

            //Details
            scriptOutput.setDetails(outputString.get(5));

            scriptOutputRepo.save(scriptOutput);

        } catch (Exception e) {
//                throw new RuntimeException(e);
            log.error("ScriptOutput was not valid: " + scriptOutput.getFilename());
        }

        return scriptOutput;

    }

}
