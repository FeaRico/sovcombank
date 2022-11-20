import React from 'react';
import { Routes, Route } from 'react-router';
import { Navigation } from '../../common/Navigation/Navigation';
import Accounts from '../pages/Accounts/Accounts';
import History from '../pages/History/History';
import InvestPortfolio from '../pages/InvestPortfolio/InvestPortfolio';


import './DashBoard.css';

function DashBoard() {

  return (
    <div className='dashboard'>
      <Navigation navigationList={
        [
          { route: 'accounts', placeholder: 'Управление счетами' },
          { route: 'investPortfolio', placeholder: 'Портфель' },
          { route: 'history', placeholder: 'История' }
        ]
      } />
      <Routes>
        <Route path='accounts' element={<Accounts />} />
        <Route path='investPortfolio' element={<InvestPortfolio />} />
        <Route path='history' element={<History />} />
      </Routes>
    </div>
  )
}

export default DashBoard;