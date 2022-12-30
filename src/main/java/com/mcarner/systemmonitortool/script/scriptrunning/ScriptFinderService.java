package com.mcarner.systemmonitortool.script.scriptrunning;

import com.mcarner.systemmonitortool.script.Script;
import com.mcarner.systemmonitortool.script.ScriptRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Slf4j
@RequiredArgsConstructor
public class ScriptFinderService {

    private final ScriptRepository scriptRepo;
    //Look for new scripts in the script folder, executes them and logs the output to a table.

    //Watchfolder

    public static String HOME_FOLDER = "C:\\CODE\\SystemMonitorTool\\local";
    public static String SCRIPTS_FOLDER = HOME_FOLDER + "\\scripts";
    public static String SCR_FOLDER = HOME_FOLDER + "\\scripts";

    @Scheduled(fixedRate = 6000)
    public void findScripts(){
        boolean isRunning=true;
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(SCRIPTS_FOLDER))){
            for (Path path : stream) {
                if (!Files.isDirectory(path)) {
//                    log.info(String.valueOf(path.toAbsolutePath()));

                    //TODO: If there are performance issues, load hashmap with scriptFilenames on init
                    //  and check it before doing any database stuff
                    //  ALSO: Store script objects in iterable and saveAll afterwards

                    String filename = String.valueOf(path.getFileName());
                    Script existingScript = scriptRepo.findScriptByFilename(filename);
                    if (existingScript == null){
                        Script script = new Script();
                        script.setFilename(filename);
                        scriptRepo.save(script);
                        log.info("Saved new script: {}", filename);

                    }

                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }




    @PostConstruct
    public void init(){
        //If watchfolder doesn't exist, create it
//        ApplicationHome home = new ApplicationHome(SystemMonitorToolApplication.class);
//        File homeFolder = home.getDir();    // returns the folder where the jar is. This is what I wanted.
//        home.getSource(); // returns the jar absolute path.
        File homeFolder = new File(HOME_FOLDER);
        File scriptsFolder = new File(homeFolder,"scripts");

        if (!scriptsFolder.exists()){
            scriptsFolder.mkdir();
        }
    }
}
