"use strict";

const bookId = document.getElementById("book id");
const title = document.getElementById("title");
const description = document.getElementById("description");
const author = document.getElementById("author");
const readBooks = document.getElementById("read all books");
const bookById = document.getElementById("book by id");
const createBook = document.getElementById("create book");
const deleteBook = document.getElementById("delete book");
const updateBook = document.getElementById("update book");
const readByAuthor = document.getElementById("find by author");

const reviewId = document.getElementById("review id");
const fname = document.getElementById("first name");
const sname = document.getElementById("surname");
const rating = document.getElementById("rating");
const review = document.getElementById("review");
const revBookId = document.getElementById("book id review");
const readReviews = document.getElementById("read all reviews");
const reviewById = document.getElementById("review by id");
const createReview = document.getElementById("create review");
const deleteReview = document.getElementById("delete review");
const updateReview = document.getElementById("update review");
const readByRating = document.getElementById("find by rating");

let readAllBooks = () => {
  fetch("http://localhost:8080/book/readAll")
    .then((data) => {
      if (data.status !== 200) {
        console.error(`status: ${data.status}`);
        return;
      }
      return data.json();
    })
    .then((objectData) => {
      console.log(objectData);
      let tableData = "";
      objectData.map((values) => {
        let reviewData = "";
        if (values.reviews.length == 0) reviewData = `<p>No Reviews</p>`;
        else {
          values.reviews.map((review) => {
            reviewData += `<li>Review Id: ${review.id}<br> Rating: ${review.rating}<br> Review: ${review.review}</li>`;
          });
        }
        tableData += `<tr>
        <td>${values.id}</td>
        <td>${values.title}</td>
        <td>${values.description}</td>
        <td>${values.author}</td>
        <td><ul>${reviewData}</ul></td>
      </tr>`;
      });
      document.getElementById("table_body_book").innerHTML = tableData;
    })
    .catch((err) => {
      console.log(err);
    });
};

let readByIdBook = () => {
  let id = bookId.value;
  fetch("http://localhost:8080/book/read/" + id)
    .then((data) => {
      if (data.status !== 200) {
        console.error(`status: ${data.status}`);
        return;
      }
      return data.json();
    })
    .then((objectData) => {
      console.log(objectData);
      let tableData = "";
      let reviewData = "";
      if (objectData.reviews.length == 0) reviewData = `<p>No Reviews</p>`;
      else {
        objectData.reviews.map((review) => {
          reviewData += `<li>Review Id: ${review.id}<br> Rating: ${review.rating}<br> Review: ${review.review}</li>`;
        });
      }
      tableData = `<tr>
        <td>${objectData.id}</td>
        <td>${objectData.title}</td>
        <td>${objectData.description}</td>
        <td>${objectData.author}</td>
        <td>${reviewData}</td>
      </tr>`;
      document.getElementById("table_body_book").innerHTML = tableData;
    })
    .catch((err) => {
      console.log(err);
    });
};

let createABook = () => {
  fetch("http://localhost:8080/book/create", {
    method: "post",
    headers: {
      "Content-type": "application/json",
    },
    body: JSON.stringify({
      title: `${title.value}`,
      description: `${description.value}`,
      author: `${author.value}`,
    }),
  })
    .then((res) => res.json())
    .then((data) => console.log(`Request succeeded with JSON response ${data}`))
    .catch((error) => console.log(`Request failed ${error}`));
};

let deleteABook = () => {
  let id = bookId.value;
  fetch("http://localhost:8080/book/delete/" + id, {
    method: "delete",
  })
    .then((data) => {
      console.log(`Request succeeded with JSON response ${data}`);
    })
    .catch((error) => {
      console.log(err);
    });
};

let updateABook = () => {
  let id = bookId.value;
  fetch("http://localhost:8080/book/update/" + id, {
    method: "put",
    headers: {
      "Content-type": "application/json",
    },
    body: JSON.stringify({
      title: `${title.value}`,
      description: `${description.value}`,
      author: `${author.value}`,
    }),
  }).catch((err) => {
    console.log(err);
  });
};

