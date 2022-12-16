package com.mcarner.systemmonitortool.script;

import com.mcarner.systemmonitortool.script.dto.ScriptDto;
import com.mcarner.systemmonitortool.script.dto.UpdateScriptDto;
import com.mcarner.systemmonitortool.script.scriptoutput.ScriptOutput;
import com.mcarner.systemmonitortool.script.scriptoutput.ScriptOutputDto;
import com.mcarner.systemmonitortool.script.scriptoutput.ScriptOutputRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ScriptService {

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


    public ScriptDto updateScript(UpdateScriptDto updateScriptRequest) {
        Script scriptToUpdate = scriptRepo.findById(updateScriptRequest.getId()).orElseThrow();
        if (updateScriptRequest.getDescription() != null){
            scriptToUpdate.setDescription(updateScriptRequest.getDescription());
        }
        if (updateScriptRequest.getScriptOutputTimeWindow() != null){
            scriptToUpdate.setScriptOutputTimeWindow(updateScriptRequest.getScriptOutputTimeWindow());
        }
        if (updateScriptRequest.getFrequencyToCheck() != null){
            scriptToUpdate.setFrequencyToCheck(updateScriptRequest.getFrequencyToCheck());
        }
        Script script = scriptRepo.save(scriptToUpdate);
        return scriptRepo.findScriptById(script.getId());
    }

    public ScriptDto getScript(Long scriptId) {
        return scriptRepo.findScriptById(scriptId);
    }

}
