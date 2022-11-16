import {Box, Button, Card, CardContent, Divider, Grid, Paper, Typography} from "@mui/material";
import {useState} from "react";
import {Link as RouterLink} from "react-router-dom";


export default function DashboardPage(props) {



    const [cards, setCards] = useState([
        {
            id:1,
            name:"Test",
            importance:"HIGH",
            state:"OK"
        },
        {
            id:2,
            name:"Test2",
            importance:"HIGH",
            state:"OK"
        },
        {
            id:3,
            name:"Test3",
            importance:"HIGH",
            state:"OK"
        },
    ]);

    function addSystem(){
        setCards((prevState) => {
            return (
                [
                ...cards,
                {
                    id:(cards.length+1),
                    name: "Test" + (cards.length+1),
                    importance:"HIGH",
                    state:"OK"
                }
            ])
        })
    }

    return (
        <>
            <Box className={"dashboard"}>
                {/*<Button onClick={()=> setCards([])}>Reset</Button>*/}
                <Button
                    {...{
                        key: "AddSystem",
                        color: "inherit",
                        to: "/system/add",
                        component: RouterLink,
                    }}
                >Add System</Button>
            </Box>
            <Grid container spacing={2}>
                {cards.map((card, index) => {
                    const {id, name, importance, state} = card;
                    return (<Grid item>
                        <Card key={index}>
                            <CardContent>
                                <Typography variant={"h6"} gutterBottom>
                                    {name}
                                </Typography>
                                <Divider light/>
                                <Typography variant={"caption"} gutterBottom>
                                    Importance:{importance}
                                </Typography>
                                <Divider light/>
                                <Typography variant={"caption"} gutterBottom>
                                    State:{state}
                                </Typography>
                            </CardContent>
                        </Card>
                    </Grid>)
                })}
            </Grid>
        </>

    )
}