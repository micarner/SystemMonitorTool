package com.mcarner.systemmonitortool.script;

import com.mcarner.systemmonitortool.system.System;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@ToString
public class Script {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description", length = 2550)
    private String description;

    @Column(name = "details", length = 2550)
    private String details;


    //      - [metric or issue]_[status]_[system name]_[issue name]_[value/metrics]_[status detail]
    //      - metricname=value;warn;crit;min;max
    //      - Multiple metrics: count1=42|count2=21;23;27|count3=73


    @ManyToOne
    @JoinColumn(name = "system_id")
    private System system;

    @Column(name = "frequency_to_check", nullable = false)
    //In ms //Default every 30s
    private Long frequencyToCheck = 30000L;

    @Column(name = "filename")
    private String filename;

    @Column(name = "last_ran")
    private LocalDateTime lastRan;

}
