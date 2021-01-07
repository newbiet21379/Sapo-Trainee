import React from 'react';
import { Link } from '@reach/router';

export default function Home(): JSX.Element {
	return (
		<div>
			<h1>Welcome</h1>
			<Link to={'/login'} className="btn btn-danger">
				Login
			</Link>
		</div>
	);
}
