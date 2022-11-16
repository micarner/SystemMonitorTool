package com.mcarner.systemmonitortool.system;

import com.mcarner.systemmonitortool.system.values.IMPORTANCE;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.stream.Collectors;

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

    @GetMapping("/importance")
    ResponseEntity<?> getImportance(){
        return ResponseEntity.ok().body(Arrays.stream(IMPORTANCE.values()).map(Enum::name).collect(Collectors.toList()));
    }

    @GetMapping("/tags")
    ResponseEntity<?> getAllTags(){
        return ResponseEntity.ok().body(systemService.getAllTags());
    }


}
