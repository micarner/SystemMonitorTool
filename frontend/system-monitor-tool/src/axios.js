import axios from "axios";

const instance = axios.create({
    baseURL: "http://localhost:8087/api"
});
export default instance;