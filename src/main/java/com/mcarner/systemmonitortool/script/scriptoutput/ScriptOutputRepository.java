package com.mcarner.systemmonitortool.script.scriptoutput;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScriptOutputRepository extends JpaRepository<ScriptOutput, Long> {

    List<ScriptOutputDto> findAllById(Long id);
}
