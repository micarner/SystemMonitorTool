package com.mcarner.systemmonitortool.script.scriptrunning;

import com.mcarner.systemmonitortool.SystemMonitorToolApplication;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;

@Service
public class ScriptFinder {
    //Look for new scripts in the script folder, executes them and logs the output to a table.

    //Watchfolder



    @PostConstruct
    public void init(){
        //If watchfolder doesn't exist, create it
//        ApplicationHome home = new ApplicationHome(SystemMonitorToolApplication.class);
//        File homeFolder = home.getDir();    // returns the folder where the jar is. This is what I wanted.
//        home.getSource(); // returns the jar absolute path.
        File homeFolder = new File("C:\\CODE\\SystemMonitorTool\\local");
        File watchFolder = new File(homeFolder,"scripts");

        if (!watchFolder.exists()){
            watchFolder.mkdir();
        }
    }

    //TODO: ScriptChecker
    //  - Look in a folder for scripts
    //  - Run the scripts
    //  - Log output to
}
