import React from 'react';

export default function Button(props:any) {

    return (
        <div className="single-box">
            <button onClick={props.onClick} type="submit">{props.confirm ? 'Logout' : 'Login'}</button>
        </div>
    );

}