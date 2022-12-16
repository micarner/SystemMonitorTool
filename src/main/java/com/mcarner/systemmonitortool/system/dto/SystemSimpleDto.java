package com.mcarner.systemmonitortool.system.dto;

import com.mcarner.systemmonitortool.system.System;
import com.mcarner.systemmonitortool.system.values.Importance;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link System} entity
 */
@Data
public class SystemSimpleDto implements Serializable {
    private final Long id;
    private final String name;
    private final String description;
    private final Importance importance;
}