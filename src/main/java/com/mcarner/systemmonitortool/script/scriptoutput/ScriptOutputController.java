package com.mcarner.systemmonitortool.script.scriptoutput;

import com.mcarner.systemmonitortool.script.ScriptRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class ScriptOutputController {

    private final ScriptOutputService scriptOutputService;

    @GetMapping("/script/{id}")
    ResponseEntity<?> getScriptOutput(@PathVariable Long id){
        return ResponseEntity.ok().body(scriptOutputService.getScriptOutput(id));
    }
}
