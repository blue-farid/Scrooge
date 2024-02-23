import React, { useState, useEffect } from 'react';
import './SnappboxDashboard.css';
import Api from "./Api";
import {useLocation} from "react-router-dom";
import logo from './assets/images/box-logo.svg';


const SnappBoxDashboard = () => {
    const [totalDebit, setTotalDebit] = useState(0);
    const [loading, setLoading] = useState(true);
    const [phoneNumber, setPhoneNumber] = useState('');
    const pageNumber = '1'
    const pageSize = '15'
    const includeAsanPardakhtWallet = true;
    const location = useLocation();

    useEffect(() => {
        const fetchTotalDebitValue = async () => {
            if (location.state && location.state.phoneNumber)
                setPhoneNumber(location.state.phoneNumber);
            else {
                console.error("phone number is empty!");
            }

            try {
                setLoading(true);
                const response = await Api.get('/box/history', {
                    params: {
                        phoneNumber,
                        pageNumber,
                        pageSize,
                        includeAsanPardakhtWallet
                    }
                });
                setTotalDebit(response.data.totalDebit);
                setLoading(false);
            } catch (error) {
                console.error('Error fetching total debit value:', error);
                setLoading(false);
            }
        };
        fetchTotalDebitValue();
    }, [includeAsanPardakhtWallet, pageNumber, pageSize, phoneNumber, location.state]);

    return (
        <div className="dashboard-container">
            <h2>
                <img src={logo} alt="Snapp! Box Logo" className="logo" />
            </h2>
            {loading ? (
                <div className="report-card">
                    <p>Loading total debit value...</p>
                </div>
            ) : (
                <div className="report-card">
                    <h3>Total Debit Value Report</h3>
                    <p>{`Total Debit Value: ${totalDebit.toLocaleString()} Rials`}</p>
                    {/* Additional content or visualizations can be added here */}
                </div>
            )}
        </div>
    );
};

export default SnappBoxDashboard;
