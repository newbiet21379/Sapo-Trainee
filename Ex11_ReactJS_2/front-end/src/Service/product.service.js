import http from "../http-common";

class ProductDataService {
    getAll() {
        return http.get("/products/all");
    }
    getAllPaging(params,id){
        return http.get(`/products/${id}`,{params})
    }
    get(id) {
        return http.get(`/product/${id}`);
    }

    create(data) {
        return http.post("/product/", data);
    }

    update(id, data) {
        return http.put(`/product/${id}`, data);
    }

    delete(id) {
        return http.delete(`/product/${id}`);
    }

    searchByCategory(params){
        return http.get(`/product/category/`,{params});
    }
}

export default new ProductDataService();