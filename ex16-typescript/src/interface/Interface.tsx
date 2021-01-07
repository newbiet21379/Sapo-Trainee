export interface ICategory {
	id: number;
	name: string;
	create_date?: string;
	fix_date?: string;
	description: string;
}
export interface IState {
	categories: Array<ICategory>;
	category: ICategory;
}

export interface IAction {
	type: string;
	payload: any;
}

export interface IParams {
	page: number;
	query: string;
	pageSize: number;
}

export interface IUser {
	id?: number;
	username: string;
	password: string;
	role?: [];
}

export interface ILogin {
	user: {};
	message: string;
	isLogin: boolean;
}

export interface ILoginProps {
	login: {};
	onLogin: (username: string, password: string) => void;
	onLogOut: () => void;
	getDate: () => string;
}
