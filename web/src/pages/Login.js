import React, { Component } from 'react'
import s from '../styles/Login.module.scss';
import { connect } from 'react-redux'
import { Redirect } from "react-router-dom";
import { bindActionCreators } from 'redux';
import { authUser } from '../redux/actions/userAction';
import { Layout, Form, Input, Button } from 'antd';


const { Content } = Layout


class Login extends Component {

    onFinish = ({ username, password }) => {
        const { authUser } = this.props

        authUser(username, password)
    }

    render() {
        const layout = {
            labelCol: { span: 6 },
            wrapperCol: { span: 18 },
        };

        const tailLayout = {
            wrapperCol: { offset: 6, span: 18 },
        };

        const { user } = this.props;

        if (user.token) {
            return <Redirect to="/" />;
        }

        return (
            <Layout>
                <Content>
                    <div className={s.root}>
                        <div className={s.form_wrap}>
                            <Form
                                {...layout}
                                name="basic"
                                onFinish={this.onFinish}
                            >
                                <Form.Item
                                    label="Логин"
                                    name="username"
                                    rules={[{ required: true, message: 'Please input your username!' }]}
                                >
                                    <Input />
                                </Form.Item>

                                <Form.Item
                                    label="Пароль"
                                    name="password"
                                    rules={[{ required: true, message: 'Please input your password!' }]}
                                >
                                    <Input.Password />
                                </Form.Item>
                                <Form.Item {...tailLayout}>
                                    <Button type="primary" htmlType="submit">
                                        Войти
                                    </Button>
                                </Form.Item>
                            </Form>
                        </div>
                    </div>
                </Content>
            </Layout>
        )
    }
}

const mapStateToProps = (state) => {
    return {
        user: state.user.user || { token: null }
    }
}

const mapDispatchToProps = (dispatch) => {
    return bindActionCreators({ authUser }, dispatch)
}

export default connect(mapStateToProps, mapDispatchToProps)(Login);
