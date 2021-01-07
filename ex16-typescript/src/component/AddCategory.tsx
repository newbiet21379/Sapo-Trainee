import React, { useState, useEffect, useContext } from 'react';
import { Link, Redirect } from 'react-router-dom';

import CategoryService from '../service/Category.service';
import { ICategory } from '../interface/Interface';
import LoginContext from '../store/Store';

export default function AddCategory(props: any):JSX.Element {
	const { getDate } = useContext(LoginContext);
	const [category, setCategory] = useState<ICategory>({
		id: 0,
		name: '',
		create_date: getDate(),
		fix_date: getDate(),
		description: '',
	});
	const [submitted, setSubmitted] = useState<boolean>(false);
	const [noteName, setNoteName] = useState<string>('');
	const [noteDescription, setNoteDescription] = useState<string>('');
	const [note, setNote] = useState<string>('');

	const handleOnChange = (event: any) => {
		const { name, value } = event.target;
		setCategory({ ...category, [name]: value });
	};

	useEffect(() => setDefault());

	const setDefault = () => {
		setNoteName('');
		setNoteDescription('');
		setNote('');
		newCategory();
	};

	const check = () => {
		if (category.name === '' && category.description === '') {
			setNote('');
		}
		if (category.name === '') {
			setNoteName('Chưa nhập name');
		}
		if (category.description === '') {
			setNoteDescription('Chưa nhập description');
		}
		if (category.name !== '' && category.description !== '') {
			setNoteName('');
			setNoteDescription('');
			saveCategory();
		}
	};

	const saveCategory = () => {
		var data = {
			id: category.id,
			name: category.name,
			create_date: category.create_date,
			fix_date: category.fix_date,
			description: category.description,
		};
		CategoryService.create(data)
			.then((response) => {
				setCategory({
					id: response.data.id,
					name: response.data.name,
					create_date: response.data.create_date,
					fix_date: response.data.fix_date,
					description: response.data.description,
				});
				setSubmitted(true);
			})
			.catch((e) => {
				console.log(e);
			});
	};

	const newCategory = () => {
		setCategory({
			id: 0,
			name: '',
			create_date: getDate(),
			fix_date: getDate(),
			description: '',
		});
		setSubmitted(false);
	};

	return (
		<div className="submit-form container">
			{submitted ? (
				// <div>
				// 	<h4>You submitted successfully!</h4>
				// 	<button className="btn btn-success" onClick={newCategory}>
				// 		Add
				// 	</button>
				// </div>
				<Redirect to={{ pathname: '/admin/categories' }} />
			) : (
				<div>
					<div className="form-group">
						<label htmlFor="name">name</label>
						<input
							type="text"
							className="form-control"
							id="name"
							required
							// value={this.state.name}
							onChange={handleOnChange}
							name="name"
						/>
						<div className="text">{noteName}</div>
					</div>
					<div className="form-group">
						<label htmlFor="description">description</label>
						<input
							type="text"
							className="form-control"
							id="description"
							required
							// value={this.state.description}
							onChange={handleOnChange}
							name="description"
						/>
						<div className="text">{noteDescription}</div>
					</div>
					<div className="d-flex justify-content-between">
						<button onClick={check} className="btn btn-success">
							Submit
						</button>
						<Link to="/admin/categories">
							<button className="btn btn-danger ">Back</button>
						</Link>
					</div>
					<div className="text">{note}</div>
				</div>
			)}
		</div>
	);
}
