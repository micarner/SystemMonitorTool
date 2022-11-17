package com.mcarner.systemmonitortool.system;

import com.mcarner.systemmonitortool.system.dto.SystemCreateDto;
import com.mcarner.systemmonitortool.system.tags.Tag;
import com.mcarner.systemmonitortool.system.tags.TagRepository;
import com.mcarner.systemmonitortool.system.values.IMPORTANCE;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SystemService {

    private final SystemRepository systemRepository;
    private final TagRepository tagRepository;
    private final ModelMapper mapper;

    public System createSystem(SystemCreateDto systemCreateDto){
        System systemToCreate = new System();
        systemToCreate.setName(systemCreateDto.getName());
        systemToCreate.setDescription(systemCreateDto.getDescription());
        if (systemCreateDto.getTagIds() != null) {
            systemToCreate.setTags(tagRepository.findTagsByIdIn(systemCreateDto.getTagIds()));
        }
        systemToCreate.setImportance(systemCreateDto.getImportance());
        return systemRepository.save(systemToCreate);
    }
    public System upsertSystem(SystemDto systemDto) {
        System system = mapper.map(systemDto,System.class);
        return systemRepository.save(system);
    }

    public void deleteSystem(Long systemId) {
        systemRepository.deleteById(systemId);
    }

    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }



}
