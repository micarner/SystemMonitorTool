package com.mcarner.systemmonitortool.system;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SystemService {

    private final SystemRepository systemRepository;

    public System upsertSystem(System system) {
        return systemRepository.save(system);
    }

    public void deleteSystem(Long systemId) {
        systemRepository.deleteById(systemId);
    }



}
