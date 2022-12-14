package com.mcarner.systemmonitortool;

import com.mcarner.systemmonitortool.script.Script;
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
    //  x Edit Script
    //      x Click edit icon, go to /script/# and edit description
    //      x PROBLEM - I get the same script info when editing both scripts?
    //  x Dashboard
    //      x # metrics, # scripts being monitored
    //      x Card Badge displaying Number of scripts in OK,WARN,CRIT,ETC
    //          x Badge only visible if it's >0
    //  - View System
    //      x Section for Issues
    //      x ScriptOutput keeps returning null from db (because the script_id column is null)
    //      x Change ScriptOutput to have relationship with System and Script
    //      x List names of issues and status
    //      x details exists in Script, but really it needs to be the most recent details from ScriptOutput
    //  x ISSUES:
    //      x Bidirectional relationship between script and scriptoutput is causing issues:
    //        failed to lazily initialize a collection of role:
    //        SOLVED: made runScripts() @Transactional to fix issues with JPA session getting closed


    //TODO: Set up "production ready" deployment
    //  x Header with navigate to dashboard
    //  x Store necessary values in import.sql
    //      x servers
    //      x Applications
    //      x Windows services
    //  x System id available in dashboard somewhere
    //  x ScriptOutput can handle multiple lines (may need additional config, but its working)
    //  - Start setting up scripts and metrics
    //      - Single script, hit multiple servers echo multiple lines of info.
    //      - ping server, is it up?
    //      - Disk space
    //      - What else?
    //      - Windows service status
    //  - Clean up visuals
    //      - Status [x] badges size, placement, spacing
    //      x Edit system page
    //  - Rework dashboard cards
    //      - Status badge with a name and thats it.
    //  - When viewing system and script, status needs to update live
    //      - Maybe separate status endpoints that give an array of statuses
    //      - /system/{id}/status - system
    //      - /system/{id}/statuses - system and scripts
    //      - /script/{id}/status
    //  - Delete data after X time
    //  - Are queries pulling fresh data?
    //  - Edit system name

    //TODO: System output visuals
    //  - Need to figure out something before taking it live

    //TODO: Link systems
    //  - Link systems together, this adds them to a list for building CONDITIONS
    //  - Conditions
    //      - Select systems and create conditions based on STATUS

    //TODO: Logging and notifications
    //  - Emails, SMS, slack, teams
    //  - Escalation after an error exists for a certain amount of time
    //      - Escalation policy

    //TODO: Dashboard improvements
    //  - Top bar with metrics
    //  - Different views
    //  - Rows grouped by classifications or parent systems
    //      - Click to expand, showing sub systems
    //  - Sorting
    //      - system type
    //  - Drag and drop?
    //  - Save views specific to users

    //TODO: Users with permissions and settings
    //  - USER/ADMIN
    //  - Personal settings
    //      - switches to tick on information on certain pages

    //TODO: PROBLEMS
    //  - PROBLEM - after saving script changes, it goes back two pages

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