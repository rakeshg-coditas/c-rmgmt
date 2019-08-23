import axios from 'axios';

const axiosInstance = axios.create({
  baseURL: 'http://localhost:8080/',
  headers: {
    'Content-Type': 'application/json'
  }
});
export class LoginService {
  static async signInWithGoogle(payload) {
    console.log();
    const url = 'api/' + 'user/login'; // uncomment this if using java as backend
    const response = await this.post(url, payload);
    const user = { ...response.employee, isLoggedIn: true };
  }

  static post(url, body): any {
    return new Promise((resolve, reject) => {
      axiosInstance.interceptors.request.use(config => {
        config.headers[HEADER_AUTH_KEY] = localStorage.getItem(LOCALSTORAGE_AUTH_KEY);
        return config;
      });
      axiosInstance
        .post(url, body)
        .then(response => {
          if (response.data.status === 'invalid') {
            reject(response.data.error);
          }
          saveToken(response);
          resolve(response.data);
          // if (response.data.token) {
          //     resolve(response.data)
          // }
        })
        .catch(error => reject(error));
    });
  }
}
