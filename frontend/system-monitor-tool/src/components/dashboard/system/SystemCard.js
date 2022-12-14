import {Card, CardContent, Divider, Grid, Typography} from "@mui/material";
import {useNavigate} from "react-router-dom";

export default function SystemCard(props) {

    const navigate = useNavigate();

    const {id, name, importance, state} = props.card;
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

                    <Typography variant={"h6"} >
                        {name}
                    </Typography>
                    <Divider light/>
                    <Typography variant={"caption"} >
                        Importance:{importance}
                    </Typography>
                    <Divider light/>
                    <Typography variant={"caption"} >
                        State:{state}
                    </Typography>
                </CardContent>
            </Card>
        </Grid>
    )

}