import React from 'react';
// import './Loading.css';

export default function Loading():JSX.Element {
    return (
        <div className="cont">
            <div className="paper"></div>
            <button><div className='loader'>
                <div></div>
                <div></div>
                <div></div>
                <div></div>
                <div></div>
                <div></div>
            </div>Loading</button>
            <div className="g-cont">
                <div className="garbage"></div>
                <div className="garbage"></div>
                <div className="garbage"></div>
                <div className="garbage"></div>
                <div className="garbage"></div>
                <div className="garbage"></div>
                <div className="garbage"></div>
                <div className="garbage"></div>
            </div>
        </div>
    );
}