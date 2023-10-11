# Ecolens Project README
### Check out our media here:
https://www.viam.com/post/build-backstories-creating-eco-lens-the-smart-machine-that-helps-with-recycling
https://www.instagram.com/p/CyGyNkQrwBm/

## Introduction

Welcome to the Ecolens project! Ecolens is a cloud-based machine learning webstream application designed to identify recyclable items from non-recyclable ones.
It not only provides users with sustainability literacy but also offers a smart trash management system for trash can owners. 
This README provides an overview of the project, its functionality, and how to get started.

## Table of Contents

- [Project Overview](#project-overview)
- [Getting Started](#getting-started)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Project Overview

Ecolens is built to help users and trash can owners make more sustainable choices when disposing of items. 
It employs machine learning and computer vision to identify items through a webstream and determines whether they are recyclable or not. 
Once the camera identifies an item stored in the trained ML database, the application provides additional information on the item's material in terms of its sustainabilty.
Trash can owners can employ Ecolens to observe and manage the recycled vs. non-recycled trash in each and every trashcan over specific time periods as a sustainability metric.
Here are some of the key features and components of the project:

- **Web-based User Interface**: The project includes a web-based interface for users and trash can owners to interact with the system.

- **Machine Learning Model**: Ecolens utilizes a machine learning model to analyze live video streams and make real-time predictions about the recyclability of items.

- **API Endpoints**: The project provides API endpoints for various functionalities such as creating and managing trash cans, dumping trash, and retrieving item information.

- **Dashboard**: A dashboard is available for trash can owners to monitor the recycling statistics and status of their trash cans.

- **Recyclability Information**: Ecolens provides information about whether an item is recyclable or not, helping users make informed decisions.

## Getting Started

To get started with the Ecolens project, follow these steps:

1. **Clone the Repository**: Clone this repository to your local machine using the following command:

   ```
   git clone <repository-url>
   ```

2. **Set Up Environment**: Ensure you are using jbr-17 for your sdk, you may need to navigate to the 'ecolens' file to run the EcolensApplication.java class

3. **Start the Web Application**: Navigate to the project directory and start the web application by running the appropriate commands. Typically, this involves running `maven clean build` to start the development server.

4. **Access the Web Stream**: Navigate to the home page url, click the circular lens button to reach the web stream page, allow permission to use the camera

5. **Access the Dashboard**: If you are a trash can owner, you can access the dashboard by navigating to the dashboard URL provided.

6. **Interact with the API**: You can use the provided API endpoints to manage trash cans, retrieve item information, and more. Refer to the API documentation for details on available endpoints and their usage.

## Usage

The Ecolens project can be used for the following purposes:

- **Recycling Identification**: Users can use the web interface to identify whether an item is recyclable or not by showing it to the camera.

- **Smart Trash Management**: Trash can owners can monitor the recycling statistics and status of their trash cans through the dashboard.

- **API Integration**: Developers can integrate the provided API endpoints into their own applications to access recycling information and manage trash cans programmatically.

## Contributing

Contributions to the Ecolens project are welcome. If you would like to contribute, please follow these guidelines:

1. Fork the repository and create a new branch for your feature or bug fix.

2. Make your changes and ensure the code adheres to the project's coding standards.

3. Write clear and concise commit messages.

4. Create a pull request with a detailed description of your changes and the problem they solve.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

Thank you for using Ecolens! If you have any questions or need assistance, please feel free to reach out to us.

Happy recycling! 🌿🌍♻️
