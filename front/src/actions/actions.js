function setUserData({ id, jwt, role }) {
  return {
    payload: {
      id,
      jwt,
      role,
    },
    type: 'AUTHENTICATED',
  }
}
export { setUserData }