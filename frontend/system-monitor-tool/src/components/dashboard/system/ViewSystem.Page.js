import {useContext} from "react";
import {AppContext} from "../../../index";
import {useQuery} from "react-query";
import axios from "axios";
import {Button, Card, CardActions, CardContent, Divider, Grid, Typography} from "@mui/material";
import SystemCard from "./SystemCard";
import SaveIcon from "@mui/icons-material/Save";
import {Edit} from "@mui/icons-material";
import {useNavigate} from "react-router-dom";
import * as PropTypes from "prop-types";
import StatusBadge from "../../common/StatusBadge";
import ScriptCard from "./script/ScriptCard";
import Loading from "../../common/Loading";


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

    if (syatemIsLoading || scriptsAreLoading){
        return <Loading/>
    }
    // console.log(systemData?.data)

    const {id, name, description, importance, status, tags} = systemData?.data;
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
                    Status:{status}
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
        <SystemScriptsView scriptsData={scriptsData}/>
        </>
    )
}

function SystemScriptsView(props) {
    var boolean = true
    return (
        <>
            {props.scriptsData?.data.map(scriptData =>
                <ScriptCard scriptData={scriptData} key={scriptData.id}/>
            )}
        </>
    );

}
SystemScriptsView.propTypes = {scriptsData: PropTypes.any};


