import React, { useState } from 'react'
import { GoogleLogin, GoogleOAuthProvider } from '@react-oauth/google';
import axios from 'axios';
import './login.css'
import { useDispatch } from 'react-redux';
import AuthService from '../../server/AuthServer';
import { useNavigate } from 'react-router-dom';

function Login() {

    const dispatch = useDispatch();
    const navigate = useNavigate() 

    const handleLogin = (credentialResponse) => {
        var token = credentialResponse.credential;
        AuthService.signOauth2(dispatch,navigate, token)
    }

    const [loginForm, setLoginForm] = useState({
        email: '',
        password: ''
    })

    const onChangeLoginForm = event => {
        setLoginForm({ ...loginForm, [event.target.name]: event.target.value })
        console.log(loginForm);
    }

    const signIn = () => {
        AuthService.signIn(dispatch,navigate, loginForm)
    }

    return (
        <div className='body'>
            <div className="container">
                <div className="row">
                    <div className="col-sm-9 col-md-7 col-lg-5 mx-auto">
                        <div className="card border-0 shadow rounded-3 my-5">
                            <div className="card-body p-4 p-sm-5">
                                <h5 className="card-title text-center mb-5 fw-light fs-5">Sign In</h5>
                                <form>
                                    <div className="form-floating mb-3">
                                        <input type="email" onChange={onChangeLoginForm} value={loginForm.email} name="email" className="form-control" id="floatingInput" placeholder="name@example.com" />
                                        <label for="floatingInput">Email address</label>
                                    </div>
                                    <div className="form-floating mb-3">
                                        <input type="password" className="form-control" onChange={onChangeLoginForm} value={loginForm.password} name="password" id="floatingPassword" placeholder="Password" />
                                        <label for="floatingPassword">Password</label>
                                    </div>

                                    <div className="form-check mb-3">
                                        <input className="form-check-input" type="checkbox" value="" id="rememberPasswordCheck" />
                                        <label className="form-check-label" for="rememberPasswordCheck">
                                            Remember password
                                        </label>
                                    </div>
                                    <div class="d-grid">
                                        <button onClick={signIn} className="btn btn-primary btn-login text-uppercase fw-bold" type="button">
                                            Sign  in
                                        </button>
                                    </div>
                                    <hr className="my-4" />
                                    <div className="d-grid mb-2">
                                        <button className="btn btn-google btn-login text-uppercase fw-bold" type="submit">
                                            <GoogleOAuthProvider clientId='540049648936-3igacb6p6ug52hg9kmf4gmr0576e2d88.apps.googleusercontent.com'>
                                                <GoogleLogin
                                                    onSuccess={handleLogin}
                                                />
                                            </GoogleOAuthProvider>
                                        </button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Login