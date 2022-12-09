package com.mcarner.systemmonitortool.script.scriptoutput;

import com.mcarner.systemmonitortool.script.Script;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ScriptOutputService {

    public final ScriptOutputRepository scriptOutputRepo;

    List<ScriptOutputDto> getScriptOutput(Long scriptId){
        //Need to link script to scriptoutput
        List<ScriptOutputDto> scriptOutputDtos = scriptOutputRepo.findAllById(scriptId);
        return scriptOutputDtos;
    }

}
