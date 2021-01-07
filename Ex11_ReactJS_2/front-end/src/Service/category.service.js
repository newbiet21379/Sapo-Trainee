import http from '../http-common';

class CategoryDataService {
	getAll() {
		return http.get('/category/all');
	}
	getAllPaging(
		//params
		params
	) {
		return http.get(
			'/category/',
			{ params }
			//data
		);
	}
	get(id) {
		return http.get(`/category/${id}`);
	}
	create(data) {
		debugger;
		return http.post('/category/', data);
	}

	update(id, data) {
		return http.put(`/category/${id}`, data);
	}
	delete(id) {
		return http.delete(`/category/${id}`);
	}
	searchByName(name) {
		return http.get(`/category/name/${name}`);
	}
}

export default new CategoryDataService();
