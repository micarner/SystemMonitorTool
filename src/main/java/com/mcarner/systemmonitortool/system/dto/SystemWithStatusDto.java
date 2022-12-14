package com.mcarner.systemmonitortool.system.dto;

import com.mcarner.systemmonitortool.script.Status;
import com.mcarner.systemmonitortool.system.tags.Tag;
import com.mcarner.systemmonitortool.system.values.Importance;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;

@Data
public class SystemWithStatusDto implements Serializable {
    private final Long id;
    private final String name;
    private final String description;
    private final Importance importance;
    private final Status status;
    private final HashMap<String, Long> statusCounts;
    private final Set<Tag> tags;

}

