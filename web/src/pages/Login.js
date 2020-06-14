import React, { Component } from 'react'
import { connect } from 'react-redux'
import { bindActionCreators } from 'redux';
import { authUser } from '../redux/actions/userAction';

class Login extends Component {

    componentDidMount() {
        const { authUser } = this.props
        authUser('roman', '12')


    }

    render() {
        const { user } = this.props
        return (
            <div>
                <h1>123456</h1>
                <div>{user.username}</div>
            </div>
        )
    }
}

const mapStateToProps = (state) => {
    console.log(state)
    return {
        user: state.user.user || { username: null },
    }
}

const mapDispatchToProps = (dispatch) => {
    return bindActionCreators({ authUser }, dispatch)
}

export default connect(mapStateToProps, mapDispatchToProps)(Login);
