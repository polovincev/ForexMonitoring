import { combineReducers } from 'redux';

import userReduser from './userReduser'

export default combineReducers({
    user: userReduser,
})