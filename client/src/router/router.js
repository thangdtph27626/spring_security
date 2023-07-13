import Dashboard from "../componets/dashboard/Dashboard";
import Login from "../componets/login/Login";
import User from "../componets/user/User";

const publicRouters = [
    { path: "/login", element: Login },
  ];
  
  const privateRoutersAdmin = [
    { path: "/admin/users", element: User },
    { path: "/admin/dashboard", element: Dashboard },
  ];

  const privateRoutersUser = [
    { path: "/user/detail-user", element: User },
  ];
  
  export { publicRouters, privateRoutersAdmin, privateRoutersUser };