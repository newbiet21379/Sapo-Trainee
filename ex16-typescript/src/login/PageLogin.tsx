import React from 'react';
import Box from './box';
import FromLogin from './FromLogin';

export default function PageLogin():JSX.Element {
	return (
		<div className="img text-monospace">
			<FromLogin/>
			<div className="title">Login Page</div>
			<Box />
		</div>
	);
}
