import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './Navigation.css'

function Link({ route, placeholder, id, setSelectedItemId, selectedItemId }) {
  let navigate = useNavigate();
  let className = 'nav__item' + (id === selectedItemId ? ' nav__item_selected' : '');
  function handleClick(route) {
    return () => {
      setSelectedItemId(id);
      navigate(route);
    }
  }
  return (
    <li className={className}>
      <button onClick={handleClick(route)}>{placeholder}</button>
    </li>
  )
}
function Navigation({ navigationList }) {
  let [selectedItemId, setSelectedItemId] = useState(0);
  let arrLinks = navigationList.map(({ route, placeholder }, id) => {

    return <Link key={id} id={id} route={route}
      placeholder={placeholder}
      selectedItemId={selectedItemId}
      setSelectedItemId={setSelectedItemId}
    />
  })
  return (
    <nav className='nav'>
      <div className='avatar'></div>
      <ul className='nav__list'>
        {arrLinks}
      </ul>
    </nav>
  )
}

export { Navigation };