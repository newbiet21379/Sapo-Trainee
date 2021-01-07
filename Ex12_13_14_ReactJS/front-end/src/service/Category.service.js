import http from '../http-common';
import HeaderService from '../service/Login/headerService';

class CategoryService {
	get(id) {
		return http.get(`/category/${id}`, { headers: HeaderService() });
	}

	getAllPaging(params) {
		return http.get('/category', { params, headers: HeaderService() });
	}

	create(data) {
		return http.post('/category', data, { headers: HeaderService() });
	}

	update(id, data) {
		return http.put(`/category/${id}`, data, { headers: HeaderService() });
	}

	delete(id) {
		return http.delete(`/category/${id}`, { headers: HeaderService });
	}
}

export default new CategoryService();
