import React, { useState, useEffect } from 'react';
import { Redirect } from 'react-router-dom';
import Button from './Button';
import LoginContext from '../store/Store';
import { IUser } from '../interface/Interface';

export default function FromLogin():JSX.Element {
	const [user, setUser] = useState<IUser>({
		username: '',
		password: '',
	});

	useEffect(() => {
		document.title = ` Chào bạn  ${user.username}`;
	}, [user]);

	const onChange = (e:any) => {
		setUser({
			...user,
			[e.target.name]: e.target.value,
		});
	};
	const {props}  = React.useContext(LoginContext);

	if (props.login.isLogin) {
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
			<Button onClick={() => props.onLogin(user.username, user.password)} />
			<div className="text">{props.login.message}</div>
		</div>
	);
}
