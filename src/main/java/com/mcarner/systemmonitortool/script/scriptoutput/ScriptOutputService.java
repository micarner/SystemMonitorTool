package com.mcarner.systemmonitortool.script.scriptoutput;

import com.mcarner.systemmonitortool.script.Script;
import com.mcarner.systemmonitortool.script.ScriptRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ScriptOutputService {

    public final ScriptOutputRepository scriptOutputRepo;
    public final ScriptRepository scriptRepo;

    List<ScriptOutputDto> getScriptOutput(Long scriptId){
        LocalDateTime now = LocalDateTime.now();
        //Get scriptOutputTimeWindow
        Script script = scriptRepo.findById(scriptId).orElseThrow();
        LocalDateTime lowerBound = LocalDateTime.now().minusNanos(script.getScriptOutputTimeWindow() * 1000000);
        //Need to link script to scriptoutput
        List<ScriptOutputDto> scriptOutputDtos =
                scriptOutputRepo.findAllByScriptIdAndRanAtBeforeOrderByRanAtDesc(scriptId, lowerBound);
//        List<ScriptOutputDto> scriptOutputDtos = scriptOutputRepo.findScriptOutputsByScriptId(scriptId);
        return scriptOutputDtos;
    }

}
