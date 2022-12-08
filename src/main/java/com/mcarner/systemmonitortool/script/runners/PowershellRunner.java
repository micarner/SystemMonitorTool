package com.mcarner.systemmonitortool.script.runners;



import com.mcarner.systemmonitortool.script.ScriptOutput;
import com.mcarner.systemmonitortool.script.scriptrunning.ScriptFinderService;
import com.mcarner.systemmonitortool.script.scriptrunning.ScriptRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class PowershellRunner implements ScriptRunner {

    //TODO: ScriptChecker
    //  - Look in a folder for scripts
    //  - Run the scripts
    //  - Log output to

    //(In async run method)
    //Run script
    //Gather ScriptOutput
    //Save to database
    //Error handling if stuff aint workin
    @Override
    public ScriptOutput run(String scriptFilename) {
        //String command = "powershell.exe  your command";
        //Getting the version
        String command = "powershell \"" + ScriptFinderService.SCRIPTS_FOLDER + "\\" + scriptFilename + "\"";
        ScriptOutput scriptOutput = new ScriptOutput();
        try {

            // Executing the command
            Process powerShellProcess = Runtime.getRuntime().exec(command);
            // Getting the results
            powerShellProcess.getOutputStream().close();
            scriptOutput.setCompletedAt(LocalDateTime.now());

            String line;
            ArrayList<String> stdOutArrayList = new ArrayList<>();
            ArrayList<String> stdErrorArrayList = new ArrayList<>();

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

            //Parse stdOutArrayList
            //Create this part and then move it into Generic ScriptOutputParser class
            //Make this class return a string
            //Also need to make Async changes. It wants a future-like class to be returned.




        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Done");
        return null;
    }
}
