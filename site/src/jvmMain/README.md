# Blog Kotlin JVM Backend Documentation

## Overview

Blog is a backend service developed in Kotlin JVM to serve content for a website and mobile applications. It uses the MongoDB driver for database operations and the Kobweb API library for handling HTTP requests.

### Project Details

- **Project Name:** Blog
- **Author:** Prashant Singh
- **GitHub Repository:** [Blog on GitHub](https://github.com/prashant17d97/Blog.git)

## Technologies Used

- **Kotlin JVM:** The primary language for developing the backend logic.
- **MongoDB Driver:** For interacting with the MongoDB database.
- **Kobweb API:** A lightweight Kotlin web framework for handling HTTP requests and responses.

## Getting Started

### Prerequisites

Before running the Blog backend, make sure you have the following installed:

- **Kotlin SDK:** The project is developed using Kotlin, so you need the Kotlin SDK installed.
- **MongoDB:** Ensure that MongoDB is installed and running, as the application relies on it for data storage.

### Setup

1. **Clone the Repository:**

    ```bash
    git clone https://github.com/prashant17d97/Blog.git
    cd Blog
    ```

2. Build the project:

    ```bash
    ./gradlew build
    ```
3. Change the directory to site:
    ```bash
    cd site
    ```

4. Run the application:

    ```bash
    kobweb run
    ```

5. **Access the API:**

   Open your web browser and navigate to [http://localhost:PORT](http://localhost:PORT), replacing `PORT` with the actual port number your application is running on.

## API Endpoints

### 1. `/api/createpost`

- **Method:** POST
- **Description:** Endpoint for creating a new post.
- **Usage:** Submit a POST request to create a new post.

### 2. `/api/getauthor`

- **Method:** GET
- **Description:** Endpoint for fetching author information.
- **Usage:** Submit a GET request to retrieve author details.

### 3. `/api/post`

- **Method:** GET
- **Description:** Endpoint for fetching post information.
- **Usage:** Submit a GET request to retrieve post details.

## Implementation Details

### 1. `MongoRepository` Interface

#### Methods:

- **`getHomeContent`**
   - **Description:** Retrieves home content from the MongoDB database.

- **`getAuthorContent`**
   - **Description:** Retrieves author content based on the provided `AuthorModel`.

- **`getAuthorContentById`**
   - **Description:** Retrieves author content based on the provided author ID.

- **`addPost`**
   - **Description:** Adds a new post to the MongoDB database.

- **`retrievePost`**
   - **Description:** Retrieves a post based on the provided post ID.

### 2. `MongoDB` Class

- **Description:** MongoDB repository class responsible for interacting with the MongoDB database.

#### Properties:

- `client`: MongoDB client instance.
- `database`: MongoDB database instance.
- `authorCollection`: MongoDB collection for author data.
- `postCollection`: MongoDB collection for post data.

#### Methods:

- **`getHomeContent`**
   - **Description:** Retrieves home content from the MongoDB database.

- **`getAuthorContent`**
   - **Description:** Retrieves author content based on the provided `AuthorModel`.

- **`getAuthorContentById`**
   - **Description:** Retrieves author content based on the provided author ID.

- **`addPost`**
   - **Description:** Adds a new post to the MongoDB database.

- **`retrievePost`**
   - **Description:** Retrieves a post based on the provided post ID.

## Contributors

- [Prashant Singh](https://github.com/prashant17d97)

## License

This project is licensed under the [MIT License](LICENSE).

Feel free to contribute and make it better!

