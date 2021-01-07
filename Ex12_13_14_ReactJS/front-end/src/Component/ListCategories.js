import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import Pagination from '@material-ui/lab/Pagination';
import Delete from './DeleteComponent';
import CategoryService from '../service/Category.service';

export default function ListCategories(props) {
	const [category, setCategory] = useState([]);
	const [currentCategory, setCurrentCategory] = useState(null);
	const [currentIndex, setCurrentIndex] = useState(-1);
	const [page, setPage] = useState(1);
	const [count, setCount] = useState(0);
	const [pageSize, setPageSize] = useState(3);
	const [query, setQuery] = useState('');
	const pageSizes = [3, 6, 9];
	useEffect(() => {
		function getCategories() {
			const params = getRequestParams(page, query, pageSize);
			CategoryService.getAllPaging(params)
				.then((response) => {
					console.log(response);
					const { categoryList, totalPages } = response.data;
					console.log(response.data);
					if (response && response.data) {
						setCategory(categoryList);
						setCount(totalPages);
					} else {
						setCategory([]);
						setCount(0);
					}
				})
				.catch((e) => {
					console.log(e);
				});
		}
		getCategories();
	}, [page, pageSize,query,currentCategory]);

	const getRequestParams = (page, query, pageSize) => {
		let params = {};
		if (query) {
			params['query'] = query;
		} else params['query'] = '';
		if (page) {
			params['page'] = page - 1;
		}
		if (pageSize) {
			params['pageSize'] = pageSize;
		}
		return params;
	};

	const reload = () => {
		setCurrentCategory(null);
	};

	const setActiveCategory = (category, index) => {
		setCurrentCategory(category);
		setCurrentIndex(index);
	};

	const handlePageChange = (event, value) => {
		setPage(value);
	};
	const handlePageSizeChange = (event) => {
		setPageSize(event.target.value);
		setPage(1);
		setActiveCategory(null, -1);
	};
	const onChangeQuery = (e) => {
		const querySearch = e.target.value;
		setQuery(querySearch);
		
	};
	const onQuery = async () => {
		setPage(1);
		setPageSize(3);
		setCurrentCategory(null);
	};
	return (
		<div className="container text-monospace text-justify">
			<div className="row">
				<div className="col-md-8">
					<div className="input-group mb-3">
						<input
							type="text"
							className="form-control"
							placeholder="Search by name"
							value={query}
							onChange={(e) => onChangeQuery(e)}
						/>
						<div className="input-group-append">
							<button className="btn btn-outline-secondary" type="button" onClick={(e) => onQuery(e)}>
								Search
							</button>
						</div>
					</div>
				</div>
			</div>
			<div className="row">
				<div className="col-3">
					<div className="mt-3 text-center">
						{'Items per Page: '}
						<select onChange={(e) => handlePageSizeChange(e)} value={pageSize}>
							{pageSizes.map((size) => (
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
							color="primary"
							shape="rounded"
							onChange={handlePageChange}
						/>
					</div>
					<h4>DS Category</h4>
					<table className="table table-striped">
						<thead>
							<tr className="just-content-center">
								<th scope="">id</th>
								<th scope="">name</th>
							</tr>
						</thead>
						<tbody>
							{category.map((categor, index) => (
								<tr
									className={index === currentIndex ? 'active' : ''}
									onClick={() => setActiveCategory(categor, index)}
									key={index}
								>
									<th scope="row">{categor.id}</th>
									<td>{categor.name}</td>
								</tr>
							))}
						</tbody>
					</table>

					<Link to={'/admin/addCategory'}>
						<button className="btn btn-success ">Add</button>
					</Link>
				</div>
				<div className="col-9">
					{currentCategory ? (
						<div>
							<h4>Info</h4>
							<table className="table table-bordered table-hover">
								<tbody>
									<tr>
										<th className="w-25 p-3">id:</th>
										<td className="w-75 p-3">{currentCategory.id}</td>
									</tr>
									<tr>
										<th scope="col">name:</th>
										<td>{currentCategory.name}</td>
									</tr>
									<tr>
										<th scope="col">create_date:</th>
										<td>{currentCategory.create_date}</td>
									</tr>
									<tr>
										<th scope="col">fix_date:</th>
										<td>
											{currentCategory.fix_date == null ? 'Chưa sửa' : currentCategory.fix_date}
										</td>
									</tr>
									<tr>
										<th scope="col">description:</th>
										<td>{currentCategory.description}</td>
									</tr>
								</tbody>
							</table>
							<div className="d-flex justify-content-between">
								<Link to={'/admin/category/' + currentCategory.id}>
									<button className="btn btn-warning ">Edit</button>
								</Link>
								<Delete
									currentCategory={currentCategory}
									// onClick={onClick}
									reload={reload}
								/>
							</div>
						</div>
					) : (
						<div>
							<br />
							<p>Please click on a Category...</p>
						</div>
					)}
				</div>
			</div>
		</div>
	);
}
