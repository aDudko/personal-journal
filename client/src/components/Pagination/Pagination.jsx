import './Pagination.css';
import React from 'react';

function Pagination({ postsPerPage, totalPosts, paginate, currentPage }) {

    if (totalPosts <= postsPerPage) {
        return null;
    }

    const pageNumbers = [];
    const countPages = Math.ceil(totalPosts / postsPerPage);

    for (let i = 1; i <= countPages; i++) {
        pageNumbers.push(i);
    }

    return (
        <>
            <ul className="pagination">
                {
                    pageNumbers.map(number => (
                        <li className={currentPage === number? 'active' : ''} key={number}>
                            <a href="#" onClick={() => {
                                paginate(number);
                            }}>{number}</a>
                        </li>
                    ))
                }
            </ul>
        </>
    )
}

export default Pagination;