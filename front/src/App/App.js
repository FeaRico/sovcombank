import './App.css';
import React from 'react';
import RegistrationPage from '../startPage/RegistrationPage/RegistrationPage.js';
import { Route, Routes } from "react-router-dom";
import AuthPage from '../startPage/AuthPage/AuthPage';
import { Provider } from 'react-redux';
import { store } from '../store';
import DashBoard from '../client/DashBoard/DashBoard';
import DashBoardAdmin from '../admin/DashBoard/DashBoard';
const IP_ADDRESS = React.createContext('http://79.133.181.84:8080/');

function App() {
  return (
    <div className="App">
      <IP_ADDRESS.Provider value='http://79.133.181.84:8080/'>
        <Provider store={store}>
          <Routes>
            <Route path='/' element={<RegistrationPage />} />
            <Route path='/auth' element={<AuthPage />} />
            <Route path='/admin/*' element={<DashBoardAdmin />} />
            <Route path='/client/*' element={<DashBoard />} />
          </Routes>
        </Provider>
      </IP_ADDRESS.Provider>
    </div>
  );
}
export { IP_ADDRESS };
export default App;
