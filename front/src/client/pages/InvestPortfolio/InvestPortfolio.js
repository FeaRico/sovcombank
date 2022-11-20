import React from 'react';
import { Doughnut } from 'react-chartjs-2';
import Chart from 'chart.js/auto';
import ListBankAccs from '../../ListBankAccs/ListBankAccs';
import './InvestPortfolio.css';
import { FormCreateAcc } from '../../FormCreateAcc/FormCreateAcc';
import { connect } from 'react-redux';

const MOC_OPTIONS = {
  responsive: true,
  plugins: {
    legend: {
      display: true,
      position: 'left',
      font: {
        size: 23,
      },
      labels: {
        boxWidth: 50,
        boxHeight: 34,
        font: {
          size: 18,
        }
      }
    },
    title: {
      display: true,
      text: 'Состав портфеля',
      font: {
        size: 24,
      }
    },
  },
};

const data = {
  labels: ['Pубли', 'Доллары', 'Йен', 'Тенге'],
  datasets: [
    {
      display: false,
      data: [12, 19, 3, 9],
      backgroundColor: [
        'rgba(255, 99, 132)',
        'rgba(54, 162, 235)',
        'rgba(255, 206, 86)',
        'rgba(75, 192, 192)',
        'rgba(153, 102, 255)',
        'rgba(255, 159, 64)',
      ],
      borderColor: [
        'rgba(255, 99, 132, 1)',
        'rgba(54, 162, 235, 1)',
        'rgba(255, 206, 86, 1)',
        'rgba(75, 192, 192, 1)',
        'rgba(153, 102, 255, 1)',
        'rgba(255, 159, 64, 1)',
      ],
      borderWidth: 1,
    },
  ],
};

function InvestPortfolio({ id, jwt, role }) {
  console.log(id, jwt, role)
  return (
    <div className='invest-portfolio'>
      <div className='invest-portfolio__wrapper'>
        <Doughnut className='invest-portfolio__doughnut' options={MOC_OPTIONS} data={data} />
      </div>
      <ListBankAccs />
    </div>
  )
}

function mapStateToProps({ id, jwt, role }) {
  return {
    id,
    jwt,
    role,
  }
}
export default connect(mapStateToProps)(InvestPortfolio);