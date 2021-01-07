import React, { Component } from "react";
import CategoryDataService from "../Service/category.service";
//import { Link } from "react-router-dom";
import Pagination from "@material-ui/lab/Pagination";
import CategoryDetails from "./CategoryDetail";
import CategoryPaging from "./CategoryPaging";

export default class categoryList extends Component {
  constructor(props) {
    super(props);
    this.onChangeQuery = this.onChangeQuery.bind(this);
    this.retrieveCategoryPaging = this.retrieveCategoryPaging.bind(this);
    this.refreshList = this.refreshList.bind(this);
    this.onQuery = this.onQuery.bind(this);
    this.handlePageChange = this.handlePageChange.bind(this);
    this.handlePageSizeChange = this.handlePageSizeChange.bind(this);
    this.state = {
      categories: [],
      currentCategory: null,
      currentProduct: null,
      currentIndexCategory: -1,
      currentIndexProduct: -1,
      query: "",
      page: 1,
      count: 0,
      pageSize: 3,
      message:'',
    };
    this.pageSizes = [3, 6, 9];
  }

  componentDidMount() {
    this.setState({
      ...this.state,
      firstRun: true,
    });
    this.retrieveCategoryPaging();
  }

  onChangeQuery(e) {
    const query = e.target.value;

    this.setState({
      query: query,
      firstRun: true,
      message:'',
    });
  }

  handlePageChange(event, value) {
    this.setState(
      {
        page: value,
        currentCategory: null,
        currentIndexCategory: -1,
      },
      () => {
        this.retrieveCategoryPaging();
      }
    );
  }

  handlePageSizeChange(event) {
    this.setState(
      {
        pageSize: event.target.value,
        page: 1,
        currentIndexCategory: -1,
      },
      () => {
        this.retrieveCategoryPaging();
      }
    );
  }

  changeCurrentCategory = (currentCategory) => {
    this.setState({
      ...this.state,
         currentCategory: currentCategory,
    });
}

  getRequestParams(query, page, pageSize) {
    let params = {};
  
    if (query) {
      params["query"] = query;
    } else {
      params["query"] = "";
    }
  
    if (page) {
      params["page"] = page - 1;
    }
  
    if (pageSize) {
      params["pageSize"] = pageSize;
    }
    console.log(params);
    return params;
  }

  retrieveCategoryPaging() {
    const { query, page, pageSize } = this.state;
    const params = this.getRequestParams(query, page, pageSize);
    // let data={
    //   page:this.state.page,
    //   pageSize:this.state.pageSize,
    //   query:this.state.query,
    // }
    
    CategoryDataService.getAllPaging(params)
      .then((response) => {
        const { categoryList, totalPages } = response.data;
        if (totalPages === 0) {
          this.setState({
            ...this.state,
            categories: [],
            count: 1,
            message: 'No categories found.',
          });
        } else {
          this.setState({
            ...this.state,
            categories: categoryList,
            count: totalPages,
            message:'',
          });
        }
        console.log("aa" + JSON.stringify(response.data));
      })
      .catch((e) => {
        console.log(e);
      });
  }

  refreshList() {
    this.retrieveCategoryPaging();
    this.setState({
      currentCategory: null,
      currentIndexCategory: -1,
    });
  }

  onQuery() {
    this.setState({
      ...this.state,
      firstRun: false,
      categories: [],
      currentCategory: null,
      count: 0,
      pageSize: 3,
      page: 1,
    });
    this.retrieveCategoryPaging();
  }

  render() {
    const {
      query,
      categories,
      currentCategory,
      page,
      count,
      pageSize,
      message,
    } = this.state;

    return (
      <div className="list row">
        <div className="col-md-8">
          <div className="input-group mb-3">
            <input
              type="text"
              className="form-control"
              placeholder="Search by name"
              value={query}
              onChange={this.onChangeQuery}
            />
            <div className="input-group-append">
              <button
                className="btn btn-outline-secondary"
                type="button"
                onClick={this.onQuery}
              >
                Search
              </button>
            </div>
          </div>
        </div>
        <div className="col-md-8">
          <h4> Category List </h4>

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
              defaultPage={1}
              siblingCount={2}
              boundaryCount={1}
              variant="outlined"
              shape="rounded"
              //showFirstButton
              //showLastButton
              onChange={this.handlePageChange}
            />
          </div>
        <CategoryPaging categories={categories} message={message} changeCurrentCategory={this.changeCurrentCategory}/>
          
        </div>
        <CategoryDetails currentCategory={currentCategory} ></CategoryDetails>
      </div>
    );
  }
}
