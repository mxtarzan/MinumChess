import React, { Component } from 'react'
import { Link } from 'react-router-dom';
import GoogleBtn from '../GoogleBtn/googleBtn';
import Sidebar from './Sidebar/sidebar';
import './styles/navbar.css';

export default class Navbar extends Component {
    constructor(props) {
        super(props);
        this.state = {
            username: "",
        };
        this.setUsername = this.setUsername.bind(this);
    }
    setUsername(name){
        this.setState({username : name});
        this.props.setUsername(name);
    }

    render() {
        let name = (this.state.username !== "") ? <span className="username">Welcome {this.state.username} </span> : <></>;
        return (
            <div>
                <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
                    <Sidebar getUserId={this.props.getUserId()}/>
                    <div className="collapse navbar-collapse" id="navbarText">
                        <ul className="navbar-nav mr-auto">
                            <li className="nav-item active">
                                <Link className="brandname nav-link" id="BrandName" to="/chess"> Minum Chess </Link>
                            </li>
                        </ul>
                        {name}
                        <span className="navbar-text">
                            <GoogleBtn setLoggedIn={this.props.setLoggedIn} setUserId={this.props.setUserId} setUsername={this.setUsername}/>
                        </span>
                    </div>
                </nav>
            </div>
        )
    }
}