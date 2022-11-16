package com.mcarner.systemmonitortool.system;

import com.mcarner.systemmonitortool.system.tags.Tag;
import com.mcarner.systemmonitortool.system.tags.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SystemService {

    private final SystemRepository systemRepository;
    private final TagRepository tagRepository;

    public System upsertSystem(System system) {
        return systemRepository.save(system);
    }

    public void deleteSystem(Long systemId) {
        systemRepository.deleteById(systemId);
    }

    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }



}
