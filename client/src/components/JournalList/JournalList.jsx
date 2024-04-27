import './JournalList.css';
import CardButton from '../CardButton/CardButton';
import JournalItem from '../JournalItem/JournalItem';
import { useMemo } from 'react';

function JournalList({ posts, setPost }) {

	const sortPosts = (a, b) => {
		if (a.date < b.date) {
			return 1;
		} else {
			return -1;
		}
	};

	const filteredPosts = useMemo(() => posts
		.sort(sortPosts), [posts]);

	if (posts.length === 0) {
		return <p>There are no entries yet, add the first one</p>;
	}


	return	<>
		{filteredPosts
			.map(el => (
				<CardButton key={el?.id} onClick={() => setPost(el)}>
					<JournalItem title={el?.title} date={el?.date} text={el?.text} />
				</CardButton>
			))}
	</>;
}

export default JournalList;