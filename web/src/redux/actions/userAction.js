import { AUTH_USER, AUTH_USER_REJECT, AUTH_USER_RESOLVE } from "./actionTypes";
import { authUserApi } from "../../apis/auth";

export const authUser = (username, password) => {
    return (dispatch, getState) => {
        dispatch(authUserAction())

        authUserApi(username, password).then(res => {
            dispatch(authUserResolveAction(res.data))
        }).catch(err => dispatch(authUserRejectAction(err)))
    }
}

export const authUserAction = () => ({
    type: AUTH_USER,
})

export const authUserResolveAction = (payload) => ({
    type: AUTH_USER_RESOLVE,
    payload,
})

export const authUserRejectAction = (err) => ({
    type: AUTH_USER_REJECT,
    err,
})

