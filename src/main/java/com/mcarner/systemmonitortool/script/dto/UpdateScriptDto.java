package com.mcarner.systemmonitortool.script.dto;

import com.mcarner.systemmonitortool.script.Script;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link Script} entity
 */
@Data
public class UpdateScriptDto implements Serializable {
    private final Long id;
    private final String description;
    private final Long scriptOutputTimeWindow;
    private final Long frequencyToCheck;
}