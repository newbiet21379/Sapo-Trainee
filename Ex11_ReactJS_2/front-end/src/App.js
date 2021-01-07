import React, { Component } from "react";
import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";
import categoryList from "./Component/CategoryList";
import AddCategory from "./Component/AddCategory";
import Category from "./Component/UpdateCategory";
import ProductList from "./Component/ProductList";

class App extends Component {
  render() {
    
    return (   
      <Router>
        <div>
          <nav className="navbar navbar-expand navbar-dark bg-dark">
            <Link
              to={"/category/list"}
              //params={{ page: 1, query: "A", pageSize: 3 }}
              className="navbar-brand"
            >
              All
            </Link>
            <div className="navbar-nav mr-auto">
              <li className="nav-item">
                <Link to={"/category/details/1"} className="nav-link">
                  Category
                </Link>
              </li>
              <li className="nav-item">
                <Link to={"/category/add"} className="nav-link">
                  Add
                </Link>
              </li>
            </div>
          </nav>

          <div className="container mt-3">
            <Switch>
              <Route
                exact
                path={["/category/list","/"]}
                component={categoryList}
              />
              <Route exact path="/category/add" component={AddCategory} />
              <Route exact path="/category/details/:id" component={Category} />
              <Route exact path="/product/:id" component={ProductList} />
            </Switch>
          </div>
        </div>
      </Router>
    );
  }
}

export default App;
