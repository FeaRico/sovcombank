async function authenticate(ip, data, api) {
  try {
    let request = await fetch(ip + 'api/v1/auth/login', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(data)
    })
    console.log(request);
    return {
      ok: request.body.ok,
      jwt: request.body.jwt,
      role: request.body.role,
    }
  } catch (err) {
    console.log(err);
  }
}
export { authenticate };