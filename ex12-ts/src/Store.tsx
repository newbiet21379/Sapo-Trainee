import React, { useReducer } from 'react';
import { IState, IAction } from './interfaces';

const initState: IState = {
	episodes: [],
	favorites: [],
};
export const Store = React.createContext<IState | any>(initState);

function reducer(state: IState, action: IAction) {
	switch (action.type) {
		case 'FETCH-DATA':
			return { ...state, episodes: action.payload };
		case 'FAV-ADD':
			return { ...state, favorites: [...state.favorites, action.payload] };
		case 'FAV-REMOVE':
			return { ...state, favorites: action.payload };
		default:
			return state;
	}
}

export function StoreProvider({ children }: JSX.ElementChildrenAttribute): JSX.Element {
	const [state, dispatch] = useReducer(reducer, initState);
	return <Store.Provider value={{ state, dispatch }}>{children}</Store.Provider>;
}