let readByAuthorBook = () => {
  let auth = author.value;
  fetch("http://localhost:8080/book/author/" + auth)
    .then((data) => {
      if (data.status !== 200) {
        console.error(`status: ${data.status}`);
        return;
      }
      return data.json();
    })
    .then((objectData) => {
      console.log(objectData);
      let tableData = "";
      objectData.map((values) => {
        let reviewData = "";
        if (values.reviews.length == 0) reviewData = `<p>No Reviews</p>`;
        else {
          values.reviews.map((review) => {
            reviewData += `<li>Review Id: ${review.id}<br> Rating: ${review.rating}<br> Review: ${review.review}</li>`;
          });
        }
        tableData += `<tr>
          <td>${values.id}</td>
          <td>${values.title}</td>
          <td>${values.description}</td>
          <td>${values.author}</td>
          <td><ul>${reviewData}</ul></td>
        </tr>`;
      });
      document.getElementById("table_body_book").innerHTML = tableData;
    })
    .catch((err) => {
      console.log(err);
    });
};

readBooks.onclick = () => readAllBooks();
createBook.onclick = () => createABook();
deleteBook.onclick = () => deleteABook();
updateBook.onclick = () => updateABook();
bookById.onclick = () => readByIdBook();
readByAuthor.onclick = () => readByAuthorBook();

let readAllReviews = () => {
  fetch("http://localhost:8080/review/readAll")
    .then((data) => {
      if (data.status !== 200) {
        console.error(`status: ${data.status}`);
        return;
      }
      return data.json();
    })
    .then((objectData) => {
      console.log(objectData);
      let tableData = "";
      objectData.map((values) => {
        tableData += `<tr>
            <td>${values.id}</td>
            <td>${values.firstName}</td>
            <td>${values.surname}</td>
            <td>${values.rating}</td>
            <td>${values.review}</td>
          </tr>`;
      });
      document.getElementById("table_body_review").innerHTML = tableData;
    })
    .catch((err) => {
      console.log(err);
    });
};

let readByIdReview = () => {
  let id = reviewId.value;
  fetch("http://localhost:8080/review/read/" + id)
    .then((data) => {
      if (data.status !== 200) {
        console.error(`status: ${data.status}`);
        return;
      }
      return data.json();
    })
    .then((objectData) => {
      console.log(objectData);
      let tableData = "";
      tableData = `<tr>
            <td>${objectData.id}</td>
            <td>${objectData.firstName}</td>
            <td>${objectData.surname}</td>
            <td>${objectData.rating}</td>
            <td>${objectData.review}</td>
          </tr>`;
      document.getElementById("table_body_review").innerHTML = tableData;
    })
    .catch((err) => {
      console.log(err);
    });
};

let readByRatingReview = () => {
  let num = rating.value;
  fetch("http://localhost:8080/review/rating/" + num)
    .then((data) => {
      if (data.status !== 200) {
        console.error(`status: ${data.status}`);
        return;
      }
      return data.json();
    })
    .then((objectData) => {
      console.log(objectData);
      let tableData = "";
      objectData.map((values) => {
        tableData += `<tr>
                <td>${values.id}</td>
                <td>${values.firstName}</td>
                <td>${values.surname}</td>
                <td>${values.rating}</td>
                <td>${values.review}</td>
                </tr>`;
      });
      document.getElementById("table_body_review").innerHTML = tableData;
    })
    .catch((err) => {
      console.log(err);
    });
};

let createAReview = () => {
  fetch("http://localhost:8080/review/create", {
    method: "post",
    headers: {
      "Content-type": "application/json",
    },
    body: JSON.stringify({
      firstName: `${fname.value}`,
      surname: `${sname.value}`,
      rating: `${rating.value}`,
      review: `${review.value}`,
      book: {
        id: `${revBookId.value}`,
      },
    }),
  })
    .then((res) => res.json())
    .then((data) => console.log(`Request succeeded with JSON response ${data}`))
    .catch((error) => console.log(`Request failed ${error}`));
};

let deleteAReview = () => {
  let id = reviewId.value;
  fetch("http://localhost:8080/review/delete/" + id, {
    method: "delete",
  })
    .then((data) => {
      console.log(`Request succeeded with JSON response ${data}`);
    })
    .catch((error) => {});
};

let updateAReview = () => {
  let id = reviewId.value;
  fetch("http://localhost:8080/review/update/" + id, {
    method: "put",
    headers: {
      "Content-type": "application/json",
    },
    body: JSON.stringify({
      firstName: `${fname.value}`,
      surname: `${sname.value}`,
      rating: `${rating.value}`,
      review: `${review.value}`,
      book: {
        id: `${revBookId.value}`,
      },
    }),
  }).catch((err) => {
    console.log(err);
  });
};

readReviews.onclick = () => readAllReviews();
createReview.onclick = () => createAReview();
deleteReview.onclick = () => deleteAReview();
updateReview.onclick = () => updateAReview();
reviewById.onclick = () => readByIdReview();
readByRating.onclick = () => readByRatingReview();
