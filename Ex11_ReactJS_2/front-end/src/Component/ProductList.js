import React, { Component } from "react";
import ProductDataService from "../Service/product.service";
import Pagination from "@material-ui/lab/Pagination";
import CategoryDataService from "../Service/category.service";
//import { useLocation } from "react-router";

export default class ProductList extends Component {
  constructor(props) {
    super(props);
    
    this.retrieveProductPaging = this.retrieveProductPaging.bind(this);
    this.refreshList = this.refreshList.bind(this);
    this.setActiveProduct = this.setActiveProduct.bind(this);
    this.handlePageChange = this.handlePageChange.bind(this);
    this.handlePageSizeChange = this.handlePageSizeChange.bind(this);
    this.state = {
      products: [],
      currentProduct: null,
      currentCategory: {
        id: null,
        name: "",
        create_date: "",
        fix_date: "",
        description: "",
      },
      currentIndexProduct: -1,
      page: 1,
      count: 0,
      pageSize: 3,
    };
    this.pageSizes = [3, 6, 9];
  }

  componentDidMount() {
    
    this.getCategory(this.props.match.params.id);
    this.retrieveProductPaging(this.props.match.params.id);
    this.setState({
      category_id:this.props.match.params.id
    })
    
  }

  getCategory(id) {
    CategoryDataService.get(id)
      .then((response) => {
        this.setState({
          currentCategory: response.data,
        });
        console.log(response.data);
      })
      .catch((e) => {
        console.log(e);
      });
  }

  handlePageChange(event, value) {
    this.setState(
      {
        page: value,
      },
      () => {
        this.retrieveProductPaging(
          //this.props.match.params.category_id
          this.state.currentCategory.id
        );
      }
    );
  }

  handlePageSizeChange(event) {
    this.setState(
      {
        pageSize: event.target.value,
        page: 1,
      },
      () => {
        this.retrieveProductPaging(
          this.state.currentCategory.id
          //this.props.match.params.category_id
        );
      }
    );
  }

  getRequestParams(page, pageSize) {
    let params = {};

    if (page) {
      params["page"] = page - 1;
    }

    if (pageSize) {
      params["pageSize"] = pageSize;
    }
    console.log(params);
    return params;
  }

  retrieveProductPaging(id) {
    const { page, pageSize } = this.state;
    const params = this.getRequestParams(page, pageSize);
    console.log("product" + this.state.id);
    ProductDataService.getAllPaging(params, id)
      .then((response) => {
        const { product, totalPages } = response.data;

        this.setState({
          products: product,
          count: totalPages,
        });
        console.log("retrieve Product Paging" + JSON.stringify(response.data));
      })
      .catch((e) => {
        console.log(e);
      });
  }

  refreshList() {
    this.retrieveProductPaging(
      this.state.currentCategory.id
      //this.props.match.params.category_id
    );
    this.setState({
      currentProduct: null,
      currentIndex: -1,
    });
  }

  setActiveProduct(Product, index) {
    this.setState({
      currentProduct: Product,
      currentIndexProduct: index,
    });
  }

  render() {
    const {
      products,
      currentCategory,
      currentProduct,
      currentIndexProduct,
      page,
      count,
      pageSize,
    } = this.state;

    return (
      <div className="list row">
        <div className="col-md-6">
          <h4> Product List of Category name : {currentCategory.name}</h4>

          <div className="mt-3">
            {"Items per Page: "}
            <select onChange={this.handlePageSizeChange} value={pageSize}>
              {this.pageSizes.map((size) => (
                <option key={size} value={size}>
                  {size}
                </option>
              ))}
            </select>

            <Pagination
              className="my-3"
              count={count}
              page={page}
              siblingCount={1}
              boundaryCount={1}
              variant="outlined"
              shape="rounded"
              onChange={this.handlePageChange}
            />
          </div>

          <ul className="list-group">
            {products &&
              products.map((product, index) => (
                <li
                  className={
                    "list-group-item " +
                    (index === currentIndexProduct ? "active" : "")
                  }
                  onClick={() => this.setActiveProduct(product, index)}
                  key={index}
                >
                  {product.name}
                </li>
              ))}
          </ul>
        </div>
        <div className="col-md-6">
          {currentProduct ? (
            <div>
              <h4> Product </h4>
              <div>
                <label>
                  <strong> ID : </strong>
                </label>{" "}
                {currentProduct.id}
              </div>
              <div>
                <label>
                  <strong> name :</strong>
                </label>{" "}
                {currentProduct.name}
              </div>
              <div>
                <label>
                  <strong> Price :</strong>
                </label>{" "}
                {currentProduct.price}
              </div>
              <div>
                <label>
                  <strong> Image :</strong>
                </label>{" "}
                {currentProduct.img_link}
              </div>
              <div>
                <label>
                  <strong> Quantity :</strong>
                </label>{" "}
                {currentProduct.quantity}
              </div>
              <div>
                <label>
                  <strong> Number of Product sale :</strong>
                </label>{" "}
                {currentProduct.sale_number}
              </div>
              <div>
                <label>
                  <strong> Description :</strong>
                </label>{" "}
                {currentProduct.description}
              </div>
              <div>
                <label>
                  <strong> Category of Product :</strong>
                </label>{" "}
                {currentProduct.category_id}
              </div>
            </div>
          ) : (
            <div>
              <br />
              <p> Please click on a Product ...</p>
            </div>
          )}
        </div>
      </div>
    );
  }
}
