import * as Yup from 'yup';

const INITIAL_VALUES = {
  firstName: '',
  lastName: '',
  patronymic: '',
  passportSeries: '',
  passportID: '',
  dateBirth: '',
  login: '',
  password: '',
}

const SIGNUP_SCHEMA = Yup.object().shape({
  firstName: Yup.string()
    .matches(/^[а-яА-ЯёЁ]{1,50}$/, {
      message: 'Введите символы количеством от 1 до 50'
    })
    .required('Поле обязательно для заполнения'),
  lastName: Yup.string()
    .matches(/^[а-яА-ЯёЁ]{1,50}$/, {
      message: 'Введите символы количеством от 1 до 50'
    })
    .required('Поле обязательно для заполнения'),
  patronymic: Yup.string()
    .matches(/^[а-яА-ЯёЁ]{1,50}$/, {
      message: 'Введите символы количеством от 1 до 50'
    })
    .required('Поле обязательно для заполнения'),
  passportSeries: Yup.string()
    .length(4, 'Введите серию паспорта')
    .matches(/[0-9]{4}/, {
      message: "Не верно введена серия пасспорта",
      excludeEmptyString: false,
    })
    .required('Поле обязательно для заполнения'),
  passportID: Yup.string()
    .length(6, 'Введите номер паспорта')
    .matches(/[0-9]{6}/, {
      message: "Не верно введен номер пасспорта",
      excludeEmptyString: false,
    }).required('Поле обязательно для заполнения'),
  dateBirth: Yup.string()
    .matches(/[0-9]{2}.[0-9]{2}.[0-9]{4}/, {
      message: "Не верно введена дата рождения",
      excludeEmptyString: false,
    }).required('Поле обязательно для заполнения'),
  login: Yup.string()
    .matches(/^[a-z0-9]{5,15}$/, {
      message: 'Такой логин не подходит',
      excludeEmptyString: false,
    })
    .required('Поле обязательно для заполнения'),
  password: Yup.string()
    .matches(/^[a-z0-9A-Z/&*^%]{5,15}$/, {
      message: 'Такой пароль не подходит',
      excludeEmptyString: false,
    })
    .required('Поле обязательно для заполнения'),
});

const LABEL_PLACEHOLDERS = {
  firstName: ['Имя', 'Иван'],
  lastName: ['Фамилия', 'Иванов'],
  patronymic: ['Отчество', 'Иванович'],
  passportSeries: ['Серия', '55555'],
  passportID: ['Номер', '555555'],
  dateBirth: ['Дата рождения', '01.01.1978'],
  login: ['Логин', 'user123'],
  password: ['Пароль', '********']
}

const MASKS = {
  dateBirth: [/\d/, /\d/, '.', /\d/, /\d/, '.', /[12]/, /[09]/, /\d/, /\d/],
  passportID: [/\d/, /\d/, /\d/, /\d/, /\d/, /\d/],
  passportSeries: [/\d/, /\d/, /\d/, /\d/]
}
export { LABEL_PLACEHOLDERS, SIGNUP_SCHEMA, INITIAL_VALUES, MASKS };