package com.mcarner.systemmonitortool.script.dto;

import lombok.Data;

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
}