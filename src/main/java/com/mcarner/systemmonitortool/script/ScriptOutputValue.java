package com.mcarner.systemmonitortool.script;

import com.mcarner.systemmonitortool.script.scriptoutput.ScriptOutput;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Slf4j
@NoArgsConstructor
public class ScriptOutputValue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column(name = "value")
    private String value;


    @ManyToOne
    @JoinColumn(name = "script_output_script_output_id")
    private ScriptOutput scriptOutput;

}
