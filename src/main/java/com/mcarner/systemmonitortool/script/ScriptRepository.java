package com.mcarner.systemmonitortool.script;

import com.mcarner.systemmonitortool.script.dto.ScriptDto;
import com.mcarner.systemmonitortool.system.System;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScriptRepository extends JpaRepository<Script, Long> {
    Script findScriptByFilename(String filename);
    List<ScriptDto> findScriptsBySystemIdOrderByIdAsc(Long systemId);
}