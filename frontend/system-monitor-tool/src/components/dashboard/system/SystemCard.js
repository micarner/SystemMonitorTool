import {Card, CardContent, Divider, Grid, Typography} from "@mui/material";
import {useNavigate} from "react-router-dom";
import StatusBadge from "../../common/StatusBadge";

export default function SystemCard(props) {

    const navigate = useNavigate();

    //TODO:
    //  - Create BadgeSection that contains StatusBadges
    //  - Check for and print

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
                    {statusCounts.map(statusCount => {
                        //TODO: How to handle hashmap serialization in DTO? probably some jsonproperties
                        //  or jackson annotation
                        return <StatusBadge status={statusCount.status} count={statusCount.count}/>
                    })}

                    <Typography variant={"h6"} >
                        {name}
                    </Typography>
                    <Divider light/>
                    <Typography variant={"caption"} >
                        Importance:{importance}
                    </Typography>
                    <Divider light/>
                    <Typography variant={"caption"} >
                        Status:{status}
                    </Typography>
                </CardContent>
            </Card>
        </Grid>
    )

}