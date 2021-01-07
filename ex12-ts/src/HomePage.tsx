import React, { Fragment, useContext, useEffect, Suspense } from 'react';
import { Store } from './Store';
import { IEpisodeProps } from './interfaces';
import { FetchDataAction, toggleFavAction } from './Actions';
const EpisodeList = React.lazy<any>(() => import('./EpisodeList'));

export default function HomePage() {
	const { state, dispatch } = useContext(Store);
	useEffect(() => {
		state.episodes.length === 0 && FetchDataAction(dispatch);
	});

	const props: IEpisodeProps = {
		episodes: state.episodes,
		store: { state, dispatch },
		toggleFavAction,
		favorites: state.favorites,
	};
	return (
		<Fragment>
			<Suspense fallback={<div>loading ...</div>}>
				<section className="episode-layout">
					<EpisodeList {...props} />
				</section>
			</Suspense>
		</Fragment>
	);
}
