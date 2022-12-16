package com.mcarner.systemmonitortool.script.scriptoutput;

import com.mcarner.systemmonitortool.script.Script;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ScriptOutputRepository extends JpaRepository<ScriptOutput, Long> {

//    List<ScriptOutputDto> findAllById(Long id);
    List<ScriptOutputDto> findAllByScriptId(Long scriptId);
    List<ScriptOutputDto> findAllByScriptIdAndRanAtAfter(Long scriptId, LocalDateTime scriptOutputTimeWindow);
    List<ScriptOutputDto> findAllByScriptIdAndRanAtBeforeOrderByRanAtDesc(Long scriptId, LocalDateTime scriptOutputTimeWindow);
    List<ScriptOutputDto> findScriptOutputsByScriptId(Long scriptId);
    ScriptOutput findFirstByScriptOrderByRanAtDesc(Script script);
}
