import jwtDecode from "jwt-decode";
import React from "react";
import { Navigate, Outlet, Redirect, Route } from "react-router-dom";

const PrivateRoutersUser = () => {
  var token =  localStorage.getItem('token')
  var role = ""
  var checkTime = true
  
    if(token != undefined){
      var tokenUser =  jwtDecode(token)
      role = tokenUser.roles
      console.log(tokenUser);
      if (Date.now() > tokenUser.exp * 1000) {
        checkTime = false
      }
    }
  return role === "USER" && checkTime == true ?  <Outlet /> : <Navigate to="/login" />;
}

export default PrivateRoutersUser;