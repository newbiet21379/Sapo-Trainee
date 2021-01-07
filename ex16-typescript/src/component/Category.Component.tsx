import React, { useState, useEffect, useContext } from 'react';
import CategoryService from '../service/Category.service';
import { Link } from 'react-router-dom';
import { ICategory } from '../interface/Interface';
import LoginContext from '../store/Store';

export default function EditCategory(props: any):JSX.Element {
	const [id, setId] = useState<number>(0);
	const [name, setName] = useState('');
	const [description, setDescription] = useState('');
	const [submitted, setSubmitted] = useState(false);

	useEffect(() => {
		function getCategory(id = props.match.params.id) {
			CategoryService.get(id)
				.then((response: any) => {
					setId(response.data.id);
					setName(response.data.name);
					setDescription(response.data.description);
				})
				.catch((e: any) => {
					console.log(e);
				});
		}
		getCategory();
	}, [submitted, props.match.params.id]);
	const {getDate}=useContext(LoginContext)
	const saveCategory = () => {
		var data: ICategory = {
			id: id,
			name: name,
			create_date: getDate(),
			fix_date: getDate(),
			description: description,
		};
		CategoryService.update(id, data)
			.then((response: any) => {
				setSubmitted(true);
			})
			.catch((e: any) => {
				console.log(e);
			});
	};

	const setDefault = () => {
		setSubmitted(false);
	};

	return (
		<div className="submit-form container">
			{submitted ? (
				<div>
					<h4>You submitted successfully!</h4>
					<Link to={'/admin/categories'} onClick={setDefault} className="badge badge-warning">
						Back
					</Link>
				</div>
			) : (
				<div>
					<div className="form-group">
						<label htmlFor="name">name</label>
						<input
							type="text"
							className="form-control"
							id="name"
							required
							value={name}
							onChange={(e) => setName(e.target.value)}
							name="name"
						/>
					</div>
					<div className="form-group">
						<label htmlFor="description">description</label>
						<input
							type="text"
							className="form-control"
							id="description"
							required
							value={description}
							onChange={(e) => setDescription(e.target.value)}
							name="description"
						/>
					</div>
					<div className="d-flex justify-content-between">
						<button onClick={saveCategory} className="btn btn-success ">
							Submit
						</button>
						<Link to="/admin/categories">
							<button className="btn btn-danger ">Back</button>
						</Link>
					</div>
				</div>
			)}
		</div>
	);
}
