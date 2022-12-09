package com.mcarner.systemmonitortool.script.parsers;

import com.mcarner.systemmonitortool.script.scriptoutput.ScriptOutput;
import com.mcarner.systemmonitortool.script.scriptrunning.ScriptType;
import com.mcarner.systemmonitortool.script.scriptrunning.Status;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class ScriptOutputParser {

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
            scriptOutput.setSystemId(Long.parseLong(outputString.get(2)));

            //Script name
            scriptOutput.setScriptName(outputString.get(3));

            //Values
            scriptOutput.setValues(outputString.get(4));

            //Details
            scriptOutput.setDetails(outputString.get(5));



        } catch (Exception e) {
//                throw new RuntimeException(e);
            log.error("ScriptOutput was not valid: " + scriptOutput.getFilename());
        }

        return scriptOutput;

    }
}
