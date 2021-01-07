import http from '../http-common';
import headerService from './Login/headerService';
import { IParams, ICategory } from '../interface/Interface';

class CategoryService {
	get(id:number) {
		return http.get(`/category/${id}`, { headers: headerService() });
	}

	getAllPaging(params:IParams) {
		return http.get('/category', { params, headers: headerService() });
	}

	create(data:ICategory) {
		return http.post('/category', data, { headers: headerService() });
	}

	update(id:number, data:ICategory) {
		return http.put(`/category/${id}`, data, { headers: headerService() });
	}

	delete(id:number) {
		return http.delete(`/category/${id}`, { headers: headerService });
	}
}

export default new CategoryService();
