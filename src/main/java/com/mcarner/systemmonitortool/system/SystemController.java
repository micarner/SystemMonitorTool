package com.mcarner.systemmonitortool.system;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class SystemController {

    private final SystemService systemService;

    @PostMapping("/system/new")
    ResponseEntity<?> createNewSystem(@RequestBody System newSystem){
        System newlyCreatedSystem = systemService.upsertSystem(newSystem);
        log.info("Created new system: ({}) - {}",newlyCreatedSystem.getId(),newlyCreatedSystem.getName());
        return ResponseEntity.ok().body(newlyCreatedSystem);
    }

    @PostMapping("/system/update")
    ResponseEntity<?> updateSystem(@RequestBody System updateSystem){
        System updatedSystem = systemService.upsertSystem(updateSystem);
        log.info("Created new system: ({}) - {}",updatedSystem.getId(),updatedSystem.getName());
        return ResponseEntity.ok().body(updatedSystem);
    }


}
