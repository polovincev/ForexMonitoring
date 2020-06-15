import React, { Component } from 'react';
import Login from './pages/Login';
import Profile from './pages/Profile';
import { Route } from "react-router-dom";

class App extends Component {
  componentDidMount() { }

  render() {
    return (
      <>
        <Route path="/" exact component={Profile} />
        <Route path="/login" component={Login} />
      </>
    )
  }
}

export default App;
