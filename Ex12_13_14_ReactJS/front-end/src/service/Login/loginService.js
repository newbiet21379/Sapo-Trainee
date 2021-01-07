import axios from "axios";

const API_URL = "http://localhost:8080/auth/";

 class LoginService {
    login(username, password) {
        return axios
            .post(API_URL + "signin", {
                username:username,
                password:password
            })
            .then((response) => {
                console.log(response)
                localStorage.setItem("users",JSON.stringify(response.data))
                
                // console.log(response.data.user);
                
                // console.log( users.user)
                return response.data;
            });
    }

}
export default new LoginService();