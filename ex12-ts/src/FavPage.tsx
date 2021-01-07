import React, { useContext, Suspense } from 'react';
import { Store } from './Store';
import {toggleFavAction} from './Actions'
import { IEpisodeProps } from './interfaces';
const EpisodeList = React.lazy<any>(() => import('./EpisodeList'));

export default function FavPage() {
	const { state, dispatch } = useContext(Store);
	const props: IEpisodeProps = {
		episodes: state.favorites,
		store: { state, dispatch },
		toggleFavAction,
		favorites: state.favorites,
	};
	return(
        <Suspense fallback={<div>loading...</div>}>
            <div className="episode-layout">
                <EpisodeList {...props}/>
            </div>
        </Suspense>
    );
}
