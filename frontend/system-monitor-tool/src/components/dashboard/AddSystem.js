import {useMutation, useQuery} from "react-query";
import axios from "axios";
import {
    Button,
    Card,
    CardActions,
    CardContent,
    Checkbox,
    ListItemText,
    MenuItem,
    Select,
    Stack,
    TextField
} from "@mui/material";
import SaveIcon from "@mui/icons-material/Save";
import {AppContext} from "../../index";
import {useContext, useState} from "react";


export default function AddSystem(props){

    const {baseUrl} = useContext(AppContext);

    const [name, setName] = useState("");
    const [description, setDescription] = useState('');
    const addSystem = useMutation({
        mutationFn: newSystem => {
            return axios.post(baseUrl + "")
        }
    })

    const { isLoading: importanceIsLoading, data: importanceData } = useQuery('importance', () => {
        // console.log(baseUrl + "/importance")
        return axios.get(baseUrl + "/importance")
    })

    const { isLoading: tagsIsLoading, data: tagsData } = useQuery('tags', () => {
        // console.log(baseUrl + "/importance")
        return axios.get(baseUrl + "/tags")
    })

    if (importanceIsLoading && tagsIsLoading){
        return <h2>Loading...</h2>
    }


    return (
        <Card>
            <CardContent>
                <Stack direction={"row"}>
                    <TextField
                        label={"Name"}
                        value={name}
                        onChange={(e) => setName(e.target.value)}
                        sx={{minWidth: "300px;"}}/>
                    <TextField select label={"Importance"} sx={{minWidth: "100px"}}
                               defaultValue={importanceData?.data[1]}>
                        {importanceData?.data.map(option => {
                            return <MenuItem key={option} value={option}>{option}</MenuItem>
                        })}
                    </TextField>
                    <Select>
                        {tagsData?.data.map(option => (
                            <MenuItem key={option} value={option}>{option}
                                <Checkbox/>
                                <ListItemText primary={"option"}/>
                            </MenuItem>
                        ))}
                    </Select>
                </Stack>
                <TextField
                    label={"Description"}
                    value={description}
                    onChange={(e) => setDescription(e.target.value)}
                    fullWidth
                    multiline/>
            </CardContent>
            <CardActions>
                <Button
                    startIcon={<SaveIcon/>}
                    onClick={}
                >Add System</Button>
            </CardActions>
        </Card>
    )
}