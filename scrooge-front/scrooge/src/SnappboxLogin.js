import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './SnappboxLogin.css';
import Api from './Api';

const Login = () => {
    const [phoneNumber, setPhoneNumber] = useState('');
    const [errorMessage, setErrorMessage] = useState('');
    const history = useNavigate();

    const handleLogin = async () => {
        try {
            const response = await Api.post('/box/otp', { phoneNumber });
            if (response.status === 200)
                history('/verify-otp', {state: {phoneNumber: phoneNumber}});
        } catch (error) {
            setErrorMessage(error.response.data.message || 'An error occurred');
        }
    };

    return (
        <div className="login-container">
            <h2>Login to Snapp! Box</h2>
            <input
                type="tel"
                placeholder="Enter your phone number"
                value={phoneNumber}
                onChange={(e) => setPhoneNumber(e.target.value)}
            />
            <button onClick={handleLogin}>Send OTP</button>
            {errorMessage && <p>{errorMessage}</p>}
        </div>
    );

};

export default Login;
