import {useMutation, useQuery} from "react-query";
import axios from "axios";
import {
    Button,
    Card,
    CardActions,
    CardContent,
    Checkbox, FormControl, InputLabel,
    ListItemText,
    MenuItem,
    Select,
    Stack,
    TextField
} from "@mui/material";
import SaveIcon from "@mui/icons-material/Save";
import {AppContext} from "../../index";
import {useContext, useEffect, useState} from "react";


export default function AddSystem(props){

    const {baseUrl} = useContext(AppContext);

    const [name, setName] = useState("");
    const [description, setDescription] = useState('');
    const [importance, setImportance] = useState('');
    const [tags, setTags] = useState([]);
    const mutation = useMutation({
        mutationFn: newSystem => {
            return axios.post(baseUrl + "api/system/new", newSystem);
        }
    })

    const { isLoading: importanceIsLoading, data: importanceData } = useQuery('importance', () => {
        // console.log(baseUrl + "api/importance")
        return axios.get(baseUrl + "api/importance")
    }, {staleTime: Infinity})

    const { isLoading: tagsIsLoading, data: tagsData } = useQuery('tags', () => {
        // console.log(baseUrl + "api/importance")
        return axios.get(baseUrl + "api/tags")
    }, {staleTime: Infinity})

    useEffect(() => {
    //     // console.log(`name:${name},description:${description},importance:${importance},tags:${tags},`)
        console.log({name:name,description:description,importance:importance,tags:tags})
    }, [name,description,importance,tags]);

    if (importanceIsLoading && tagsIsLoading){
        return <h2>Loading...</h2>
    }

    if (tagsData?.data.length === 0){
        tagsData.data = [{id:0,name:"None"}]
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
                    <FormControl>
                        <InputLabel>Importance</InputLabel>
                        <Select label="Importance" sx={{minWidth: "200px"}}
                                defaultValue={importanceData?.data[1]} value={importance}
                                onChange={(e) => setImportance(e.target.value)}>
                            {importanceData?.data.map(option => {
                                return <MenuItem key={option} value={option}>{option}</MenuItem>
                            })}
                        </Select>
                    </FormControl>
                    <FormControl>
                        <InputLabel>Tags</InputLabel>
                        <Select multiple value={tags} label="Tags" sx={{minWidth:"200px;"}} onChange={(e) => setTags(e.target.value)}>
                            {tagsData?.data.map(option => (
                                <MenuItem key={option.name} value={option.id}>
                                    {option.name}
                                    {/*<Checkbox/>*/}
                                    {/*<ListItemText primary={"option"}>{option.name}</ListItemText>*/}
                                </MenuItem>
                            ))}
                        </Select>
                    </FormControl>
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
                    onClick={() => {
                        mutation.mutate({name:name,description:description,importance:importance,tags:tags})
                    }}
                >Add System</Button>
            </CardActions>
        </Card>
    )
}