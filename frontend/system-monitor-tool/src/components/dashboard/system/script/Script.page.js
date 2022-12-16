import {useContext} from "react";
import {AppContext} from "../../../../index";
import {useParams} from "react-router-dom";

import {CardContent} from "@mui/material";
import EditScriptPage from "./EditScript.page";
import ViewScriptPage from "./ViewScript.page";

export default function ScriptPage(props){
    const {baseUrl} = useContext(AppContext);

    const params = useParams();

    const conditionalOutput = () => {
        {
            if (isFinite(params.scriptId) && params.edit !== undefined) {
                return <EditScriptPage/>
            } else if (isFinite(params.scriptId)){
                return <ViewScriptPage id={params.scriptId}/>
            } else {
                return <>
                    <CardContent><h2>Invalid Script Id</h2></CardContent>
                </>
            }
        }
    }


    return conditionalOutput()
}