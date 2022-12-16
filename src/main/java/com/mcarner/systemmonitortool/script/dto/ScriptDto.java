package com.mcarner.systemmonitortool.script.dto;

import com.mcarner.systemmonitortool.script.Script;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link com.mcarner.systemmonitortool.script.Script} entity
 */
@Data
public class ScriptDto implements Serializable {
    private final Long id;
    private final String name;
    private final String description;
    private final String details;
    private final LocalDateTime lastRan;
//    private final Long scriptOutputTimeWindow;
    private final Long frequencyToCheck;

//    public ScriptDto(Script script) {
//        this.id = script.getId();
//        this.name = script.getName();
//        this.description = script.getDescription();
//        this.details = script.getDetails();
//        this.lastRan = script.getLastRan();
////        this.scriptOutputTimeWindow = script.getScriptOutputTimeWindow();
////        this.frequencyToCheck = script.getFrequencyToCheck();
//    }
}