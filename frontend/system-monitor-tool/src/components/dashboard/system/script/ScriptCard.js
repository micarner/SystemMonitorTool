import {useContext} from "react";
import {AppContext} from "../../../../index";
import InfoIcon from '@mui/icons-material/Info';
import {useQuery} from "react-query";
import axios from "axios";
import {Button, Card, CardActions, CardContent, Divider, Typography} from "@mui/material";
import StatusBadge from "../../../common/StatusBadge";
import {Edit} from "@mui/icons-material";
import {useNavigate} from "react-router-dom";
import Loading from "../../../common/Loading";

export default function ScriptCard(props) {
    const scriptData = props.scriptData;
    const {baseUrl} = useContext(AppContext);
    const navigate = useNavigate();

    // console.log(scriptData)

    const { isLoading: scriptOutputIsLoading, data: scriptOutputData} = useQuery(`scriptOutput-${scriptData.id}`, () => {
        // console.log(baseUrl + "api/importance")
        return axios.get(baseUrl + `api/script/${scriptData.id}`)
    }, {staleTime: 1000*5})

    if (scriptOutputIsLoading){
        return <Loading/>
    }

    // console.log(scriptOutputData?.data)

    var status = "UNKNOWN"
    if (scriptOutputData?.data[0] !== undefined && scriptOutputData?.data[0]?.status !== undefined){
        status = scriptOutputData?.data[0].status
    }

    return (
        <Card sx={{
            marginBottom: "5px;",
        }}>
            <CardContent>
                <StatusBadge status={status}/>
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
            <CardActions>
                <Button
                    startIcon={<InfoIcon/>}
                    // onClick={() => {
                    //     navigate(`/script/${id}`)
                    //     // mutation.mutate({name:name,description:description,importance:importance,tagIds:tags})
                    // }}
                >More Info</Button>
                <Button
                    startIcon={<Edit/>}
                    // onClick={() => {
                    //     navigate(`/script/${id}/edit`)
                    //     // mutation.mutate({name:name,description:description,importance:importance,tagIds:tags})
                    // }}
                >Edit</Button>
            </CardActions>
        </Card>
    );
}

ScriptCard.propTypes = {};