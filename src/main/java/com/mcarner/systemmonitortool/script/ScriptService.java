package com.mcarner.systemmonitortool.script;

import com.mcarner.systemmonitortool.script.dto.ScriptDto;
import com.mcarner.systemmonitortool.script.dto.UpdateScriptDto;
import com.mcarner.systemmonitortool.script.scriptoutput.ScriptOutputDto;
import com.mcarner.systemmonitortool.script.scriptoutput.ScriptOutputRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
        List<ScriptOutputDto> scriptOutputDtos = script.getScriptOutputs()
                .stream()
                .filter(s -> s.getRanAt().isAfter(lowerBound))
                .map(s -> new ScriptOutputDto(s.getId(),s.getScriptType(),s.getScriptName(),s.getDetails(),s.getStatus(),s.getRanAt(),s.getValues()))
                .collect(Collectors.toList());
//        List<ScriptOutputDto> scriptOutputDtos =
                //TODO: This is returning an empty list. Why? Is the repo query method wrong? maybe it should be RanAtAfter
//                scriptOutputRepo.findAllByScriptIdAndRanAtBeforeOrderByRanAtDesc(scriptId, lowerBound);
//        scriptOutputRepo.findAllByScriptIdAndRanAtAfterOrderByRanAtDesc(scriptId, lowerBound);
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
