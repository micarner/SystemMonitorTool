package com.mcarner.systemmonitortool.system;

import com.mcarner.systemmonitortool.script.dto.UpdateScriptDto;
import com.mcarner.systemmonitortool.system.dto.SystemCreateDto;
import com.mcarner.systemmonitortool.system.dto.SystemDto;
import com.mcarner.systemmonitortool.system.values.IMPORTANCE;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class SystemController {

    private final SystemService systemService;


    @GetMapping("/system/{id}")
    ResponseEntity<?> getSystem(@PathVariable Long id){
        return ResponseEntity.ok().body(systemService.getSystem(id));
    }

    @GetMapping("/system/{id}/scripts")
    ResponseEntity<?> getSystemScripts(@PathVariable Long id){
        return ResponseEntity.ok().body(systemService.getSystemScripts(id));
    }

    @PutMapping("/scripts")
    ResponseEntity<?> updateScript(@RequestBody UpdateScriptDto updateScriptRequest){
        return ResponseEntity.ok().body(systemService.updateScript(updateScriptRequest));
    }


    @GetMapping("/system")
    ResponseEntity<?> getSystems(){
        return ResponseEntity.ok().body(systemService.getAllSystems());
    }

    @PostMapping("/system/new")
    ResponseEntity<?> createNewSystem(@RequestBody SystemCreateDto systemCreateDto){
        System newlyCreatedSystem = systemService.createSystem(systemCreateDto);
        log.info("Created new system: ({}) - {}",newlyCreatedSystem.getId(),newlyCreatedSystem.getName());
        return ResponseEntity.ok().body(newlyCreatedSystem);
    }

    @PostMapping("/system/update")
    ResponseEntity<?> updateSystem(@RequestBody SystemDto updateSystem){
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
