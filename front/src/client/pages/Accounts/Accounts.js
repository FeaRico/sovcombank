import React from 'react';
import { LinearGraphic } from '../../LinearGraphic/LinearGraphic';
import './Accounts.css';
import { connect } from 'react-redux';
import { FormOrder } from '../../FormOrder/FormOrder';

function Accounts() {
  return (
    <div className='accounts'>
      <LinearGraphic />
      <FormOrder />

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
export default connect(mapStateToProps)(Accounts);