package com.mcarner.systemmonitortool.system;

import com.mcarner.systemmonitortool.issue.Issue;
import com.mcarner.systemmonitortool.system.link.Link;
import com.mcarner.systemmonitortool.system.values.IMPORTANCE;
import com.mcarner.systemmonitortool.system.values.STATE;
import com.mcarner.systemmonitortool.system.tags.Tag;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class System {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description", length = 500)
    private String description;



    @OneToMany(mappedBy = "system", orphanRemoval = true)
    @ToString.Exclude
    private Set<Issue> issuesToMonitor = new LinkedHashSet<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false, unique = true)
    private STATE state;

    @Enumerated(EnumType.STRING)
    @Column(name = "importance", nullable = false, unique = true)
    private IMPORTANCE importance;

    @OneToOne(mappedBy = "system", orphanRemoval = true)
    private Link link;

    @ManyToMany
    @JoinTable(name = "system_tags",
            joinColumns = @JoinColumn(name = "system_id"),
            inverseJoinColumns = @JoinColumn(name = "tags_id"))
    private Set<Tag> tags = new LinkedHashSet<>();

}
