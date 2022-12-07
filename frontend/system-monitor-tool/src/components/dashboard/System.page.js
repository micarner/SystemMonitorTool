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
import AddSystemPage from "./AddSystem.page";
import ViewSystemPage from "./ViewSystem.Page";
import EditSystemPage from "./EditSystem.page";


export default function SystemPage(props) {


    const {baseUrl} = useContext(AppContext);

    const params = useParams();

    const conditionalOutput = () => {
        {
            if (params.systemId === "add" ) {
                return <AddSystemPage/>
            } else if (isFinite(params.systemId) && params.edit !== undefined) {
                return <EditSystemPage/>
            } else if (isFinite(params.systemId)){
                return <ViewSystemPage id={params.systemId}/>
            } else {
                return <>
                    <CardContent><h2>Invalid System Id</h2></CardContent>
                </>
            }
        }
    }


    return conditionalOutput()

}