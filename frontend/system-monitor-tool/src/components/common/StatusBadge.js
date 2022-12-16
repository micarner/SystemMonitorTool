import {Button, Card} from "@mui/material";

export default function StatusBadge (props) {



    var backgroundColor = ""
    var textColor = ""
    var status = props?.status
    var count = props?.count
    if (count === undefined){
        count = ""
    }
    if(status === undefined){
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
        case "DOWN":
            backgroundColor = "#000000";
            textColor = "white"
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
        >{count} {status}</Button>
    )

}