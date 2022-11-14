package com.mcarner.systemmonitortool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SystemMonitorToolApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemMonitorToolApplication.class, args);
    }

    //TODO: Create management area to create Systems and link them together by severity
    //  - [BACKEND]
    //      - Setup automatically with Spring Data Rest
    //  - Frontend page
    //      x Add new systems
    //      x Grid of system cards
    //  - Systems
    //      - https://github.com/marmelab/react-admin/blob/master/examples/crm/src/companies/CompanyCreate.tsx
    //      - Create
    //      - Click card to see more info
    //      - edit system info


    //TODO: NEEDS
    //  - Management screen
    //      - Manage list of systems
    //      - Link systems including severity
    //  - Dashboard
    //      - Displays systems
    //      - Color codes when stuff breaks
    //      - Systems rely on eachother
    //      - Use TanTable
    //  - Scripts to monitor
    //      - Drop scripts in folder
    //      - Assign scripts to systems
    //      - Log output of scripts
    //      - scripts return output value to monitoring tool
    //      - Need output value format

}