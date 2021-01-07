import React, { useEffect, useState } from 'react';

export default function errorHandler(WrappedComponent: any, axios: any): JSX.Element {
	// eslint-disable-next-line react-hooks/rules-of-hooks
	const [state, setState] = useState<{ error: any | null }>({ error: '' });

	// eslint-disable-next-line react-hooks/rules-of-hooks
	useEffect(() => {
		// Set axios interceptors
		const requestInterceptor = axios.interceptors.request.use((req: any) => {
			setState({ error: null });
			return req;
		});

		const responseInterceptor = axios.interceptors.response.use(
			(res: any) => res,
			(error: any) => {
				alert('Error happened');
				setState({ error });
			}
		);

		return () => {
			// Remove handlers, so Garbage Collector will get rid of if WrappedComponent will be removed
			axios.interceptors.request.eject(requestInterceptor);
			axios.interceptors.response.eject(responseInterceptor);
		};
	});
	let renderSection = state.error ? <div>Error</div> : <WrappedComponent {...state} />;
	return renderSection;
}
