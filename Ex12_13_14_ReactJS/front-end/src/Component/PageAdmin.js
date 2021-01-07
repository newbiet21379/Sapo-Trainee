import React, { useEffect, useRef } from 'react';
import ListCategories from './ListCategories';
import EditCategory from './Category.Component';
import AddCategory from './AddCategory';
import { BrowserRouter as Router, Switch, Link, Route } from 'react-router-dom';
import Error from '../Errors';
import PageLogin from '../Login/PageLogin';
import LoginContext from '../Context/LoginContext';

export default function PageAdmin() {
	const { login, onLogOut, loadUser } = React.useContext(LoginContext);
	const isMountedRef = useRef(null);
	useEffect(() => {
		isMountedRef.current = true;
		loadUser();
		return () => (isMountedRef.current = false);
	}, []);
	console.log(JSON.stringify(login));
	if (login.user) {
		return (
			<Router>
				<div className="box-header bg-primary text-monospace">
					<div className="logo ">Hello {login.user.username} </div>
					<Link to="/login">
						<button className="loguot" onClick={onLogOut}>
							Logout
						</button>
					</Link>

					<Switch>
						<Route exact path="/login" component={PageLogin} />
						<Route exact path="/admin/categories" component={ListCategories} />
						<Route exact path="/admin/category/:id" component={EditCategory} />
						<Route exact path="/admin/addCategory" component={AddCategory} />
						<Route component={Error} />
					</Switch>
				</div>
			</Router>
		);
	} else {
		return (
			<Router>
				<Switch>
					<Route exact path="/login" component={PageLogin} />
					<Route exact path="/admin/categories" component={ListCategories} />
					<Route exact path="/admin/category/:id" component={EditCategory} />
					<Route exact path="/admin/addCategory" component={AddCategory} />
					<Route component={Error} />
				</Switch>
			</Router>
		);
	}
}
