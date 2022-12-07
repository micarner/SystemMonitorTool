import {useContext} from "react";
import {AppContext} from "../../index";
import {useQuery} from "react-query";
import axios from "axios";
import {Button, Card, CardActions, CardContent, Divider, Grid, Typography} from "@mui/material";
import SystemCard from "./SystemCard";
import SaveIcon from "@mui/icons-material/Save";
import {Edit} from "@mui/icons-material";
import {useNavigate} from "react-router-dom";

export default function ViewSystemPage(props){

    const {baseUrl} = useContext(AppContext);
    const navigate = useNavigate();

    const { isLoading, data} = useQuery(`system-${props.id}`, () => {
        // console.log(baseUrl + "api/importance")
        return axios.get(baseUrl + `api/system/${props.id}`)
    }, {staleTime: 1000*5})

    if (isLoading){
        return <h2>Loading...</h2>
    }

    const {id, name, description, importance, state, tags} = data?.data;
    return (

        <Card>
            <CardContent>
                <Typography variant={"h6"} gutterBottom>
                    Name: {name}
                </Typography>
                <Divider light/>
                <Typography variant={"p"} gutterBottom>
                    Description: {description}
                </Typography>
                <Divider light/>
                <Typography variant={"p"} gutterBottom>
                    Tags: {tags.map(tag => tag.name).join(", ")}
                </Typography>
                <Divider light/>
                <Typography variant={"p"} gutterBottom>
                    Importance:{importance}
                </Typography>
                <Divider light/>
                <Typography variant={"p"} gutterBottom>
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
    )
}