package com.mcarner.systemmonitortool.system.dto;

import com.mcarner.systemmonitortool.system.System;
import com.mcarner.systemmonitortool.system.values.IMPORTANCE;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * A DTO for the {@link System} entity
 */
@Data
public class SystemCreateDto implements Serializable {
    private final String name;
    private final String description;
    private final IMPORTANCE importance;
    private final Set<Long> tagIds;
}