import {Navigate, useNavigate, useParams} from "react-router-dom";
import {useContext} from "react";
import {AppContext} from "../../../../index";
import {useState} from "react";
import {Button, Card, CardActions, CardContent, Slider, TextField, Typography} from "@mui/material";
import {useMutation, useQuery} from "react-query";
import axios from "axios";
import Loading from "../../../common/Loading";
import {useEffect} from "react";
import InfoIcon from "@mui/icons-material/Info";
import CancelIcon from '@mui/icons-material/Cancel';
import {Save} from "@mui/icons-material";

export default function EditScriptPage(props){

    const params = useParams();
    const {baseUrl} = useContext(AppContext);
    const navigate = useNavigate();

    const [description, setDescription] = useState('');
    const [frequencyToCheck, setFrequencyToCheck] = useState(10);

    const mutation = useMutation({
        mutationFn: updateScript => {
            return axios.put(baseUrl + "api/script", updateScript);
        }
    })

    const { isLoading: scriptIsLoading, data: scriptData, status: scriptQueryStatus } = useQuery(`script-${params.scriptId}-edit`, () => {
        // console.log(baseUrl + "api/importance")
        return axios.get(baseUrl + `api/script/${params.scriptId}`)
    }, {staleTime: Infinity})


    //Load systemData into state
    useEffect(() => {
        if (scriptQueryStatus === 'success'){
            setDescription(scriptData?.data.description)
            setFrequencyToCheck(scriptData?.data.frequencyToCheck / 1000)
        }
    }, [scriptQueryStatus,scriptData]);

    if (scriptIsLoading){
        return <Loading/>
    }

    if (mutation.isSuccess) {
        navigate(-1,{replace: true});
    }

    // console.log(scriptData.data)

    return (
        <Card>
            <CardContent>
                <Typography variant={"h6"}>
                    Script Name: {scriptData?.data.name}
                </Typography>
                <TextField
                    sx={{marginTop:"10px;"}}
                    label={"Description"}
                    value={description}
                    onChange={(e) => setDescription(e.target.value)}
                    fullWidth
                    multiline/>
                <Typography variant={"h6"} sx={{marginTop:"30px"}}>
                    Frequency to run script (seconds)
                </Typography>
                <Slider
                    sx={{margin: "35px 10px 0 10px"}}
                    // defaultValue={scriptData?.data.frequencyToCheck ? scriptData?.data.frequencyToCheck : 10 }
                    defaultValue={scriptData?.data.frequencyToCheck}
                    value={frequencyToCheck}
                    onChange={(event, value) => setFrequencyToCheck(value)}
                    valueLabelDisplay="on"
                    step={1}
                    min={10}
                    max={60}
                />
            </CardContent>
            <CardActions>
                <Button
                    startIcon={<Save/>}
                    onClick={() => {
                        // navigate(`/script/${scriptData.id}`)
                        var script = {
                            ...scriptData?.data,
                            description:description,
                            frequencyToCheck:(frequencyToCheck * 1000)
                        }
                        // console.log(script)
                        mutation.mutate(script)
                    }}
                >Save</Button>
                <Button
                    startIcon={<CancelIcon/>}
                    onClick={() => {
                        navigate(-1)
                    }}
                >Cancel</Button>
            </CardActions>
        </Card>
    )
}