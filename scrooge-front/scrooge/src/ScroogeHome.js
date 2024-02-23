import React from 'react';
import './ScroogeHome.css';
import {useNavigate} from "react-router-dom";

const companies = ['Snapp! Box'];

const ScroogeHome = () => {

    const nav = useNavigate();
    const handleClick = (companyName) => {
        if (companyName === companies[0]) {
            nav('/snappbox-login');
        }
    };

    return (
        <div className="scrooge-home">
            <header>
                <h1>Scrooge Application!</h1>
            </header>
            <main>
                {companies.map((company, index) => (
                    <p key={index} onClick={() => handleClick(company)}>
                        {company}
                    </p>
                ))}
            </main>
            <footer>
                <span>Contact Us</span> | <span>Privacy Policy</span>
            </footer>
        </div>
    );
};

export default ScroogeHome;
