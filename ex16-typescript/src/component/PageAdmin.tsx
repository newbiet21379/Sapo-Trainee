import React from 'react';
import ListCategories from './ListCategories';
import EditCategory from './Category.Component';
import AddCategory from './AddCategory';
import { BrowserRouter as Router, Switch, Link, Route } from 'react-router-dom';

import PageLogin from '../login/PageLogin';
import LoginContext from '../store/Store';

export default function PageAdmin() {
	
	let {  props } = React.useContext(LoginContext);
	const users=JSON.parse(localStorage.getItem("users")||'{}')
	if (localStorage.getItem("users")) {
		return (
			<div className="box-header bg-primary text-monospace">
				<div className="logo ">Hello {users.user.username}</div>	
				<Link to="/login">
					<button className="logout" onClick={props.onLogOut}>
						Logout
					</button>
				</Link>

				<Switch>
					<Route exact path="/login" component={PageLogin} />
					<Route exact path="/admin/categories" component={ListCategories} />
					<Route exact path="/admin/category/:id" component={EditCategory} />
					<Route exact path="/admin/addCategory" component={AddCategory} />
				</Switch>
			</div>
		);
	} else {
		return (
			<Router>
				<Switch>
					<Route exact path="/login" component={PageLogin} />
					<Route exact path="/admin/categories" component={ListCategories} />
					<Route exact path="/admin/category/:id" component={EditCategory} />
					<Route exact path="/admin/addCategory" component={AddCategory} />
				</Switch>
			</Router>
		);
	}
}
