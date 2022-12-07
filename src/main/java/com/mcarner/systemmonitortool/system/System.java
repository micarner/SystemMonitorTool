package com.mcarner.systemmonitortool.system;


import com.mcarner.systemmonitortool.system.link.Link;
import com.mcarner.systemmonitortool.system.tags.Tag;
import com.mcarner.systemmonitortool.system.values.IMPORTANCE;
import com.mcarner.systemmonitortool.system.values.STATE;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false)
    private STATE state = STATE.OK;

    @Enumerated(EnumType.STRING)
    @Column(name = "importance", nullable = false)
    private IMPORTANCE importance = IMPORTANCE.MEDIUM;

    @OneToOne(mappedBy = "system", orphanRemoval = true)
    private Link link;

    @ManyToMany
    @JoinTable(name = "system_tags",
            joinColumns = @JoinColumn(name = "system_id"),
            inverseJoinColumns = @JoinColumn(name = "tags_id"))
    private Set<Tag> tags = new LinkedHashSet<>();

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "description = " + description + ", " +
                "state = " + state + ", " +
                "importance = " + importance + ", " +
                "link = " + link + ")";
    }
}
