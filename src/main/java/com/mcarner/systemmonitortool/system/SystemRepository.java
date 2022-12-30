package com.mcarner.systemmonitortool.system;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface SystemRepository extends JpaRepository<System,Long> {

    System findSystemById(Long id);
    Set<System> findSystemsByIdIn(List<Long> id);
}
