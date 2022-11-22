import React, {createContext} from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import * as PropTypes from "prop-types";
import {QueryClient,QueryClientProvider} from "react-query";
import {ReactQueryDevtools} from "react-query/devtools";

const root = ReactDOM.createRoot(document.getElementById('root'));
const queryClient = new QueryClient();

export const AppContext = createContext({})
const appContext = {
    baseUrl: "http://localhost:8087/"
}

Routes.propTypes = {children: PropTypes.node};
root.render(
  <React.StrictMode>
      <BrowserRouter>
          <AppContext.Provider value={appContext}>
              <QueryClientProvider client={queryClient}>
                  <Routes>
                      <Route path="/*" element={<App/>}/>
                  </Routes>
                  <ReactQueryDevtools/>
              </QueryClientProvider>
          </AppContext.Provider>
      </BrowserRouter>
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
