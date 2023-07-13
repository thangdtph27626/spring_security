import axios from "axios";
import { loginSuccess } from "../redux/slice/AuthSlice";
import jwtDecode from "jwt-decode";

var api = process.env.REACT_APP_API_KEY;
var apiAuth = api + `/auth`;

const signIn = (dispatch, navigate, request) => {
  try {
    axios.post(apiAuth + "/sign-in", request).then((response) => {
      dispatch(loginSuccess(response.data));
      localStorage.setItem("user", JSON.stringify(response.data));
      localStorage.setItem("token", JSON.stringify(response.data.token));
      const decodedToken = jwtDecode(response.data.token);
      if(decodedToken.roles === "USER"){
        navigate("/home")
      }else{
        navigate("/dashboard")
      }
    });
  } catch {}
  return;
};

const signOauth2 = (dispatch, navigate, token) => {
  try {
    axios.post(apiAuth + "/oauth2/" + token).then((response) => {
      dispatch(loginSuccess(response.data));
      localStorage.setItem("user", JSON.stringify(response.data));
      localStorage.setItem("token", JSON.stringify(response.data.token));
      const decodedToken = jwtDecode(response.data.token);
      if(decodedToken.roles === "USER"){
        navigate("/home")
      }else{
        navigate("/dashboard")
      }
    });
  } catch {}
  return;
};

const AuthService = {
  signIn,
  signOauth2,
};

export default AuthService;
