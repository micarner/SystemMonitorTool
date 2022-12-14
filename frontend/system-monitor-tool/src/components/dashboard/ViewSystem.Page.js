import {useContext} from "react";
import {AppContext} from "../../index";
import {useQuery} from "react-query";
import axios from "axios";
import {Button, Card, CardActions, CardContent, Divider, Grid, Typography} from "@mui/material";
import SystemCard from "./SystemCard";
import SaveIcon from "@mui/icons-material/Save";
import {Edit} from "@mui/icons-material";
import {useNavigate} from "react-router-dom";
import * as PropTypes from "prop-types";
import StatusBadge from "./StatusBadge";


export default function ViewSystemPage(props){

    const {baseUrl} = useContext(AppContext);
    const navigate = useNavigate();

    const { isLoading: syatemIsLoading, data: systemData} = useQuery(`system-${props.id}`, () => {
        // console.log(baseUrl + "api/importance")
        return axios.get(baseUrl + `api/system/${props.id}`)
    }, {staleTime: 1000*5})

    const { isLoading: scriptsAreLoading, data: scriptsData} = useQuery(`system-${props.id}-scripts`, () => {
        // console.log(baseUrl + "api/importance")
        return axios.get(baseUrl + `api/system/${props.id}/scripts`)
    }, {staleTime: 1000*5})

    if (syatemIsLoading && scriptsAreLoading){
        return <h2>Loading...</h2>
    }
    console.log(systemData?.data)

    const {id, name, description, importance, state, tags} = systemData?.data;
    return (
        <>
        <Card sx={{
            marginBottom: "20px;",
        }}>
            <CardContent>
                <Typography variant={"h6"} >
                    Name: {name}
                </Typography>
                <Divider light/>
                <Typography variant={"p"} >
                    Description: {description}
                </Typography>
                <Divider light/>
                <Typography variant={"p"} >
                    Tags: {tags.map(tag => tag.name).join(", ")}
                </Typography>
                <Divider light/>
                <Typography variant={"p"} >
                    Importance:{importance}
                </Typography>
                <Divider light/>
                <Typography variant={"p"} >
                    State:{state}
                </Typography>
            </CardContent>
            <CardActions>
                <Button
                    startIcon={<Edit/>}
                    onClick={() => {
                        navigate(`/system/${id}/edit`)
                        // mutation.mutate({name:name,description:description,importance:importance,tagIds:tags})
                    }}
                >Edit System</Button>
            </CardActions>
        </Card>
        <SystemSriptsView scriptsData={scriptsData}/>
        </>
    )
}

function SystemSriptsView(props) {
    var boolean = true
    return (
        <>
            {props.scriptsData?.data.map(scriptData =>
                <ScriptCard scriptData={scriptData} key={scriptData.id}/>
            )}
        </>
    );

}
SystemSriptsView.propTypes = {scriptsData: PropTypes.any};


function ScriptCard(props) {
    const scriptData = props.scriptData;
    const {baseUrl} = useContext(AppContext);

    // console.log(scriptData)

    const { isLoading: scriptOutputIsLoading, data: scriptOutputData} = useQuery(`scriptOutput-${scriptData.id}`, () => {
        // console.log(baseUrl + "api/importance")
        return axios.get(baseUrl + `api/script/${scriptData.id}`)
    }, {staleTime: 1000*5})

    if (scriptOutputIsLoading){
        return <h2>Loading...</h2>
    }

    console.log(scriptOutputData?.data)

    return (
        <Card sx={{
            marginBottom: "5px;",
        }}>
            <CardContent>
                <StatusBadge status={scriptOutputData?.data[0].status}/>
                <Typography variant={"h6"}>
                    {scriptData.name}
                </Typography>
                <Divider light/>
                <Typography variant={"p"} >
                    Description: {scriptData.description}
                </Typography>
                <Divider light/>
                <Typography variant={"p"} >
                    Details: {scriptOutputData?.data[0].details}
                </Typography>
                <Divider light/>
                <Typography variant={"p"} >
                    Last Ran: {scriptData.lastRan}
                </Typography>
                <Divider light />
            </CardContent>
        </Card>
    );
}

ScriptCard.propTypes = {};