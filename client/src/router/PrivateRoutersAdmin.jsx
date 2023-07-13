import React from "react";
import { Navigate, Outlet, Redirect, Route } from "react-router-dom";
import jwtDecode from "jwt-decode";

const PrivateRoutersAdmin = () => {
  var token =  localStorage.getItem('token')
  var role = ""
  var checkTime = true
  
    if(token != undefined){
      var tokenUser =  jwtDecode(token)
      role = tokenUser.roles
      console.log(tokenUser);
      if (Date.now() > tokenUser.exp * 10000) {
        checkTime = false
      }
    }
  return role === "ADMIN" && checkTime == true ?  <Outlet /> : <Navigate to="/login" />;
}

export default PrivateRoutersAdmin;