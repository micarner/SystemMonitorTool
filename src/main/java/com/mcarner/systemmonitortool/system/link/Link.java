package com.mcarner.systemmonitortool.system.link;

import com.mcarner.systemmonitortool.system.System;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Link {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "link_severity")
    private LINK_SEVERITY linkSeverity;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "linked_system_id", nullable = false)
    private System system;

    @OneToOne(optional = false)
    @JoinColumn(name = "relies_on_id", nullable = false)
    private System reliesOn;

}
