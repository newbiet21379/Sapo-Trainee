import React, { Component } from 'react';
import CategoryDataService from '../Service/category.service';
import moment from 'moment';
export default class AddCategory extends Component {
	constructor(props) {
		super(props);

		this.newCategory = this.newCategory.bind(this);
		this.handleChange = this.handleChange.bind(this);
		this.saveCategory = this.saveCategory.bind(this);
		this.newCategory = this.newCategory.bind(this);
		this.state = {
			id: null,
			name: '',
			create_date: moment().toDate(),
			fix_date: moment().toDate(),
			description: '',
			submitted: false,
		};
	}
	handleChange(e) {
		this.setState({
			[e.target.name]: e.target.value,
		});
	}

	saveCategory() {
		let data = {
			name: this.state.name,
			create_date: this.state.create_date,
			fix_date: this.state.fix_date,
			description: this.state.description,
		};
		debugger
		CategoryDataService.create(data)
			.then((response) => {
				if (response && response.data) {
					this.setState({
						id: response.data.id,
						name: response.data.name,
						create_date: response.data.create_date,
						fix_date: response.data.fix_date,
						description: response.data.description,
						submitted: true,
					});
				}
				console.log(response.data);
			})
			.catch((e) => {
				console.log(e);
			});
	}
	newCategory() {
		this.setState({
			id: null,
			name: '',
			create_date: '',
			fix_date: '',
			description: '',
			submitted: false,
		});
	}

	render() {
		return (
			<div className="submit-form">
				{this.state.submitted ? (
					<div>
						<h4> You submitted successfully! </h4>
						<button className="btn btn-success" onClick={this.newCategory}>
							Add
						</button>
					</div>
				) : (
					<div>
						<div className="form-group">
							<label htmlFor="name"> Name </label>
							<input
								name="name"
								type="text"
								className="form-control"
								id="name"
								required
								value={this.state.name}
								onChange={this.handleChange}
							/>
						</div>
						<div className="form-group">
							<label htmlFor="create_date"> Create Date </label>
							<input
								name="create_date"
								type="date"
								value={moment(this.state.create_date).format('YYYY-MM-DD')}
								className="form-control"
								onChange={this.handleChange}
								disabled
							/>
						</div>
						<div className="form-group">
							<label htmlFor="fix_date"> Fix Date </label>
							<input
								name="fix_date"
								type="date"
								value={moment(this.state.fix_date).format('YYYY-MM-DD')}
								className="form-control"
								onChange={this.handleChange}
								disabled
							/>
						</div>
						<div className="form-group">
							<label htmlFor="description"> Description </label>
							<input
								type="text"
								className="form-control"
								id="description"
								required
								value={this.state.description}
								onChange={this.handleChange}
								name="description"
							/>
						</div>

						<button onClick={this.saveCategory} className="btn btn-success">
							Submit
						</button>
					</div>
				)}
			</div>
		);
	}
}
