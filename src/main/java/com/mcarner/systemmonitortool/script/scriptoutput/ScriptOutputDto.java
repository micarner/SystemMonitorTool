package com.mcarner.systemmonitortool.script.scriptoutput;

import com.mcarner.systemmonitortool.script.scriptrunning.ScriptType;
import com.mcarner.systemmonitortool.script.scriptrunning.Status;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link ScriptOutput} entity
 */
@Data
public class ScriptOutputDto implements Serializable {
    private final Long id;
    private final ScriptType scriptType;
    private final Status status;
    private final LocalDateTime ranAt;
    private final String values;
}