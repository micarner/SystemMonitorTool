package com.mcarner.systemmonitortool.script.parsers;

import com.mcarner.systemmonitortool.script.scriptoutput.ScriptOutput;
import com.mcarner.systemmonitortool.script.scriptoutput.ScriptOutputRepository;
import com.mcarner.systemmonitortool.script.scriptrunning.ScriptType;
import com.mcarner.systemmonitortool.script.scriptrunning.Status;
import com.mcarner.systemmonitortool.system.SystemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ScriptOutputParser {

    @Autowired
    private final SystemRepository systemRepo;
    @Autowired
    private final ScriptOutputRepository scriptOutputRepo;



}
