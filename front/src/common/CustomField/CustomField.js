import React from 'react';
import { Field } from 'formik';
import InputMask from 'react-text-mask';

function CustomField(props) {
  const { errors, touched, value } = props;
  const { MASKS, LABEL_PLACEHOLDERS } = props;
  const { handleChange, handleBlur } = props;
  let [label, placeholder] = LABEL_PLACEHOLDERS[value];

  let mask = MASKS[value];
  if (mask) {
    return (
      <div className='form__elem'>
        <label htmlFor={value}>{label}</label>
        <InputMask onChange={handleChange} onBlur={handleBlur} mask={mask} id={value} name={value} placeholder={placeholder} />
        {errors[value] && touched[value] && <div className='form__error'>{errors[value]}</div>}
      </div>)
  }
  return (
    <div className='form__elem'>
      <label htmlFor={value}>{label}</label>
      <Field type={value === 'password' ? 'password' : 'text'} id={value} name={value} placeholder={placeholder} />
      {errors[value] && touched[value] && <div className='form__error'>{errors[value]}</div>}
    </div>
  )
}

export { CustomField };