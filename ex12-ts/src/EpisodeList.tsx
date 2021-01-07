import { IEpisode } from './interfaces';
import React from 'react';

export default function EpisodeList(props: any): Array<JSX.Element> {
	const { episodes, toggleFavAction, favorites, store } = props;
	const { state, dispatch } = store;
	return episodes.map((episode: IEpisode) => {
		return (
			<section key={episode.id} className="episode">
				<a href={episode.url}>
					{' '}
					<img src={episode.image.medium} alt={`Rick and Morty ${episode.name}`} />
				</a>
				<div>{episode.name}</div>
				<section style={{ display: 'flex', justifyContent: 'space-between' }}>
					<div>
						Season: {episode.season} Number : {episode.number}
					</div>
					<button type="button" onClick={() => toggleFavAction(state,dispatch,episode)}>
						{favorites.find((fav: IEpisode) => fav.id === episode.id) ? 'Unfav' : 'Fav'}
					</button>
				</section>
			</section>
		);
	});
}
