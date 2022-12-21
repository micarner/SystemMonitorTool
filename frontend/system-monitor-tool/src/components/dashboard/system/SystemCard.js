import {Card, CardContent, Divider, Grid, Typography} from "@mui/material";
import {useNavigate} from "react-router-dom";
import StatusBadge from "../../common/StatusBadge";

export default function SystemCard(props) {

    const navigate = useNavigate();

    const {id, name, importance, status, statusCounts} = props.card;
    const index = props.index
    return (
        <Grid item>
            <Card
                key={index}
                sx={{
                    '&:hover': {
                        boxShadow: 4, // theme.shadows[20],
                        cursor: "pointer"
                    }
                }}
                onClick={() => {
                    navigate(`/system/${id}`)
                }}>
                <CardContent>
                    <StatusBadge status={status}/>
                    <Typography variant={"h6"} >
                        {name}
                    </Typography>
                    <Divider light/>
                    <Typography variant={"caption"} >
                        Importance:{importance}
                    </Typography>
                    <Divider light/>
                    {Object.keys(statusCounts).map((key, index) =>
                        <StatusBadge status={key} count={statusCounts[key]} key={index}/>
                    )}
                </CardContent>
            </Card>
        </Grid>
    )

}