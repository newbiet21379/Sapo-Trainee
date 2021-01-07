import React, { Component } from 'react';
import CategoryDataService from '../Service/category.service';
import moment from 'moment';
import { Link, Redirect } from 'react-router-dom';

class Category extends Component {
	constructor(props) {
		super(props);

		this.handleChange = this.handleChange.bind(this);
		this.getCategory = this.getCategory.bind(this);
		this.updateCategory = this.updateCategory.bind(this);
		this.deleteCategory = this.deleteCategory.bind(this);

		this.state = {
			currentCategory: {
				id: null,
				name: '',
				create_date: moment().toDate(),
				fix_date: moment().toDate(),
				description: '',
				updated:false,
			},
			message: '',
		};
	}

	componentDidMount() {
		this.getCategory(this.props.match.params.id);
	}

	handleChange(e) {
		this.setState({
			currentCategory: {
				...this.state.currentCategory,
				[e.target.name]: e.target.value,
			},
		});
	}

	getCategory(id) {
		CategoryDataService.get(id)
			.then((response) => {
				if (response && response.data)
					this.setState({
						currentCategory:response.data
						// {
						// 	...this.state.currentCategory,
						// 	name:response.data.name,
						// 	description:response.data.description,
						// } ,
					});
				console.log(response.data);
			})
			.catch((e) => {
				this.setState({
					...this.state,
					message: e.message,
				});
				console.log(e);
			});
	}

	 updateCategory() {
		
		CategoryDataService.update(this.state.currentCategory.id, this.state.currentCategory)
			.then((response) => {
				// console.log(response.data);
				 if(response && response.data){
					this.setState({
						message: 'The category was updated successfully!',
						update:true,
					});
				 }
				else{
					this.setState({
						message: 'The category was updated failed!',
						update:false,
					});
				}
				
			})
			.catch((e) => {
				console.log(e);
			});
		if(this.state.update){
			<Redirect to="/" from={this.props.history}/>
		}
	}

	deleteCategory() {
		CategoryDataService.delete(this.state.currentCategory.id)
			.then((response) => {
				console.log(response.data);
				this.props.history.push('/');
			})
			.catch((e) => {
				console.log(e);
			});
	}

	render() {
		const { currentCategory } = this.state;
		// let { from } = this.props.location.state || { from: { pathname: '/'} };
		// let redirectToReferrer = this.state.update;
		// if (redirectToReferrer) return <Redirect to={from} />;
		return (
			<div>
				{currentCategory ? (
					<div className="edit-form">
						<h4>Category</h4>
						<form>
							<div className="form-group">
								<label htmlFor="name">Title</label>
								<input
									name="name"
									type="text"
									className="form-control"
									id="title"
									value={currentCategory.name}
									onChange={this.handleChange}
								/>
							</div>
							<div className="form-group">
								<label htmlFor="create_date"> Create Date </label>
								<input
									name="create_date"
									type="date"
									value={moment(currentCategory.create_date).format('YYYY-MM-DD')}
									className="form-control"
									onChange={this.handleChange}
									//disabled
								/>
							</div>
							<div className="form-group">
								<label htmlFor="fix_date"> Fix Date </label>
								<input
									name="fix_date"
									type="date"
									value={moment(currentCategory.fix_date).format('YYYY-MM-DD')}
									className="form-control"
									onChange={this.handleChange}
									//disabled
								/>
							</div>
							<div className="form-group">
								<label htmlFor="description">Description</label>
								<input
									name="description"
									type="text"
									className="form-control"
									id="description"
									value={currentCategory.description}
									onChange={this.handleChange}
								/>
							</div>
							<div className="form-group">
								<label>
									<strong> Product in Category {currentCategory.name}:</strong>
								</label>{' '}
								<ul className="list-group">
									{currentCategory.productList &&
										currentCategory.productList.map((product, index) => (
											<li className={'list-group-item '} key={index}>
												{product.name}
											</li>
										))}
								</ul>
							</div>
							<div className="col-12 justify-content-start mb-3">
								<Link
									to={'/product/' + currentCategory.id}
									//params={{data:{currentCategory}}}
									className="badge badge-warning"
								>
									View Products in current Category
								</Link>
							</div>
							<div className="d-flex justify-content-center">
								<button className="badge badge-danger mr-2 mr-5" onClick={this.deleteCategory}>
									Delete
								</button>

								<button
									type="submit"
									className="badge badge-success mr-5 ml-5"
									onClick={this.updateCategory}
								>
									Update
								</button>
							</div>

							<p>{this.state.message}</p>
						</form>
					</div>
				) : (
					<div>
						<br />
						<p>Please click on a Category...</p>
					</div>
				)}
			</div>
		);
	}
}
export default Category;

// <button
//               className="badge badge-danger mr-2 mr-5"
//               onClick={this.sendCategory}
//             >
//               View Products
//             </button>
