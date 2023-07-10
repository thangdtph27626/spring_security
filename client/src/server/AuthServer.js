import axios from "axios";
import { loginSuccess } from "../redux/slice/AuthSlice";

var api = process.env.REACT_APP_API_KEY; 
var apiAuth = api + `/auth`;

const signIn = (dispatch, request) => {
  try {
    axios.post(apiAuth + "/sign-in",request )
      .then(response => {
          dispatch(loginSuccess(response.data));
          localStorage.setItem("user", JSON.stringify(response.data));
      })
  } catch {
  }
  return
};

const signOauth2 = (dispatch, token) => {
  try {
    axios.post(apiAuth + "/oauth2/" + token )
      .then(response => {
          dispatch(loginSuccess(response.data));
          localStorage.setItem("user", JSON.stringify(response.data));
      })
  } catch {
  }
  return
};


const AuthService = {
    signIn,
    signOauth2
};

export default AuthService;