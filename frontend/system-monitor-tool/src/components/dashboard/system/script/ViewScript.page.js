import {Button, Card, CardActions, CardContent, Divider, Typography} from "@mui/material";
import StatusBadge from "../../../common/StatusBadge";
import InfoIcon from "@mui/icons-material/Info";
import {Edit} from "@mui/icons-material";
import Loading from "../../../common/Loading";
import {useQuery} from "react-query";
import axios from "axios";
import {useNavigate, useParams} from "react-router-dom";
import {useContext} from "react";
import {AppContext} from "../../../../index";

export default function ViewScriptPage(props){

    const params = useParams();
    const {baseUrl} = useContext(AppContext);
    const navigate = useNavigate();

    const { isLoading: scriptIsLoading, data: scriptData, status: scriptQueryStatus } = useQuery(`script-${params.scriptId}`, () => {
        // console.log(baseUrl + "api/importance")
        return axios.get(baseUrl + `api/script/${params.scriptId}`)
    }, {staleTime: Infinity})

    const { isLoading: scriptOutputIsLoading, data: scriptOutputData} = useQuery(`script-${params.scriptId}-scriptOutput`, () => {
        // console.log(baseUrl + "api/importance")
        return axios.get(baseUrl + `api/script/${params.scriptId}/output`)
    }, {staleTime: 1000*5})

    if (scriptIsLoading || scriptOutputIsLoading){
        return <Loading/>
    }

    var status = "UNKNOWN"
    if (scriptOutputData?.data[0] !== undefined && scriptOutputData?.data[0]?.status !== undefined){
        status = scriptOutputData?.data[0].status
    }

    return (
        <>
            <Card sx={{
                marginBottom: "5px;",
            }}>
                <CardContent>
                    <StatusBadge status={status}/>
                    <Typography variant={"h6"}>
                        {scriptData.data.name}
                    </Typography>
                    <Divider light/>
                    <Typography variant={"p"}>
                        Description: {scriptData.data.description}
                    </Typography>
                    <Divider light/>
                    <Typography variant={"p"}>
                        Details: {scriptOutputData?.data[0]?.details ? scriptOutputData?.data[0].details : ""}
                    </Typography>
                    <Divider light/>
                    <Typography variant={"p"}>
                        Last Ran: {scriptData.data.lastRan}
                    </Typography>
                    <Divider light/>
                </CardContent>
                <CardActions>
                    <Button
                        startIcon={<Edit/>}
                        onClick={() => {
                            navigate(`/script/${scriptData.id}/edit`)
                            // mutation.mutate({name:name,description:description,importance:importance,tagIds:tags})
                        }}
                    >Edit</Button>
                </CardActions>
            </Card>
            <Card>
                <CardContent>
                    <Typography variant={"h6"}>
                        ScriptOutput goes here. Depending on type of data collected, it could be text, table or graph.
                        Text, shows history of changes.
                        Graph you can do stuff with.
                    </Typography>
                </CardContent>
            </Card>
        </>
    )
}