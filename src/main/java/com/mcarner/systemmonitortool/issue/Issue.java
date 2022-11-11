package com.mcarner.systemmonitortool.issue;

import com.mcarner.systemmonitortool.system.System;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@Entity
@ToString
public class Issue {
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





    @ManyToOne
    @JoinColumn(name = "system_id")
    private System system;

    @Column(name = "frequency_to_check", nullable = false)
    //In ms //Default every 5 min
    private Long frequencyToCheck = 300000L;

    @Column(name = "script_path")
    private String scriptPath;

}
