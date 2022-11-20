import * as Yup from 'yup';

const INITIAL_VALUES = {
  login: '',
  password: '',
}

const SIGNUP_SCHEMA = Yup.object().shape({
  login: Yup.string()
    .matches(/^[a-z0-9]{5,15}$/, {
      message: 'Такой логин не подходит',
      excludeEmptyString: false,
    }).required('Поле обязательно для заполнения'),
  password: Yup.string()
    .matches(/^[a-zA-Z0-9/&*^%]{5,15}$/, {
      message: 'Такой пароль не подходит',
      excludeEmptyString: false,
    }).required('Поле обязательно для заполнения'),
});

const LABEL_PLACEHOLDERS = {
  login: ['Логин', 'use/&*^%r'],
  password: ['Пароль', '********'],
}

const MASKS = {};

const API_AUTH_ADMIN = 'v1/admin';
const API_AUTH_CLIENT = 'v1/client';

export { LABEL_PLACEHOLDERS, SIGNUP_SCHEMA, INITIAL_VALUES, MASKS, API_AUTH_ADMIN, API_AUTH_CLIENT };