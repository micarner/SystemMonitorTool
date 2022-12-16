package com.mcarner.systemmonitortool.system;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mcarner.systemmonitortool.script.Script;
import com.mcarner.systemmonitortool.system.link.Link;
import com.mcarner.systemmonitortool.system.tags.Tag;
import com.mcarner.systemmonitortool.system.values.Importance;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="`system`")
public class System {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description", length = 500)
    private String description;


//    @OneToMany(mappedBy = "system", orphanRemoval = true)
//    @ToString.Exclude
//    private Set<Script> scripts = new LinkedHashSet<>();

//    @Enumerated(EnumType.STRING)
//    @Column(name = "status", nullable = false)
//    private STATUS status = STATUS.OK;

    @Enumerated(EnumType.STRING)
    @Column(name = "importance", nullable = false)
    private Importance importance = Importance.MEDIUM;

    @OneToOne(mappedBy = "system", orphanRemoval = true)
    private Link link;

    @ManyToMany
    @JoinTable(name = "system_tags",
            joinColumns = @JoinColumn(name = "system_id"),
            inverseJoinColumns = @JoinColumn(name = "tags_id"))
    private Set<Tag> tags = new LinkedHashSet<>();

    @OneToMany(mappedBy = "system", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnoreProperties("system")
    private Set<Script> scripts = new LinkedHashSet<>();


    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "description = " + description + ", " +
//                "status = " + status + ", " +
                "importance = " + importance + ", " +
                "link = " + link + ")";
    }
}
