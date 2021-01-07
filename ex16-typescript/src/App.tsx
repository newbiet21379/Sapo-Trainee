import React, { Suspense, lazy } from 'react';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';

// const ListCategories = lazy(() => import ('./Component/ListCategories'));
// const Category = lazy(()=> import ('./Component/Category.Component'));
// const AddCategory = lazy(()=> import('./Component/AddCategori'));

import Loading from './Loading';
import Errors from './Errors';

const Home = lazy(() => import('./home'));
const PageLogin = lazy(() => import('./login/PageLogin'));
const PageAdmin = lazy(() => import('./component/PageAdmin'));
class App extends React.Component {
	render() {
		return (
			<Router>
				<Suspense fallback={<Loading />}>
					<Switch>
						<Route exact path="/" component={Home} />
						<Route exact path="/login" component={PageLogin} />
						<Route path="/admin/*" component={PageAdmin} />

						<Route component={Errors} path="*" />
					</Switch>
				</Suspense>
			</Router>
		);
	}
}
export default App;
