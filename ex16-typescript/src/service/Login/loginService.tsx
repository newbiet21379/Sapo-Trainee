import axios from "axios";

const API_URL = "http://localhost:8080/auth/";

 class LoginService {
    login(username:string, password:string) {
        return axios
            .post(API_URL + "signin", {
                username:username,
                password:password
            })
            .then((response:any) => {
                console.log(response)
                localStorage.setItem("users",JSON.stringify(response.data))

                return response.data;
            });
    }

}
export default new LoginService();