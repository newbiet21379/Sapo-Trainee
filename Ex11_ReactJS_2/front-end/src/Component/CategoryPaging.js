import React, { Component } from 'react';

export default class CategoryPaging extends Component {
	constructor(props) {
		super(props);
		this.setActiveCategory = this.setActiveCategory.bind(this);
		this.state = {
			currentIndexCategory: null,
			currentCategory: null,
		};
	}

	setActiveCategory(category, index) {
		this.setState({
			currentCategory: category,
			currentIndexCategory: index,
		});
		this.props.changeCurrentCategory(category);
	}

	render() {
		const { categories, message } = this.props;
		return (
			<>
				<ul className="list-group">
					{categories ? (
						categories &&
						categories.map((category, index) => (
							<li
								className={
									'd-flex justify-content-center list-group-item ' +
									(index === this.state.currentIndexCategory ? 'active' : '')
								}
								onClick={() => this.setActiveCategory(category, index)}
								key={index}
							>
								{category.name}
							</li>
						))
					) : (
						<div className="d-flex justify-content-center">
							<h3 className="text-danger ">{message}</h3>
						</div>
					)}
				</ul>
			</>
		);
	}
}
