import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';

import CategoryService from '../service/Category.service';

export default function AddCategory(props) {
	const [category, setCategory] = useState([
		{
			id: null,
			name: '',
			description: '',
		},
	]);
	const [submitted, setSubmitted] = useState(false);
	const [noteName, setNoteName] = useState('');
	const [noteDescription, setNoteDescription] = useState('');
	const [note, setNote] = useState('');

	const handleOnChange = (event) => {
		const { name, value } = event.target;
		setCategory({ ...category, [name]: value });
	};

	useEffect(() => {
		setDefault();
	}, [category]);

	const setDefault = () => {
		setNoteName('');
		setNoteDescription('');
		setNote('');
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
			name: category.name,
			description: category.description,
		};
		CategoryService.create(data)
			.then((response) => {
				setCategory([
					{
						name: response.data.name,
						description: response.data.description,
					},
				]);
				setSubmitted(true);
			})
			.catch((e) => {
				console.log(e);
			});
	};

	const newCategory = () => {
		setCategory([
			{
				name: '',
				description: '',
			},
		]);
		setSubmitted(false);
	};

	return (
		<div className="submit-form container">
			{submitted ? (
				<div>
					<h4>You submitted successfully!</h4>
					<button className="btn btn-success" onClick={newCategory}>
						Add
					</button>
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
