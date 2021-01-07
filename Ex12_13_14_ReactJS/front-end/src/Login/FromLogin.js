import React, { useState, useEffect } from 'react';
import { Redirect } from 'react-router-dom';
import Button from './Button';
import LoginContext from '../Context/LoginContext';

export default function FromLogin() {
	const { onLogin, login,loadUser } = React.useContext(LoginContext);
	const [user, setUser] = useState({
		username: '',
		password: '',
	});

	useEffect(() => loadUser(), [user]);

	const onChange = (e) => {
		setUser({
			...user,
			[e.target.name]: e.target.value,
		});
	};
	

	if (login.isLogin) {
		return <Redirect to={{ pathname: '/admin/categories' }} />;
	}
	return (
		<div className="login">
			<div className="single-box">
				<label>User Name</label>
				<input
					type="text"
					name="username"
					onChange={(e) => onChange(e)}
					value={user.username}
					placeholder="Enter User Name"

				/>
			</div>
			<div className="single-box">
				<label>Password</label>
				<input
					type="password"
					onChange={(e) => onChange(e)}
					name="password"
					value={user.password}
					placeholder="Enter Password"

				/>
			</div>
			<Button onClick={() => onLogin(user.username, user.password)} />
			<div className="text">{login.message}</div>
		</div>
	);
}
