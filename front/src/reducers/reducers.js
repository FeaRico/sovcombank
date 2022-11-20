let initialState = {
  id: null,
  jwt: null,
  role: null,
}

const reducer = (state = initialState, action) => {
  switch (action.type) {
    case 'AUTHENTICATED':
      return ({
        ...state,
        id: action.payload,
        jwt: action.jwt,
        role: action.role,
      })
    default:
      return state;
  }
}

export { reducer }