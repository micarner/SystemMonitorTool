package com.mcarner.systemmonitortool.script.scriptrunning;

import com.mcarner.systemmonitortool.script.Script;
import com.mcarner.systemmonitortool.script.Status;
import com.mcarner.systemmonitortool.script.scriptoutput.ScriptOutput;
import com.mcarner.systemmonitortool.script.ScriptRepository;
import com.mcarner.systemmonitortool.script.runners.PowershellRunner;
import com.mcarner.systemmonitortool.script.scriptoutput.ScriptOutputInvalidException;
import com.mcarner.systemmonitortool.script.scriptoutput.ScriptOutputRepository;
import com.mcarner.systemmonitortool.system.SystemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;

import static com.mcarner.systemmonitortool.script.scriptrunning.ScriptFinderService.SCRIPTS_FOLDER;

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

            //Does script still exist?
            File file = new File(SCRIPTS_FOLDER + "\\" + script.getFilename());
            if (!file.exists()){
                log.warn("Script file not found: " + script.getFilename());
                continue;
            }

            log.info("Running script: " + script.getFilename());
            Future<ScriptOutput> rawScriptOutputFuture = null;
            ScriptOutput initialScriptOutput = null;
            //Determine type of script (powershell, etc)
            if (script.getFilename().matches(".*?\\.ps1")){
                rawScriptOutputFuture = pshellRunner.run(script.getFilename());
            }


            //Did something go wrong?
            try {
                assert rawScriptOutputFuture != null;
                initialScriptOutput = rawScriptOutputFuture.get();
            } catch (Exception e) {
//                throw new RuntimeException(e);
                log.error(e.getLocalizedMessage());
            }

            //TODO: Since this is transactional, we need to make sure error handling is ok here.

            //Parse
//            ScriptOutputParser scriptOutputParser = new ScriptOutputParser(systemRepo,scriptOutputRepo);
            assert initialScriptOutput != null;
            initialScriptOutput.setScript(script);
            ArrayList<ScriptOutput> scriptOutputArrayList = parseAndSave(initialScriptOutput);


            //Set Script info
            //If System is null, the systemId isn't set right.
            script.setName(initialScriptOutput.getScriptName());
            script.setScriptOutputs(scriptOutputArrayList);


            script.setLastRan(initialScriptOutput.getRanAt());
            scriptRepo.save(script);

        }


    }

    public ArrayList<ScriptOutput> parseAndSave(ScriptOutput initialScriptOutput) {

        ArrayList<ScriptOutput> parsedScriptOutputs = new ArrayList<>();

        //Parse stdOutArrayList
        ArrayList<String> rawScriptOutputs = initialScriptOutput.getRawScriptOutput();
        for (String rawScriptOutput :
                rawScriptOutputs) {

            //Set rawScriptOutput for this single instance of ScriptOutput
            ScriptOutput parsedScriptOutput = new ScriptOutput();
            ArrayList<String> singleRawScriptOutput = new ArrayList<>();
            singleRawScriptOutput.add(rawScriptOutput);
            parsedScriptOutput.setRawScriptOutput(singleRawScriptOutput);

            try {
                List<String> outputString = Arrays.stream(rawScriptOutput.split("_")).toList();
                //Metric or issue
                if (outputString.get(0).equalsIgnoreCase("M")) {
                    parsedScriptOutput.setScriptType(ScriptType.METRIC);
                } else if (outputString.get(0).equalsIgnoreCase("I")) {
                    parsedScriptOutput.setScriptType(ScriptType.ISSUE);
                } else {
                    throw new ScriptOutputInvalidException("ScriptType not valid, value found: \"" + outputString.get(0) + "\"");
                }

                //Status
                if (outputString.get(1).equalsIgnoreCase(Status.OK.name())) {
                    parsedScriptOutput.setStatus(Status.OK);
                } else if (outputString.get(1).equalsIgnoreCase(Status.WARN.name())) {
                    parsedScriptOutput.setStatus(Status.WARN);
                } else if (outputString.get(1).equalsIgnoreCase(Status.CRIT.name())) {
                    parsedScriptOutput.setStatus(Status.CRIT);
                } else if (outputString.get(1).equalsIgnoreCase(Status.UNKNOWN.name())) {
                    parsedScriptOutput.setStatus(Status.UNKNOWN);
                } else {
                    throw new ScriptOutputInvalidException("Status was not valid: \"" + outputString.get(1) + "\"");
                }

                //System id
                parsedScriptOutput.setSystem(systemRepo.findSystemById(Long.parseLong(outputString.get(2))));

                //Script name
                parsedScriptOutput.setScriptName(outputString.get(3));

                //Values
                parsedScriptOutput.setValues(outputString.get(4));

                //Details
                parsedScriptOutput.setDetails(outputString.get(5));

                parsedScriptOutput.setRanAt(initialScriptOutput.getRanAt());
                parsedScriptOutput.setCompletedAt(initialScriptOutput.getCompletedAt());
                parsedScriptOutput.setFilename(initialScriptOutput.getFilename());
                parsedScriptOutput.setScript(initialScriptOutput.getScript());

//                scriptOutputRepo.save(initialScriptOutput);
                parsedScriptOutputs.add(parsedScriptOutput);

            } catch (ScriptOutputInvalidException e){
//                log.error("ScriptOutput was not valid: " + initialScriptOutput.getFilename());
                //Not valid, ignore (change later?)
            } catch (Exception e) {
//                throw new RuntimeException(e);
                log.error("ScriptOutput was not valid: " + initialScriptOutput.getFilename());
            }
        }

//        scriptOutputRepo.saveAll(parsedScriptOutputs);









        return parsedScriptOutputs;

    }

}
