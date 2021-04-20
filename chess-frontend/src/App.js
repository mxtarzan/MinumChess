import React from 'react'
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle';
import { Route, Switch, Redirect } from 'react-router-dom';

import Navbar    from './components/Navbar/navbar';
import Board from './components/Board/board'

import './App.css';
import Lobby from './components/Lobby/lobby';

export default class App extends React.Component {
  constructor(){
    super();
    this.state = {
      loggedIn : false,
      username : "",
      userId : 0
    }
    this.getLoggedIn = this.getLoggedIn.bind(this);
    this.setLoggedIn = this.setLoggedIn.bind(this);
    this.getUsername = this.getUsername.bind(this);
    this.setUsername = this.setUsername.bind(this);
    this.getUserId = this.getUserId.bind(this);
    this.setUserId = this.setUserId.bind(this);
  }
  
  getLoggedIn(){
    return this.state.loggedIn;
  } 
  setLoggedIn(boolVal){
    this.setState({loggedIn : boolVal});
  }
  getUsername(){
    return this.state.username;
  }
  setUsername(name){
    this.setState({username: name})
  }
  getUserId(){
    return this.state.userId;
  }
  setUserId(id){
    this.setState({userId : id});
  }
 
  render() {
    return (<React.Fragment>
      <Navbar setLoggedIn={this.setLoggedIn} setUserId={this.setUserId} getUserId={this.getUserId} setUsername={this.setUsername}/>
      <Switch>
        <Route path="/chess" render={props => <Board {...props} getUserId={this.getUserId} /> } />
        <Route path="/lobby" render={props => <Lobby {...props} getUserId={this.getUserId}/>}/>
        <Redirect from="/" to="/home" />
      </Switch>
    </React.Fragment >
    );
  }
}
