import React from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import './ListBankAccs.css';
import { FormCreateAcc } from '../FormCreateAcc/FormCreateAcc';

const MOCKUP_DATA = [
  { idAcc: 123123123, balance: 2134 },
  { idAcc: 123123123, balance: 2134 },
  { idAcc: 123123123, balance: 2134 },
  { idAcc: 123123123, balance: 2134 },
]
function BankAccount({ idAcc, balance }) {
  return (
    <div className='card bank-account'>
      <span className='bank-account__id'>{idAcc}</span>
      <div className='bank-account__wrapper'>
        <div className='bank-account__balance'>
          <span>Баланс</span>
          <span>{balance}</span>
        </div>
        <button className='bank-account__btn'>Реквизиты</button>
      </div>
    </div>
  )
}

function BanklAccountsList({ arrAccs = MOCKUP_DATA }) {
  return (
    <section className='bank-accounts-list'>
      <h2>Мои счета</h2>
      {arrAccs.map(({ idAcc, balance }) => {
        return (<BankAccount idAcc={idAcc} balance={balance} />)
      })}
      <FormCreateAcc />
    </section>
  )
}

function mapStateToProps({ arrAccs }) {
  return ({
    arrAccs: arrAccs,
  })
}
function mapDispatchToProps() {

}
export default connect(mapStateToProps, null)(BanklAccountsList)