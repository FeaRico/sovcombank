import React from 'react';

function CustomSelect({ list, handleChange, value }) {
  console.log('CUSTOM_SELECT', list[0])
  return (
    <select value={value} onChange={handleChange}>
      {list.map((elem, index) => {
        return <option key={`optiont${index}`} value={elem}>{elem}</option>
      })}
    </select>
  )
}

export { CustomSelect };