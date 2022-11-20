import { useState } from 'react';
import React from 'react';
import './FormCreateAcc.css';
import { CustomSelect } from '../../common/CustomSelect/CustomSelect'
function FormCreateAcc() {
  let [isFormOpened, setIsFormOpened] = useState(false);
  let [currency, setCurrency] = useState('dollars');
  const handleOpenForm = () => {
    console.log('form-create-acc ' + (isFormOpened ? 'form-create-acc_closed' : ''))
    setIsFormOpened(true);
  }
  const handleChangeCurrency = (event) => {

    setCurrency(event.target.value);
  }
  const handleReject = (event) => {
    event.preventDefault();
    setIsFormOpened(false);
  }
  const handleCreateAcc = (event) => {
    event.preventDefault();
    console.log('create!')
  }
  return (
    <div className='form-create-acc-wrapper'>
      <button className='form-create-acc-wrapper__btn form-create-acc-wrapper__btn_open' onClick={handleOpenForm}>Создать новый счет</button>
      <form className={'card form-create-acc ' + (isFormOpened ? 'form-create-acc_opened' : '')} action='#'>
        <span className='form-create-acc__head'>Открытие нового счета</span>
        <div className="form-create-acc__select">
          <CustomSelect
            handleChange={handleChangeCurrency}
            list={["доллары", "юань", "йен", "франки"]}
            value={currency}
          />
        </div>
        <div className="form-create-acc__btns">
          <button onClick={handleReject} className='form-create-acc__btn form-create-acc__btn_reject'>Отмена</button>
          <button onClick={handleCreateAcc} className='form-create-acc__btn form-create-acc__btn_create'>Создать счет</button>
        </div>
      </form>
    </div>
  )
}

export { FormCreateAcc }