package com.mcarner.systemmonitortool.script.scriptoutput;


import com.mcarner.systemmonitortool.script.Script;
import com.mcarner.systemmonitortool.script.Status;
import com.mcarner.systemmonitortool.script.scriptrunning.ScriptType;

import com.mcarner.systemmonitortool.system.System;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Entity
@Getter
@Setter
@Slf4j
@Table(name = "script_output")
public class ScriptOutput {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;


    //TODO: How to handle this? Maybe not at all.
    //  Just do a generic catch block when running the script and display it to end user
//    @Column(name = "exception")
//    @Type(type = "com.mcarner.systemmonitortool.script.Exception")
//    private java.lang.Exception exception;

    @Column(name = "error_details")
    private String errorDetails;

    @Enumerated(EnumType.STRING)
    @Column(name = "script_type")
    private ScriptType scriptType;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;



    @Column(name = "script_name")
    private String scriptName;


    @Column(name = "details")
    private String details;

    @Column(name = "ran_at")
    private LocalDateTime ranAt = LocalDateTime.now();

    @Column(name = "completed_at")
    private LocalDateTime completedAt;

    @Column(name = "filename")
    private String filename;

    @Column(name = "`values`")
    private String values;

    @Transient
    private ArrayList<String> rawScriptOutput;


    @ManyToOne
    @JoinColumn(name = "script_id")
    private Script script;


    @ManyToOne
    @JoinColumn(name = "system_id")
    private System system;

}

