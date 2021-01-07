import React, { useState } from 'react';

import LoginService from '../service/Login/loginService';

import LoginContext from './LoginContext';

export default function LoginProvider(props) {
	const [login, setLogin] = useState({
		user: null,
		message: '',
		isLogin: false,
	});
	const loadUser = () => {
		if (localStorage.getItem('users'))
			setLogin({ user: JSON.parse(localStorage.getItem('users')).user, message: '', isLogin: true });
	};
	const onLogin = (username, password) => {
		LoginService.login(username, password).then((response) => {
			// console.log('Login Provider: ' + response.accessToken);
			if (!response) {
				username && password
					? setLogin({ ...login, message: 'Wrong username/password', isLogin: false })
					: setLogin({ ...login, message: 'Please input username/password', isLogin: false });
			} else {
				console.log(response);
				setLogin({ user: response, message: '', isLogin: true });
			}
		});
	};

	const onLogOut = () => {
		setLogin({ user: null, message: '', isLogin: false });
		localStorage.removeItem('token');
	};

	return (
		<LoginContext.Provider
			value={{
				login: login,
				onLogin: onLogin,
				onLogOut: onLogOut,
				loadUser: loadUser,
			}}
		>
			{props.children}
		</LoginContext.Provider>
	);
}
