import React from 'react';
import { Routes, Route } from 'react-router';
import { Navigation } from '../../common/Navigation/Navigation';
import ListUsers from '../ListUsers/ListUsers';

function DashBoard() {
  return (
    <div className='dashboard'>
      <Navigation navigationList={
        [
          { route: 'blocked', placeholder: 'Заблокированные пользователи' },
          { route: 'validated', placeholder: 'Валидированные пользователи' },
        ]
      } />
      <Routes>
        <Route exact path='blocked' element={<ListUsers APImethod={'/api/admin/block'} />} />
        <Route exact='validated' element={<ListUsers APImethod={'/api/admin/valid'} />} />
      </Routes>
    </div>
  )
}

export default DashBoard;