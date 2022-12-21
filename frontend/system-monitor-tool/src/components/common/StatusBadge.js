import {Button, Card} from "@mui/material";

export default function StatusBadge (props) {



    var backgroundColor = ""
    var textColor = ""
    var status = props?.status
    var count = props?.count
    var size = "small"
    var padding = "2px"
    if (count === undefined){
        count = ""
        size = "medium"
        padding = null
    }
    if(status === undefined){
        status = "INVALID STATUS"
    }
    switch (status) {
        case "OK":
            backgroundColor = "#24A148";
            textColor = "white"
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
        <Button size={size} //Size small if counts !== null, such as in SystemCard
            sx={{
                backgroundColor:backgroundColor,
                color: textColor,
                "&:hover":{
                    backgroundColor:backgroundColor,
                    color: textColor
                },
                margin: "5px;",
                padding: padding
            }}
        >{count} {status}</Button>
    )

}