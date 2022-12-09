package com.mcarner.systemmonitortool.script.runners;



import com.mcarner.systemmonitortool.script.scriptoutput.ScriptOutput;
import com.mcarner.systemmonitortool.script.scriptrunning.ScriptFinderService;
import com.mcarner.systemmonitortool.script.scriptrunning.ScriptRunner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.AsyncResult;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.Future;

@Slf4j
public class PowershellRunner implements ScriptRunner {

    //TODO: ScriptChecker
    //  - Look in a folder for scripts
    //  - Run the scripts
    //  - Log output to

    public String pathToPowershell = "C:\\Program Files\\PowerShell\\7\\pwsh.exe";

    //(In async run method)
    //Run script
    //Gather ScriptOutput
    //Save to database
    //Error handling if stuff aint workin
    @Override
    public Future<ScriptOutput> run(String scriptFilename) {
        //String command = "powershell.exe  your command";
        //Getting the version
        String command = "\"" + pathToPowershell + "\" \"" + ScriptFinderService.SCRIPTS_FOLDER + "\\" + scriptFilename + "\"";
        ScriptOutput scriptOutput = new ScriptOutput();
        scriptOutput.setFilename(scriptFilename);
        String line;
        ArrayList<String> stdOutArrayList = new ArrayList<>();
        ArrayList<String> stdErrorArrayList = new ArrayList<>();
        try {
            // Executing the command
            Process powerShellProcess = Runtime.getRuntime().exec(command);
            // Getting the results
            powerShellProcess.getOutputStream().close();
            scriptOutput.setCompletedAt(LocalDateTime.now());

//            System.out.println("Standard Output:");
            BufferedReader stdout = new BufferedReader(new InputStreamReader(
                    powerShellProcess.getInputStream()));
            while ((line = stdout.readLine()) != null) {
//                System.out.println(line);
                stdOutArrayList.add(line);
            }
            stdout.close();
//            System.out.println("Standard Error:");
            BufferedReader stderr = new BufferedReader(new InputStreamReader(
                    powerShellProcess.getErrorStream()));
            while ((line = stderr.readLine()) != null) {
//                System.out.println(line);
                stdErrorArrayList.add(line);
            }
            stderr.close();

            //Parse stdErrorArraylist, it should be empty
            if (stdErrorArrayList.size() > 0){
                //Something wrong happened
                //Throw error
            } else if (stdOutArrayList.size() != 1){
                //Something wrong happened
                //Throw error
            }

            scriptOutput.setRawScriptOutput(stdOutArrayList);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new AsyncResult<>(scriptOutput);

    }
}
