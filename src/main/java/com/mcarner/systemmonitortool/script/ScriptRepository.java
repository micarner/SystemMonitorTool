package com.mcarner.systemmonitortool.script;

import com.mcarner.systemmonitortool.script.dto.ScriptDto;
import com.mcarner.systemmonitortool.system.System;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface ScriptRepository extends JpaRepository<Script, Long> {
    Script findScriptByFilename(String filename);
//    List<ScriptDto> findScriptsBySystemsOrderByIdAsc(Iterable<System> system);
//    List<Script> findScriptsBySystemIdOrderByIdAsc(Long systemId);
//    List<Script> findScriptsBySystems(Iterable<System> system);

    List<ScriptDto> findAllBySystemsInOrderByIdAsc(Set<System> systems);

    List<ScriptDto> findAllBySystemsInOrderByIdDesc(Set<System> systems);

    //These are giving errors, no clue how to fix:
    //Requested tuple value [index=0, realType=com.mcarner.systemmonitortool.script.Script] cannot be assigned to requested type [java.util.Set]
//    @Query("SELECT u.scripts FROM System u WHERE u.id = :id order by u.id asc")
    @Query("SELECT u.scripts FROM System u WHERE u.id = :id order by u.id asc")
    public List<ScriptDto> getScriptDtosBySystemIdOrderByIdAsc(@Param("id") Long id);
    @Query("SELECT u.scripts FROM System u WHERE u.id = :id")
    public Set<ScriptDto> getScriptDtosBySystemId(@Param("id") Long id);

    @Query("SELECT u.scripts FROM System u WHERE u.id = :id order by u.id asc")
    public List<Script> getScriptsBySystemId(@Param("id") Long id);

    ScriptDto findScriptById(Long scriptId);

}