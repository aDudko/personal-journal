import './App.css';
import LeftPanel from './layouts/LeftPanel/LeftPanel';
import Body from './layouts/Body/Body';
import Header from './components/Header/Header';
import JournalAddButton from './components/JournalAddButton/JournalAddButton';
import JournalList from './components/JournalList/JournalList';
import JournalForm from './components/JournalForm/JournalForm';
import {useEffect, useState} from 'react';
import axios from 'axios';
import Pagination from "./components/Pagination/Pagination.jsx";

const server = axios.create({
	baseURL: 'http://localhost:8081'
});

function mapPosts(posts) {
	if (!posts) {
		return [];
	}
	return posts.map(post => ({ ...post }));
}

function App() {

	const [posts, setPosts] = useState([]);
	const [selectedPost, setSelectedPost] = useState(null);
	// Pagination
	const [currentPage, setCurrentPage] = useState(1);
	const [postsPerPage] = useState(3);
	const lastPostsIndex = currentPage * postsPerPage;
	const firstPostIndex = lastPostsIndex - postsPerPage;
	const currentPost = posts.slice(firstPostIndex, lastPostsIndex);
	const paginate = pageNumber => setCurrentPage(pageNumber);

	useEffect(() => {
		const getPosts = async () => {
			setPosts((await server.get('/post/list')).data);
		};
		getPosts();
	}, []);

	const createOrUpdatePost = async (postDto) => {
		let data = {...postDto};
		try {
			let url = '/post'.concat(postDto.id ? ('/' + postDto.id) : '');
			await server.post(url, data);
		} catch (error) {
			console.error('Error creating or updating: ', error);
		}
		setPosts((await server.get('/post/list')).data);
	};

	const deletePost = async (id) => {
		try {
			await server.delete(`/post/${id}`);
		} catch (error) {
			console.error('Error deleting: ', error);
		}
		setPosts((await server.get('/post/list')).data);
		if (((posts.length-1) % postsPerPage) === 0) {
			setCurrentPage(currentPage - 1);
		}
	};

	return (
		<>
			<div className='app'>
				<LeftPanel>
					<Header />
					<JournalAddButton clearForm={() => setSelectedPost(null)} />
					<JournalList posts={mapPosts(currentPost)} setPost={setSelectedPost} />
					<Pagination postsPerPage={postsPerPage} totalPosts={posts.length} paginate={paginate} currentPage={currentPage} setCurrentPage={setCurrentPage} />
				</LeftPanel>
				<Body>
					<JournalForm onSubmit={createOrUpdatePost} onDelete={deletePost} data={selectedPost} />
				</Body>
			</div>
		</>
	);
}

export default App;
