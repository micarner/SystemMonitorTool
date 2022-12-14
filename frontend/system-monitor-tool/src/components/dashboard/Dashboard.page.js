import {Box, Button, Card, CardContent, Divider, Grid, Typography} from "@mui/material";
import {AppContext} from "../../index";
import {useQuery} from "react-query";
import axios from "axios";
import {useContext, useState} from "react";
import SystemCard from "./system/SystemCard";
import {Link as RouterLink} from "react-router-dom";

export default function DashboardPage(props){

    const {baseUrl} = useContext(AppContext);

    const { isLoading: systemsIsLoading, data: systemsData } = useQuery('systems', () => {
        // console.log(baseUrl + "api/importance")
        return axios.get(baseUrl + "api/system")
    }, {staleTime: 1000*5})

    if (systemsIsLoading){
        return <></>
    }

    return (
        <>
            <Box className={"dashboard"}>
                {/*<Button onClick={()=> setCards([])}>Reset</Button>*/}
                <Button
                    {...{
                        key: "AddSystemPage",
                        color: "inherit",
                        to: "/system/add",
                        component: RouterLink,
                    }}
                >Add System</Button>
            </Box>
            <Grid container spacing={2}>
                {systemsData?.data.map((card, index) => {
                    return <SystemCard card={card} index={index} key={index}/>
                })}
            </Grid>
        </>
    )
}