import React from 'react';
import './CardUser.css';

function CardUser({ data }) {
  console.log(data);
  let { createdTime, id, isBlocked } = data;
  let { firstName, lastName, middleName, dateOfBirth, passportNumber, serialNumber } = data.passport;
  function handleClick() {
    if (isBlocked) {
      console.log('unBlock!')
    } else {
      console.log('Block!')
    }
  }
  return (
    <div className={'card-user card ' + (isBlocked ? 'card-user__status_blocked' : 'card-user__status_validated')}>
      <span>Время создания: </span><span className='card-user__time'>{createdTime}</span>
      <ul className='card-user-info__list'>
        <li className='card-user-info__item'><span>Имя: </span><span>{firstName}</span></li>
        <li className='card-user-info__item'><span>Фамилия: </span><span>{lastName}</span></li>
        <li className='card-user-info__item'><span>Отчество: </span><span>{middleName}</span></li>
        <li className='card-user-info__item'><span>Дата рождения: </span><span>{dateOfBirth}</span></li>
        <li className='card-user-info__item'><span>Номер паспорта: </span><span>{passportNumber}</span></li>
        <li className='card-user-info__item'><span>Серия  паспорта: </span><span>{serialNumber}</span></li>
      </ul>
      {isBlocked ? <button onClick={handleClick} className='card-user__btn card-user__btn_block'>Заблокировать</button>
        : <button onClick={handleClick} className='card-user__btn card-user__btn_unblock'>Разблокировать</button>}
    </div>
  )
}

export { CardUser }