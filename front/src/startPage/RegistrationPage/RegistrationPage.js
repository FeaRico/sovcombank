import React, { useContext } from 'react';
import { Formik, Form } from 'formik';
import './RegistrationPage.css';
import { CustomField } from '../../common/common';
import { SIGNUP_SCHEMA, LABEL_PLACEHOLDERS, INITIAL_VALUES, MASKS } from './constants'
import { useNavigate } from 'react-router-dom';
import { setUserData } from '../../actions/actions';
import { registrate } from '../../services/registrate';
import { IP_ADDRESS } from '../../App/App';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';

// Для добавления новых полей, нужно перейти в файл constants.js и ввести соотвественно изменения в:
// SIGNUP_SCHEMA, LABEL_PLACEHOLDERS, INITIAL_VALUES, MASKS

function RadioButton({ handleChange }) {
  return (
    <fieldset>
      <div>
        <legend>Ваш пол:</legend>
        <input onChange={handleChange} type="radio" id="male" name="gender" value='male' checked />
        <label htmlFor="male">Мужской</label>
      </div>
      <div>
        <input onChange={handleChange} type="radio" id="female" name="gender" value='female' />
        <label htmlFor="female">Женский</label>
      </div>
    </fieldset>
  )
}

function RegistrationPage() {
  const navigate = useNavigate();
  const IP = useContext(IP_ADDRESS)

  const handleSwitchForm = (event) => {
    navigate('auth');
  }

  const handleSubmit = async (values) => {
    let { login: username, password, patronymic: middleName, firstName,
      lastName, passportID: passportNumber, passportSeries: serialNumber, dateBirth: dateOfBirth } = values;
    let data = {
      username,
      password,
      passport: {
        middleName,
        firstName,
        lastName,
        passportNumber,
        serialNumber,
        dateOfBirth: "2022-11-20T06:17:19.371Z",
        createdTime: "2022-11-20T06:17:19.371Z",
        changedTime: "2022-11-20T06:17:19.371Z",
      }
    }
    let answer = await registrate(IP, data)
    if (answer.ok) {
      setUserData({
        id: answer.id,
        jwt: answer.jwt,
        role: answer.role,
      })
      if (answer.role === 'admin') navigate('/admin')
      else navigate('/client')
    } else {
      alert('Введены неверные данные')
    }
  }

  return (
    <div className='reg-form'>
      <Formik
        initialValues={INITIAL_VALUES}
        onSubmit={handleSubmit}
        validationSchema={SIGNUP_SCHEMA}
      >
        {({ errors, touched, handleChange, handleBlur }) => (
          <Form className='form'>
            {Object.keys(INITIAL_VALUES).map((value, index) => {
              return (
                <CustomField
                  key={`form__input${index}1`}
                  errors={errors} touched={touched}
                  value={value} MASKS={MASKS}
                  LABEL_PLACEHOLDERS={LABEL_PLACEHOLDERS}
                  handleChange={handleChange}
                  handleBlur={handleBlur}
                />
              );
            })}
            <button className='form__btn form__btn_reg' type="submit">Зарегистрироваться</button>
          </Form>
        )}
      </Formik>
      <button onClick={(event) => handleSwitchForm(event)} className='form__btn form__btn_come' type='button'>Войти</button>
    </div>
  )
}

function mapDispatchToProps(dispatch) {
  return bindActionCreators({ setUserData }, dispatch)
}

export default connect(null, mapDispatchToProps)(RegistrationPage);