import React, { Component } from 'react';
import { Link } from "react-router-dom";
export default class CategoryDetails extends Component {
	constructor(props) {
		super(props);
		this.state = {
            currentCategory: null,
            
            currentProduct:null,
		};
    }


    setActiveProduct(product, index) {
        this.setState({
          currentProduct: product,
          //currentIndexProduct: index,
        });
      }

	render() {
        
        const{currentCategory}=this.props;
        console.log(currentCategory);
        
		return (
			<div className="col-md-4 align-baseline">
				{currentCategory ? (
					<div>
						<h4> Category </h4>
						<div>
							<label>
								<strong> ID : </strong>
							</label>{' '}
							{currentCategory.id}
						</div>
						<div>
							<label>
								<strong> name :</strong>
							</label>{' '}
							{currentCategory.name}
						</div>
						
						

						<Link to={'/category/details/' + currentCategory.id} className="badge badge-warning">
							Edit
						</Link>
					</div>
				) : (
					<div>
						<br />
						<p> Please click on a Category ...</p>
					</div>
				)}
			</div>
		);
	}
}

