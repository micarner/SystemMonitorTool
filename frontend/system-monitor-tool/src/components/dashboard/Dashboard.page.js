import {Box, Button, Card, Grid, Paper, Typography} from "@mui/material";

import {Link as RouterLink} from "react-router-dom";

import DashboardSystemView from "./DashboardSystemView";




export default function DashboardPage() {

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
            <DashboardSystemView/>
        </>

    )
}