package com.mcarner.systemmonitortool;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class SystemMonitorToolApplication {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(SystemMonitorToolApplication.class, args);
    }

    //Create systems page
    //      x Add system
    //      x View systems on dashboard
    //          x Fix origin issues
    //      x Inspect system
    //          x Click on system card, go to page with more detailed system info
    //      x Edit systems page
    //          x Click card and it takes you to systems page
    //          x Edit button on this page

    //Planning phase for script running
    //  x How to run powershell script?
    //      x ScriptFinder - Monitors script folder, discovers new scripts, loads in database to be run
    //      x ScriptRunner - Look in database table, run script on set interval
    //      x ScriptOutput - Object containing parsed output, loaded into table
    //      x Run it async and parse echo
    //      x Get basic example working
    //  - Determine format for returning information
    //      - Like check_mk - https://docs.checkmk.com/latest/en/localchecks.html
    //      - [metric or issue]_[status]_[system id]_[script name]_[value/metrics]_[status detail]
    //      - See ScriptOutputParser.java for logic and formatting
    //      - metricname=value;warn;crit;min;max
    //      - Multiple metrics: count1=42|count2=21;23;27|count3=73

    //Set up script running
    //  x Successfully run script and get return info
    //  x Assign issue to System

    //Fix some stuff before continuing
    //  - Fix TAGS preloaded in resources.import.sql (not working since switching to MySQL
    //      - FIXED, changed data.sql to import.sql

    //Link Script to Systems
    //  - View System
    //      - Section for Issues
    //      - List names of issues and status
    //  - Dashboard
    //      - # metrics, # Issues being monitored
    //      - Card Badge displaying Status


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