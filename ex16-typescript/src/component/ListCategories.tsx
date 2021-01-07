import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import Pagination from '@material-ui/lab/Pagination';
import Delete from './DeleteComponent';
import CategoryService from '../service/Category.service';
import { ICategory, IParams } from '../interface/Interface';

export default function ListCategories(props: any): JSX.Element {
	const [categories, setCategories] = useState<Array<ICategory>>([]);
	const [currentCategory, setCurrentCategory] = useState<ICategory | null>(null);
	const [currentIndex, setCurrentIndex] = useState<number>(-1);
	const [page, setPage] = useState<number>(0);
	const [count, setCount] = useState<number>(0);
	const [pageSize, setPageSize] = useState<number>(3);
	const [query, setQuery] = useState<string>('');
	const pageSizes = [3, 6, 9];
	useEffect(() => {
		function getCategories() {
			const params = getRequestParams(page, query, pageSize);
			CategoryService.getAllPaging(params)
				.then((response: any) => {
					console.log(response);
					const { categoryList, totalPages } = response.data;
					console.log(categoryList);
					if (response && response.data && categoryList !== []) {
						setCategories(categoryList);
						setCount(totalPages);
					} else {
						setCategories([]);
						setCount(1);
					}
					console.log(JSON.stringify(categories))
				})
				.catch((e: any) => {
					console.log(e);
				});
		}
		getCategories();
	}, [page, pageSize, query, currentCategory]);

	const getRequestParams = (page: number, query: string, pageSize: number): IParams => {
		let params = { query: '', page: 0, pageSize: 0 };
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

	const setActiveCategory = (category: ICategory | null, index: number) => {
		setCurrentCategory(category);
		setCurrentIndex(index);
	};

	const handlePageChange = (event: any, value: number) => {
		setPage(value);
	};
	const handlePageSizeChange = (event: any) => {
		setPageSize(event.target.value);
		setPage(1);
		setActiveCategory(null, -1);
	};
	const onChangeQuery = (e: any) => {
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
							<button className="btn btn-outline-secondary" type="button" onClick={() => onQuery()}>
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
						<select onChange={handlePageSizeChange} value={pageSize}>
							{pageSizes.map((size) => (
								<option key={size} value={size}>
									{size}
								</option>
							))}
						</select>
						<Pagination
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
							{categories.map((category, index) => (
								<tr
									className={index === currentIndex ? 'active' : ''}
									onClick={() => setActiveCategory(category, index)}
									key={index}
								>
									<th scope="row">{category.id}</th>
									<td>{category.name}</td>
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
