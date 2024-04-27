import './Pagination.css';

function Pagination({ postsPerPage, totalPosts, paginate, currentPage, setCurrentPage }) {

	if (totalPosts <= postsPerPage) {
		return null;
	}

	const pageNumbers = [];
	const countPages = Math.ceil(totalPosts / postsPerPage);

	for (let i = 1; i <= countPages; i++) {
		pageNumbers.push(i);
	}

	const prevPage = () => {
		if (currentPage > 1) {
			setCurrentPage(currentPage - 1);
		}
	};

	const nextPage = () => {
		if (currentPage < countPages) {
			setCurrentPage(currentPage + 1);
		}
	};

	return (
		<>
			<ul className="pagination">
				<li><a href="#" onClick={prevPage}>Prev</a></li>
				{
					pageNumbers.map(number => (
						<li className={currentPage === number ? 'active' : ''} key={number}>
							<a href="#" onClick={() => {
								paginate(number);
							}}>{number}</a>
						</li>
					))
				}
				<li><a href="#" onClick={nextPage}>Next</a></li>
			</ul>
		</>
	);
}

export default Pagination;