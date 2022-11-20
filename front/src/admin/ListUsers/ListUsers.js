import React, { useEffect, useState } from 'react';
import { CardUser } from '../CardUser/CardUser';
import { connect } from 'react-redux';
import './ListUsers.css';

let MOCK_DATA_USER1 = {
  id: 0,
  passport: {
    dateOfBirth: "2022-11-20T04:03:41.357Z",
    serialNumber: '2123',
    passportNumber: '123123',
    firstName: 'Толя',
    lastName: 'Твардовский',
    middleName: 'Иванович',
  },
  createdTime: "2022-11-20T04:03:41.357Z",
  isBlocked: true,
  isValidated: false,
};
let MOCK_DATA_USER2 = {
  id: 0,
  passport: {
    dateOfBirth: "2022-11-20T04:03:41.357Z",
    serialNumber: '2123',
    passportNumber: '123123',
    firstName: 'Кирилл',
    lastName: 'Афанасьев',
    middleName: 'Акакиевич',
  },
  createdTime: "2022-11-20T04:03:41.357Z",
  isBlocked: false,
  isValidated: true,
};
// let { createdTime, id, isBlocked } = data;
// let { firstName, lastName, middleName, dateOfBirth, passportNumber, serialNumber } = data.passport;
function ListUsers({ APImethod, id, jwt, role }) {
  let [listData, setListData] = useState(null);
  useEffect(() => {
    console.log(APImethod);
    if (APImethod == '/api/admin/block') {
      setListData([MOCK_DATA_USER1, MOCK_DATA_USER1, MOCK_DATA_USER1, MOCK_DATA_USER1, MOCK_DATA_USER1])
    } else if (APImethod == '/api/admin/valid') {
      setListData([MOCK_DATA_USER2, MOCK_DATA_USER2, MOCK_DATA_USER2, MOCK_DATA_USER2, MOCK_DATA_USER2])
    }
  }, [])
  if (!listData) {
    return <h1>Loading...</h1>
  }
  return (
    <div className='user-list'>
      {listData.map(elem => {
        console.log(elem);
        return <CardUser data={elem} />
      })
      }
    </div>
  )
}
function mapStateToProps({ id, jwt, role }) {
  return {
    id,
    jwt,
    role,
  }
}
export default connect(mapStateToProps)(ListUsers);