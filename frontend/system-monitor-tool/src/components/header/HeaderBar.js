import {AppBar, Box, Button, Container, Menu, MenuItem, Toolbar, Typography} from "@mui/material";
import MonitorHeartIcon from "@mui/icons-material/MonitorHeart";

const pages = ['Dashboard'];

export default function HeaderBar(props) {

    return (
        <AppBar position={"static"}>
            <Container maxWidth={"x1"}>
                <Toolbar disableGutters>
                    <MonitorHeartIcon sx={{ mr: 1 }}/>
                    <Typography
                        variant="h6"
                        noWrap
                        component="a"
                        href="/"
                        sx={{
                            mr: 2,
                            display: 'flex',
                            fontFamily: 'monospace',
                            fontWeight: 700,
                            letterSpacing: '.3rem',
                            color: 'inherit',
                            textDecoration: 'none',
                        }}
                    >
                        SysMonTool
                    </Typography>
                    <Button
                        // key={page}
                        // onClick={handleCloseNavMenu}
                        sx={{ my: 2, color: 'white', display: 'block' }}
                        component="a"
                        href="/"
                    >
                        Dashboard
                    </Button>
                    {/*<Box sx={{ flexGrow: 1, display: 'flex' }}>*/}
                    {/*    */}
                    {/*    {pages.map((page) => (*/}
                    {/*        <Button*/}
                    {/*            key={page}*/}
                    {/*            // onClick={handleCloseNavMenu}*/}
                    {/*            sx={{ my: 2, color: 'white', display: 'block' }}*/}
                    {/*        >*/}
                    {/*            {page}*/}
                    {/*        </Button>*/}
                    {/*    ))}*/}
                    {/*</Box>*/}
                    {/*TODO: Settings/login icon here*/}
                    {/*This part will be right-aligned*/}
                    {/*<Box sx={{ display: 'block' }}>*/}
                    {/*    {pages.map((item) => (*/}
                    {/*        <Button key={item} sx={{ color: '#fff' }}>*/}
                    {/*            {item}*/}
                    {/*        </Button>*/}
                    {/*    ))}*/}
                    {/*</Box>*/}
                </Toolbar>
            </Container>
        </AppBar>
    )
}