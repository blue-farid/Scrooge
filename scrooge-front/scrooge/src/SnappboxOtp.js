import React, {useEffect, useState} from 'react';
import { useNavigate, useLocation} from 'react-router-dom';
import './SnappboxOtp.css';
import Api from "./Api";

const VerifyOTP = () => {
    const [otp, setOtp] = useState('');
    const [phoneNumber, setPhoneNumber] = useState('');
    const [errorMessage, setErrorMessage] = useState('');
    const history = useNavigate();
    const location = useLocation();

    useEffect( () => {
        if (location.state && location.state.phoneNumber)
            setPhoneNumber(location.state.phoneNumber);
    }, [location.state]);
    const handleVerify = async () => {
        try {
            const response = await Api.post('/box/login', { otp, phoneNumber});
            if (response.status === 200)
                history('/snappbox-dashboard', {state: {phoneNumber: phoneNumber}});
        } catch (error) {
            setErrorMessage(error.response.data.message || 'An error occurred');
        }
    };

    return (
        <div className="otp-container">
            <h2>Verify OTP</h2>
            <input
                type="text"
                placeholder="Enter OTP"
                value={otp}
                onChange={(e) => setOtp(e.target.value)}
            />
            <button onClick={handleVerify}>Verify</button>
            {errorMessage && <p>{errorMessage}</p>}
        </div>
    );
};

export default VerifyOTP;
