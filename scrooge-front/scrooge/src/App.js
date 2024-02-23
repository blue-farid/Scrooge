import React from 'react';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import SnappboxLogin from './SnappboxLogin';
import VerifyOTP from './SnappboxOtp';
import SnappBoxDashboard from './SnappboxDashboard';
import ScroogeHome from './ScroogeHome';

const App = () => {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<ScroogeHome/>}>
                </Route>
                <Route path="/verify-otp" element={<VerifyOTP/>}>
                </Route>
                <Route path="/snappbox-dashboard" element={<SnappBoxDashboard/>}>
                </Route>
                <Route path="/snappbox-login" element={<SnappboxLogin/>}>
                </Route>
            </Routes>
        </Router>
    );
};

export default App;
