import React from 'react';
import { Link } from "react-router-dom";

export default class Home extends React.Component {
    render() {
        return (
            <div>
                <h1>Wellcome</h1>
                <Link to={"/login"} className="btn btn-danger" > 
                Login
                </Link>
            </div>
        );
    }
}