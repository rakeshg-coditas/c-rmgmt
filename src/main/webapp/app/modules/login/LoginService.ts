import axios from 'axios';

const LOCALSTORAGE_AUTH_KEY = 'token';
const HEADER_AUTH_KEY = 'token';

const axiosInstance = axios.create({
  baseURL: 'http://localhost:8083/crmgmt',
  headers: {
    'Content-Type': 'application/json'
  }
});
export class LoginService {
  static async signInWithGoogle(payload) {
    const url = 'api/authenticate';
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

  saveToken(response) {
    if (response.data && response.data[HEADER_AUTH_KEY]) {
      localStorage.setItem(LOCALSTORAGE_AUTH_KEY, response.data[HEADER_AUTH_KEY]);
    }
  }
}
