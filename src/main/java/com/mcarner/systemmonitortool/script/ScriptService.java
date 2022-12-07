package com.mcarner.systemmonitortool.script;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScriptService {
    private final ScriptRepository scriptRepository;
}
