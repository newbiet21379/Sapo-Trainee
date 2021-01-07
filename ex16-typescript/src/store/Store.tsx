import React from 'react';
import { IState } from '../interface/Interface';

const LoginContext = React.createContext<IState|any>({});
export default LoginContext;
