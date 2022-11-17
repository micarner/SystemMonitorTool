package com.mcarner.systemmonitortool.system.tags;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface TagRepository extends JpaRepository<Tag, Long> {

    Set<Tag> findTagsByIdIn(Set<Long> ids);
}