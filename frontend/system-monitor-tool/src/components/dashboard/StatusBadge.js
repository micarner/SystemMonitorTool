import {Button, Card} from "@mui/material";

export default function StatusBadge (props) {



    var backgroundColor = ""
    var textColor = ""
    var status = props?.status
    if(props?.status === undefined){
        status = "INVALID STATUS"
    }
    switch (status) {
        case "OK":
            backgroundColor = "#24A148";
            textColor = "black"
            break;
        case "WARN":
            backgroundColor = "#F1C21B";
            textColor = "black"
            break;
        case "CRIT":
            backgroundColor = "#DA1E28";
            textColor = "black"
            break;
        case "UNKNOWN":
            backgroundColor = "#6F6F6F";
            textColor = "black"
            break;
        default:
            backgroundColor = "#DA1E28";
            textColor = "black"
    }

    return (
        <Button
            sx={{
                backgroundColor:backgroundColor,
                color: textColor,
                "&:hover":{
                    backgroundColor:backgroundColor,
                    color: textColor
                }
            }}
        >{status}</Button>
    )

}