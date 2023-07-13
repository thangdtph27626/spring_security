import jwtDecode from 'jwt-decode'
import React, { Component, useContext } from 'react'

const AuthContext = React.createContext()

class AuthProvider extends Component {
  state = {
    user: null
  }

  componentDidMount() {
    const user = localStorage.getItem('user')
    this.setState({ user })
  }

  getUser = () => {
    return JSON.parse(localStorage.getItem('user'))
  }

  userIsAuthenticated = () => {
    let user = localStorage.getItem('user')
    if (!user) {
      return false
    }
    user = JSON.parse(user)
    
    // kiem tra token nguoi dung 
    if (Date.now() > user.data.exp * 1000) {
      this.userLogout()
      return false
    }
    return true
  }

  userLogin = user => {
    localStorage.setItem('user', JSON.stringify(user))
    this.setState({ user })
  }

  getRoleUser = () => {
    var user =  localStorage.getItem('user')
    if(user == undefined){
      return ''
    }
    const decodedToken = jwtDecode(user.token);
    return decodedToken.roles 
  }

  userLogout = () => {
    localStorage.removeItem('user')
    this.setState({ user: null })
  }

  render() {
    const { children } = this.props
    const { user } = this.state
    const { getUser, userIsAuthenticated, userLogin, userLogout, getRoleUser } = this

    return (
      <AuthContext.Provider value={{ user, getUser, userIsAuthenticated, userLogin, userLogout, getRoleUser,}}>
        {children}
      </AuthContext.Provider>
    )
  }
}

export default AuthContext

export function useAuth() {
  return useContext(AuthContext)
}

export { AuthProvider }