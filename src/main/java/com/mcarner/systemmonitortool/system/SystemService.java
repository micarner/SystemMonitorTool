package com.mcarner.systemmonitortool.system;

import com.mcarner.systemmonitortool.script.Script;
import com.mcarner.systemmonitortool.script.ScriptRepository;
import com.mcarner.systemmonitortool.script.Status;
import com.mcarner.systemmonitortool.script.dto.ScriptDto;
import com.mcarner.systemmonitortool.script.scriptoutput.ScriptOutput;
import com.mcarner.systemmonitortool.script.scriptoutput.ScriptOutputRepository;
import com.mcarner.systemmonitortool.system.dto.SystemCreateDto;
import com.mcarner.systemmonitortool.system.dto.SystemDto;
import com.mcarner.systemmonitortool.system.dto.SystemWithStatusDto;
import com.mcarner.systemmonitortool.system.tags.Tag;
import com.mcarner.systemmonitortool.system.tags.TagRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SystemService {

    private final SystemRepository systemRepo;
    private final TagRepository tagRepo;
    private final ScriptRepository scriptRepo;
    private final ModelMapper mapper;
    private final ScriptOutputRepository scriptOutputRepo;

    public System createSystem(SystemCreateDto systemCreateDto){
        System systemToCreate = new System();
        systemToCreate.setName(systemCreateDto.getName());
        systemToCreate.setDescription(systemCreateDto.getDescription());
//        systemToCreate.setTags(systemCreateDto.getTagIds());
        if (systemCreateDto.getTagIds() != null) {
            systemToCreate.setTags(tagRepo.findTagsByIdIn(systemCreateDto.getTagIds()));
        }
        systemToCreate.setImportance(systemCreateDto.getImportance());
        return systemRepo.save(systemToCreate);
    }
    public System upsertSystem(SystemDto systemDto) {
        System system = systemRepo.findById(systemDto.getId()).orElseThrow();
        system.setName(systemDto.getName());
        system.setDescription(systemDto.getDescription());
        if (systemDto.getTagIds() != null) {
            system.setTags(tagRepo.findTagsByIdIn(systemDto.getTagIds()));
        }
        system.setImportance(systemDto.getImportance());
        return systemRepo.save(system);

    }

    public void deleteSystem(Long systemId) {
        systemRepo.deleteById(systemId);
    }

    public List<Tag> getAllTags() {
        return tagRepo.findAll();
    }


    public List<System> getAllSystems() {
        return systemRepo.findAll();
    }


    public List<ScriptDto> getSystemScripts(Long id){
        //Return Script details as well as list of metrics and values and whatnot
        return scriptRepo.findScriptsBySystemIdOrderByIdAsc(id);
    }


    public SystemWithStatusDto getSystemWithStatus(Long id) {
        System system = systemRepo.findSystemById(id);
        List<System> systemList = new ArrayList<>();
        systemList.add(system);
        ArrayList<SystemWithStatusDto> statusDtos = calculateSystemStatuses(systemList);

        return statusDtos.get(0);
    }
    public ArrayList<SystemWithStatusDto> getAllSystemsWithState() {
        //Get systems
        List<System> systems = systemRepo.findAll();

        return calculateSystemStatuses(systems);

    }

    private ArrayList<SystemWithStatusDto> calculateSystemStatuses(List<System> systems) {
        ArrayList<SystemWithStatusDto> statusDtos = new ArrayList<>();
        for (System system :
                systems) {
            List<Script> scripts = scriptRepo.findScriptsBySystemId(system.getId());
            HashMap<String, Long> statusCounts = new HashMap<>();

            for (Script script :
                    scripts) {
                ScriptOutput latestScriptOutput = scriptOutputRepo.findFirstByScriptOrderByRanAtDesc(script);
                if (latestScriptOutput == null){
                    continue;
                }
                statusCounts = addStatusCount(latestScriptOutput.getStatus(),statusCounts);
            }

            long inOK       = statusCounts.get(Status.OK.name()) == null ? 0 : statusCounts.get(Status.OK.name());
            long inWARN     = statusCounts.get(Status.WARN.name()) == null ? 0 : statusCounts.get(Status.WARN.name());
            long inCRIT     = statusCounts.get(Status.CRIT.name()) == null ? 0 : statusCounts.get(Status.CRIT.name());
            long inDOWN     = statusCounts.get(Status.DOWN.name()) == null ? 0 : statusCounts.get(Status.DOWN.name());
            long inUNKNOWN  = statusCounts.get(Status.UNKNOWN.name()) == null ? 0 : statusCounts.get(Status.UNKNOWN.name());

            Status status = Status.UNKNOWN;
            //Determine status
            if(inWARN == 0 && inCRIT == 0 && inDOWN == 0 && inUNKNOWN == 0){
                status = Status.OK;
            } else if(inDOWN > 0) {
                status = Status.DOWN;
            } else if(inCRIT > 0) {
                status = Status.CRIT;
            } else if (inWARN > 0) {
                status = Status.WARN;
            }



//            SystemWithStatusDto dto = new SystemWithStatusDto(
//                    system.getId(), system.getName(), system.getDescription(), system.getImportance(),
//                    status, statusCounts, system.getTags().stream().map(Tag::getId).collect(Collectors.toSet())
//            );
            SystemWithStatusDto dto = new SystemWithStatusDto(
                    system.getId(), system.getName(), system.getDescription(), system.getImportance(),
                    status, statusCounts, system.getTags()
            );
            statusDtos.add(dto);

        }

        return statusDtos;
    }

    private HashMap<String, Long> addStatusCount(Status status, HashMap<String, Long> statusCounts) {
        LongAdder num = new LongAdder();
        num.add(1);
        if (statusCounts.get(status.name()) != null){
            num.add(statusCounts.get(status.name()));
        }
        statusCounts.put(status.name(), num.longValue());
        return statusCounts;
    }
}
