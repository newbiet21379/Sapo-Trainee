import React, { useState } from 'react';

import LoginService from '../service/Login/loginService';

import LoginContext from './Store';
import {  ILoginProps, ILogin } from '../interface/Interface';

export default function LoginProvider({ children }: any) {
	const [login, setLogin] = useState<ILogin>({
		user: {},
		message: '',
		isLogin: false,
	});
	const getDate = () => {
		var today = new Date();
		var date = today.getFullYear() + '/' + (today.getMonth() + 1) + '/' + today.getDate();
		var time = today.getHours() + ':' + today.getMinutes() + ':' + today.getSeconds();
		var dateTime = date + ' ' + time;
		return dateTime;
	};
	const onLogin = (username: string, password: string) => {
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
		setLogin({ user: {}, message: '', isLogin: false });
		localStorage.removeItem('token');
	};
	const props: ILoginProps = {
		login: login,
		onLogin: onLogin,
		onLogOut: onLogOut,
		getDate: getDate,
	};
	return <LoginContext.Provider value={{props}}>{children}</LoginContext.Provider>;
}
