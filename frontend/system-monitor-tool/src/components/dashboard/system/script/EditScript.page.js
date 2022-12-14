import {useParams} from "react-router-dom";
import {useContext} from "react";
import {AppContext} from "../../../../index";
import {useState} from "react";
import {Card, CardContent, Slider, TextField, Typography} from "@mui/material";
import {useMutation, useQuery} from "react-query";
import axios from "axios";
import Loading from "../../../common/Loading";
import {useEffect} from "react";

export default function EditScriptPage(props){

    const params = useParams();
    const {baseUrl} = useContext(AppContext);

    const [description, setDescription] = useState('');

    const mutation = useMutation({
        mutationFn: updateScript => {
            return axios.post(baseUrl + "api/script", updateScript);
        }
    })

    const { isLoading: scriptIsLoading, data: scriptData, status: scriptQueryStatus } = useQuery('importance', () => {
        // console.log(baseUrl + "api/importance")
        return axios.get(baseUrl + `api/script/${params.scriptId}`)
    }, {staleTime: Infinity})

    //Load systemData into state
    useEffect(() => {
        if (scriptQueryStatus === 'success'){
            setDescription(scriptData?.data.description)
        }
    }, [scriptQueryStatus,scriptData]);

    if (scriptIsLoading){
        return <Loading/>
    }

    return (
        <Card>
            <CardContent>
                <Typography variant={"h6"}>
                    Script Name: {scriptData?.data.name}
                </Typography>
                <TextField
                    label={"Description"}
                    value={description}
                    onChange={(e) => setDescription(e.target.value)}
                    fullWidth
                    multiline/>
                <Typography variant={"p"}>
                    Frequency to run script
                </Typography>
                <Slider
                    defaultValue={scriptData?.data.frequencyToCheck}
                    step={1}
                    min={10}
                    max={60}
                />
            </CardContent>
        </Card>
    )
}