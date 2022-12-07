package com.mcarner.systemmonitortool.system.dto;


import com.mcarner.systemmonitortool.system.System;
import com.mcarner.systemmonitortool.system.link.LINK_SEVERITY;
import com.mcarner.systemmonitortool.system.values.IMPORTANCE;
import com.mcarner.systemmonitortool.system.values.STATE;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * A DTO for the {@link System} entity
 */
@Data
public class SystemDto implements Serializable {
    private final Long id;
    private final String name;
    private final String description;
    private final Set<ScriptDto> issuesToMonitor;
    private final STATE state;
    private final IMPORTANCE importance;
    private final Long linkId;
    private final LINK_SEVERITY linkLinkSeverity;
    private final Long linkReliesOnId;
    private final Set<Long> tagIds;


    @Data
    public static class ScriptDto implements Serializable {
        private final Long id;
        private final String name;
        private final String description;
        private final String details;
        private final Long frequencyToCheck;
        private final String scriptPath;
    }
}