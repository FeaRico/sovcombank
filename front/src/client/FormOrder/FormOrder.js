import React, { useEffect, useState } from 'react';
import { CustomSelect } from '../../common/common';
import { Formik, Form, Field } from 'formik';
import './FormOrder.css'

function FormOrder({ listAccs, id }) {
  let [accountId, setAccountId] = useState();
  let [currency, setCurrency] = useState('dollar');
  let [koef, setKoef] = useState(0.5);
  let [sumRub, setSumRub] = useState(300);
  let [sumForein, setSumForein] = useState();

  const handleChangeAccountId = (event) => {
    setAccountId(event.target.value);
  }
  const handleChangeCurrency = (event) => {
    setCurrency(event.target.value)
  }
  const handleChangeSumRub = (event) => {
    let inputTxt = event.target.value;
    console.log(inputTxt);
    if (inputTxt.match(/^[0-9]{3,7}$/)) {
      setSumRub(inputTxt)
    } else return;
  }
  useEffect(() => {
    setSumForein(koef * sumRub);
  }, [sumRub])

  const handleSubmit = async (event) => {
    event.preventDefault();
    console.log(sumForein);
  }
  return (
    <div className='form-order-wrapper'>
      <form className='form-order' action='#' onSubmit={handleSubmit}>
        <span className='form-order__field'>
          Сейчас на балансе: {sumForein}
        </span>
        <input type='text'
          className='form-order__field'
          onChange={handleChangeSumRub}
          value={sumRub}
          placeholder={'Введите сумму в рублях'}
        />
        <CustomSelect
          className='form-order__field'
          handleChange={handleChangeCurrency}
          list={["доллары", "юань", "йен", "франки"]}
          value={currency}
        />
        <span className='form-order__field'>
          {sumForein}
        </span>
        <button className='form-order__btn'>Купить</button>
        <button className='form-order__btn'>Продать</button>
      </form>
    </div>
  )
}

export { FormOrder }