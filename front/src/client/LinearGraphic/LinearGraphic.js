import React, { useState } from 'react';
import { CustomSelect } from '../../common/common';
import Chart from 'chart.js/auto';
import { Line } from 'react-chartjs-2';
import './LinearGraphic.css';
import { FormOrder } from '../FormOrder/FormOrder';

const MOC_OPTIONS = {
  responsive: true,
  plugins: {
    legend: {
      display: false,
      position: 'top',
    },
    title: {
      display: false,
      text: 'Chart.js Line Chart',
    },
  },
};
const MOC_LABELS = ['00:00', '06:00', '09:00', '12:00', '15:00', '18:00', '21:00']
const MOC_DATA = {
  labels: MOC_LABELS,
  datasets: [{
    label: 'Input data',
    data: [65, 59, 80, 81, 56, 55, 40],
    fill: false,
    borderColor: 'rgb(75, 192, 192)',
    tension: 0.1
  }]
};
function LinearGraphic({ sortedByDateData }) {
  let [currency, setCurrency] = useState('руб');
  let [period, setPeriod] = useState('неделя');

  function handleChange(funcHandleState) {
    return (event) => {
      funcHandleState(event.target.value)
    }
  }

  return (
    <div className='graphic-wrapper'>
      <Line id='graphic' data={MOC_DATA} options={MOC_OPTIONS} />
      <div className='graphic-wrapper__selects'>
        <CustomSelect
          list={['руб', '$', 'йен', 'тенге', 'юань']}
          value={currency} handleChange={handleChange(setCurrency)}
        />
        <CustomSelect
          list={['мес', 'нед', '6 мес.', '12 мес.']}
          value={period} handleChange={handleChange(setPeriod)}
        />
      </div>
    </div>
  )
}

export { LinearGraphic }