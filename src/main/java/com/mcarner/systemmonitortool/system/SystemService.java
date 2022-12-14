package com.mcarner.systemmonitortool.system;

import com.mcarner.systemmonitortool.script.Script;
import com.mcarner.systemmonitortool.script.ScriptRepository;
import com.mcarner.systemmonitortool.script.dto.UpdateScriptDto;
import com.mcarner.systemmonitortool.script.dto.ScriptDto;
import com.mcarner.systemmonitortool.system.dto.SystemCreateDto;
import com.mcarner.systemmonitortool.system.dto.SystemDto;
import com.mcarner.systemmonitortool.system.tags.Tag;
import com.mcarner.systemmonitortool.system.tags.TagRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SystemService {

    private final SystemRepository systemRepository;
    private final TagRepository tagRepository;
    private final ScriptRepository scriptRepository;
    private final ModelMapper mapper;

    public System createSystem(SystemCreateDto systemCreateDto){
        System systemToCreate = new System();
        systemToCreate.setName(systemCreateDto.getName());
        systemToCreate.setDescription(systemCreateDto.getDescription());
//        systemToCreate.setTags(systemCreateDto.getTagIds());
        if (systemCreateDto.getTagIds() != null) {
            systemToCreate.setTags(tagRepository.findTagsByIdIn(systemCreateDto.getTagIds()));
        }
        systemToCreate.setImportance(systemCreateDto.getImportance());
        return systemRepository.save(systemToCreate);
    }
    public System upsertSystem(SystemDto systemDto) {
        System system = systemRepository.findById(systemDto.getId()).orElseThrow();
        system.setName(systemDto.getName());
        system.setDescription(systemDto.getDescription());
        if (systemDto.getTagIds() != null) {
            system.setTags(tagRepository.findTagsByIdIn(systemDto.getTagIds()));
        }
        system.setImportance(systemDto.getImportance());
        return systemRepository.save(system);
    }

    public void deleteSystem(Long systemId) {
        systemRepository.deleteById(systemId);
    }

    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }


    public List<System> getAllSystems() {
        return systemRepository.findAll();
    }

    public System getSystem(Long id) {
        return systemRepository.findById(id).orElseThrow();
    }

    public List<ScriptDto> getSystemScripts(Long id){
        //Return Script details as well as list of metrics and values and whatnot
        return scriptRepository.findScriptsBySystemIdOrderByIdAsc(id);
    }

    public ScriptDto updateScript(UpdateScriptDto updateScriptRequest) {
        Script scriptToUpdate = scriptRepository.findById(updateScriptRequest.getId()).orElseThrow();
        if (updateScriptRequest.getDescription() != null){
            scriptToUpdate.setDescription(updateScriptRequest.getDescription());
        }
        if (updateScriptRequest.getScriptOutputTimeWindow() != null){
            scriptToUpdate.setScriptOutputTimeWindow(updateScriptRequest.getScriptOutputTimeWindow());
        }
        if (updateScriptRequest.getFrequencyToCheck() != null){
            scriptToUpdate.setFrequencyToCheck(updateScriptRequest.getFrequencyToCheck());
        }
        Script script = scriptRepository.save(scriptToUpdate);
        return scriptRepository.findScriptById(script.getId());
    }
}
