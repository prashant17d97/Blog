# Blog

Blog is a Kotlin JVM backend project designed to serve content for a website and mobile applications. It utilizes the MongoDB driver and the Kobweb API library.

## Project Overview

- **Project Name:** Blog
- **Author:** [Prashant Singh](https://github.com/prashant17d97)
- **GitHub Repository:** [Blog](https://github.com/prashant17d97/Blog.git)

## Technologies Used

- Kotlin JVM
- MongoDB Driver
- Kobweb API

## Getting Started

### Prerequisites

- Kotlin SDK
- MongoDB installed and running

### Setup

1. Clone the repository:

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

5. Open your browser and navigate to [http://localhost:PORT](http://localhost:PORT) to access the API.

## API Endpoints

- `/api/createPost`: Endpoint for creating a new post.
- `/api/getAuthor`: Endpoint for fetching author information.
- `/api/post`: Endpoint for fetching post information.

**Note:** More detailed API documentation and examples can be added here.

## Contributors

- [Prashant Singh](https://github.com/prashant17d97)

## License

This project is licensed under the [MIT License](LICENSE).

Feel free to contribute and make it better!

