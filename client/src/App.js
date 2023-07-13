import logo from "./logo.svg";
import "./App.css";
import Login from "./componets/login/Login";
import AuthContext, { AuthProvider } from "./componets/context/AuthContext";
import { Navigate, Route, Router, Routes } from "react-router-dom";
import { privateRoutersAdmin, privateRoutersUser, publicRouters } from "./router/router";
import PrivateRoutersAdmin from "./router/PrivateRoutersAdmin";
import PrivateRoutersUser from "./router/PrivateRoutersUser";

function App() {
  return (
    <div className="App">
      <AuthProvider>
        <Routes>
          {publicRouters.map((route, index) => {
            const Page = route.element;
            return (
              <Route
                exact
                key={index}
                path={route.path}
                element={<Page />}
              ></Route>
            );
          })}
          <Route  path="/admin" element={<PrivateRoutersAdmin />}>
            {privateRoutersAdmin.map((route, index) => {
              const Page = route.element;
              return (
                <Route
                  path={route.path}
                  element={Page}
                ></Route>
              );
            })}
          </Route>
          <Route  path="/user" element={<PrivateRoutersUser />}>
            {privateRoutersUser.map((route, index) => {
              const Page = route.element;
              return (
                <Route
                  path={route.path}
                  element={Page}
                ></Route>
              );
            })}
          </Route>
          <Route exact path="/" element={<Login />} />
        </Routes>
      </AuthProvider>
    </div>
  );
}

const PrivateRoute = ({ component: Component, ...rest }) => {
  const { getRoleUser } = AuthContext();
  return (
    <Route
      {...rest}
      render={(props) => {
        return getRoleUser === "ADMIN" ? (
          <Component {...props} />
        ) : (
          <Navigate to="/login" />
        );
      }}
    />
  );
};

export default App;
