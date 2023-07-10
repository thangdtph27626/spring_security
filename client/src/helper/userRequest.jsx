import jwtDecode from "jwt-decode";

export const getUserInforInLocalStorage = () => {
  const userIformation = localStorage.getItem("user");
  return userIformation ? JSON.parse(userIformation) : null;
};

export const getIdUser = () => {
  const userIformation = getDetailUser();
  return userIformation?.idUser || null;
};

export const getToken = () => {
  const userIformation = getUserInforInLocalStorage();
  return userIformation?.token || null;
};

export const getDetailUser = () => {
  const token = getToken();
  return token ? jwtDecode(token) : null;
};