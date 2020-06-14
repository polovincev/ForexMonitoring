import { AUTH_USER, AUTH_USER_RESOLVE, AUTH_USER_REJECT } from "../actions/actionTypes";

const userReduser = (state = {}, action) => {
    switch (action.type) {
        case AUTH_USER:
            return {
                user: {},
                err: null,
                loaded: false,
            }
        case AUTH_USER_RESOLVE:
            return {
                user: action.payload,
                err: null,
                loaded: true,
            }
        case AUTH_USER_REJECT:
            return {
                user: null,
                err: action.err,
                loaded: false,
            }
        default:
            return {
                user: null,
                err: null,
                loaded: false,
            }
    }
}

export default userReduser