import logo from './logo.svg';
import './App.css';
import {AppBar, Box, Container, Toolbar, Typography} from "@mui/material";
import {Route,Routes} from "react-router-dom";
import DashboardPage from "./components/dashboard/Dashboard.page";
import SystemPage from "./components/dashboard/system/System.page";
import ScriptPage from "./components/dashboard/system/script/Script.page";
import MonitorHeartIcon from '@mui/icons-material/MonitorHeart';
import HeaderBar from "./components/header/HeaderBar";




function App() {
  return (
      <Box className={"app"}
           sx={{
             height: "100%;",
             width: "100%;",
             contain: "layout;",
             overflowY: "auto;",
               backgroundColor: "#f5f5f5"
           }}>
          {/*Header goes here*/}
          <HeaderBar/>

          <Container className={"app-content"}
                     component={"section"}
                     sx={{marginTop: "80px", width: "100%"}}>


              <Routes>
                  <Route path="" element={<DashboardPage/>}/>
                  <Route path="management" element={<DashboardPage/>}/>

                  <Route path="system/:systemId" element={<SystemPage/>}/>
                  <Route path="system/:systemId/:edit" element={<SystemPage/>}/>

                  <Route path="script/:scriptId" element={<ScriptPage/>}/>
                  <Route path="script/:scriptId/:edit" element={<ScriptPage/>}/>
              </Routes>


          </Container>

      </Box>
  );
}

export default App;
