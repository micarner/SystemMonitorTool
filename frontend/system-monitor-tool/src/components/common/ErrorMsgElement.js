import {Typography} from "@mui/material";
import React from "react";

export default function ErrorMsgElement(props) {
    return (
        <Typography component={"p"} aria-live={"assertive"} //ref={errRef}
                    sx={{visibility: props.errMsg ? "visible" : "hidden",
                        color: "red", minHeight: "16px"}}
        >{props.errMsg}</Typography>
    )
}