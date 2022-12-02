package com.mcarner.systemmonitortool;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SystemMonitorToolApplication {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(SystemMonitorToolApplication.class, args);
    }

    //TODO:
    //  - Create systems page
    //      x Add system
    //      x View systems on dashboard
    //          x Fix origin issues
    //      - Inspect system
    //          - Click on system card, go to page with more detailed system info
    //          - Hover over system for more details
    //      - Edit systems page
    //          - Click card and it takes you to systems page
    //          - Edit button on this page
    //          - Click that to go to edit system page

    //TODO: Set up issues and script running

    //TODO: Logging and notifications

    //TODO: Link systems

    //TODO: Create management area to create Systems and link them together by severity
    //  x [BACKEND]
    //      x Setup automatically with Spring Data Rest
    //  - Frontend page
    //      x Add new systems
    //      x Grid of system cards
    //  - Systems
    //      - https://github.com/marmelab/react-admin/blob/master/examples/crm/src/companies/CompanyCreate.tsx
    //      - https://marmelab.com/react-admin-crm/
    //      - Create
    //      - Click card to see more info
    //      - edit system info
    //  - Systems have Issues
    //  - System have Incidents
    //      - Set up filters to determine what creates an incident
    //          - Number of times it's triggered
    //          - How long it's triggered for
    //          - Percentage reached
    //      - Reuse Debuggy stuff
    //          - login, users, permissions, issue = incident
    //  - Notifications
    //      - Slack, direct or in channel
    //      - Emails


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