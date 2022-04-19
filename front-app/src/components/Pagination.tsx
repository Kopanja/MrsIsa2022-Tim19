import React, { FC } from "react";

interface Props {
  numOfPages: number;
  paginate: (page: number) => void;
}

const Pagination: FC<Props> = ({ numOfPages, paginate }) => {
  const pageNumbers = [];

  for (let i = 1; i <= numOfPages; i++) {
    pageNumbers.push(i);
  }

  return (
    <nav>
      <ul className="pagination">
        {pageNumbers.map((pageNumber) => (
          <li key={pageNumber} className="page-item">
            <a
              onClick={() => paginate(pageNumber)}
              href="!#"
              className="page-link"
            >
              {pageNumber}
            </a>
          </li>
        ))}
      </ul>
    </nav>
  );
};

export default Pagination;
