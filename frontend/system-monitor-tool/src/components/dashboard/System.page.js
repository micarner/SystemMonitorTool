import {
    Button,
    Card, CardActions,
    CardContent,
    Checkbox,
    Container,
    ListItemText,
    MenuItem,
    Paper,
    Select,
    Stack,
    TextField
} from "@mui/material";
import SaveIcon from '@mui/icons-material/Save';
import {useQuery} from "react-query";
import axios from 'axios'
import {AppContext} from "../../index";


import {useContext} from "react";
import {useParams} from "react-router-dom";
import AddSystem from "./AddSystem";


function ViewSystem() {
    return null;
}

export default function SystemPage(props) {


    const {baseUrl} = useContext(AppContext);

    const params = useParams();

    const conditionalOutput = () => {
        {
            if (params.systemId === "add") {
                return <AddSystem/>
            } else if (isFinite(params.systemId)){
                return <ViewSystem/>
            } else {
                return <>
                    <CardContent><h2>Invalid System Id</h2></CardContent>
                </>
            }
        }
    }


    return conditionalOutput()

}