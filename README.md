<p align="center">
  <img src="https://www.metu.edu.tr/system/files/logo_orj/3/3.8.png" alt="Logo" >
  <h2 align="center">PhotoHocam</h2>
  # PhotoHocam App

PhotoHocam is an application designed for  photo sharing. Users can register, send friend requests to each other, allowing them to send photos with privacy features.

## Features

- **User Registration:** Users can create accounts and register on the platform.

- **Friend Requests:** Users can send and receive friend requests to connect with others.

- **Photo Sharing:** Users can securely send photos to each other within private chats.

- **One-Time View:** Received photos can only be viewed once, enhancing user privacy.

- **Base64 Encoding:** Photos are stored in the database in base64 format until they are accessed, ensuring convenient access and secure preservation.

## Technologies Used

- **Frontend:** Flutter

- **Backend:** Spring Boot

- **Database:** PostgreSQL

## Getting Started

### Prerequisites

- Java 17
- Gradle

### Installation

1. Clone the repository:

   ```
   https://github.com/bartucank/PhotoHocamBackEnd.git
   ```
2. Import project using Gradle:
   ```
   cd photoshare-app
   gradle build
   ```
3. Configure Google Cloud SQL in application.properties:
   ```
   # application.properties

   spring.datasource.url=jdbc:google:mysql://your-cloud-sql-instance:your-database
   spring.datasource.username=your-username
   spring.datasource.password=your-password
   ```
4. Run the application:
   ```
   gradle bootRun
   ```
   The application will be accessible at http://localhost:8080.

## Usage

### User Registration

1. Open the application in your browser.
2. Click on the "Register" button.
3. Fill in the required information to create a new account.

### Friend Requests

1. Log in to your account.
2. Navigate to the "Friends" section.
3. Send and accept friend requests.

### Photo Sharing

1. In the friend's profile, click on the photo icon to share a photo.

## Contributing

We welcome contributions from the community. To contribute, please follow our [contribution guidelines](CONTRIBUTING.md).

## License

This project is licensed under the [MIT License](LICENSE). Feel free to use, modify, and distribute the code as per the terms of the license.

</p>
