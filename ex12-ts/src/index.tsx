import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import * as serviceWorker from './serviceWorker';
import { StoreProvider } from './Store';
import HomePage from './HomePage';
import FavPage from './FavPage';
import { Router, RouteComponentProps } from '@reach/router';
const RoutePage=(props:{pageComponent:JSX.Element} & RouteComponentProps)=>props.pageComponent
ReactDOM.render(
	<StoreProvider>
		<Router>
			<App path="/" >
				<RoutePage pageComponent={<HomePage/>} path='/' />
				<RoutePage pageComponent={<FavPage/>} path='/faves' />
			</App>
		</Router>
	</StoreProvider>,
	document.getElementById('root')
);

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
