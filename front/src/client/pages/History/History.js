import React, { useEffect, useState } from 'react';
import './History.css';
import { connect } from 'react-redux';

const MOC_DATA = [
  { currency: 'P', balance: 120, amount: 123, createdTime: '12.12.12', type: 'Изъятие' },
  { currency: '$', balance: 0, amount: 123, createdTime: '12.12.12', type: 'Пополнение' },
  { currency: '$', balance: 0, amount: 333, createdTime: '12.12.12', type: 'Изъятие' }
];
function OperationInfo({ currency, balance, createdTime, amount, type }) {
  return (
    <div className='list-operations-item'>
      <div className='list-operations-item__date'>
        <span>Время операции: </span>
        <span>{createdTime}</span>
      </div>
      <div className='list-operations__wrapper'>
        <div className='list-operations-item__amount'>
          <span>Объем денежных средств: </span>
          <span>{amount}</span>
        </div>
        <div className='list-operations-item__currency'>
          <span>Валюта: </span>
          <span>{currency}</span>
        </div>
        <div className='list-operations-item__balance'>
          <span>Баланс: </span>
          <span>{balance}</span>
        </div>
        <div className='list-operations-item__type'>
          <span>Тип операции: </span>
          <span>{type}</span>
        </div>
      </div>
    </div>
  )
}


function History() {
  let [arrOfOperations, setArrOfOperations] = useState(null);

  useEffect(() => {
    setArrOfOperations(MOC_DATA);
  }, [])
  if (!arrOfOperations) {
    return <h1>Loading...</h1>
  }
  return (
    <div className='history'>

      <div className='list-operations'>
        {arrOfOperations.map(({ currency, amount, balance, createdTime, type }) => {
          return (
            <OperationInfo
              amount={amount}
              currency={currency}
              balance={balance}
              createdTime={createdTime}
              type={type}
            />)
        })}
      </div>
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
export default connect(mapStateToProps)(History);