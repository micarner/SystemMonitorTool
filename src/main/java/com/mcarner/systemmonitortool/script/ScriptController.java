package com.mcarner.systemmonitortool.script;


import com.mcarner.systemmonitortool.script.dto.UpdateScriptDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class ScriptController {
    
    //Script endpoints are in SystemController atm
    private final ScriptService scriptService;

    @GetMapping("/script/{id}/output")
    ResponseEntity<?> getScriptOutput(@PathVariable Long id) {
        return ResponseEntity.ok().body(scriptService.getScriptOutput(id));
    }

    @GetMapping("/script/{id}")
    ResponseEntity<?> getScript(@PathVariable Long id) {
        return ResponseEntity.ok().body(scriptService.getScript(id));
    }

    @PutMapping("/script")
    ResponseEntity<?> updateScript(@RequestBody UpdateScriptDto updateScriptRequest){
        return ResponseEntity.ok().body(scriptService.updateScript(updateScriptRequest));
    }
}
