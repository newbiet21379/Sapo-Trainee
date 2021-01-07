import React, { Fragment, useContext} from 'react';
import './App.css';
import { Store } from './Store';
import { Link } from '@reach/router';

export default function App(props: any): JSX.Element {
	const { state } = useContext(Store);

	return (
		<Fragment>
			<header className="header">
				<div>
					<h1>Rick and Morty</h1>
					<p>Pick your favourite episodes</p>
				</div>
				<div>
					<Link to="/">Home</Link>
					<Link to="/faves">Favorites(s):{state.favorites.length}</Link>
				</div>
			</header>
			{props.children}
		</Fragment>
	);
}
