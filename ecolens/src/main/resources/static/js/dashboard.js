document.addEventListener('DOMContentLoaded', function () {
    // Initialize the trashcanId variable with a default value
    let trashcanId = 1;

    // Add an event listener to the dropdown to update the trashcanId variable when the selection changes
    const dropdown = document.getElementById('trashcanselector');
    dropdown.addEventListener('change', function () {
        trashcanId = dropdown.value;
    });

    function getAllTrashCanIds() {
        return fetch('/alltrashcan')
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                // Return the list of trash can IDs
                return data;
            })
            .catch(error => {
                // Handle errors here
                console.error('There was a problem with the fetch operation:', error);
                throw error; // Rethrow the error for further handling
            });
    }

    // Function to populate the trash can selector with options
    function populateTrashCanSelector(trashCanIds) {
        const trashCanSelector = document.getElementById('trashcanselector');

        // Clear any existing options
        trashCanSelector.innerHTML = '';

        // Create and append options based on the list of IDs
        trashCanIds.forEach(id => {
            const option = document.createElement('option');
            option.value = id;
            option.textContent = `Trash Can ${id}`;
            trashCanSelector.appendChild(option);
        });
    }

    // Fetch the list of trash can IDs and populate the selector
    getAllTrashCanIds()
        .then(trashCanIds => {
            // Call the function to populate the selector
            populateTrashCanSelector(trashCanIds);
        })
        .catch(error => {
            // Handle any errors that occurred during the fetch operation
            console.error('Error while retrieving Trash Can IDs:', error);
        });

    // Function to fetch and display both recycled and non-recycled trash data
    function getAllCurrentTrash() {
        fetch('/trashcan:all?current=true')
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                // Call the display functions to show the data
                displayRecycledTrash(data);
                displayNonRecycledTrash(data);
            })
            .catch(error => {
                // Handle errors here
                console.error('There was a problem with the fetch operation:', error);
            });
    }

    function displayFirstField(data) {
        // Assuming data is an array of objects
        if (Array.isArray(data) && data.length > 0) {
            // Get the first object from the array
            const firstObject = data[0];

            // Get the keys (field names) of the object
            const fieldNames = Object.keys(firstObject);

            // Display the first field in the specified HTML element
            const currentRecycledTrashContent = document.getElementById('currentRecycledTrashContent');
            if (currentRecycledTrashContent) {
                currentRecycledTrashContent.textContent = fieldNames[0]; // Display the first field
            }
        }
    }

    function displaySecondField(data) {
        // Assuming data is an array of objects
        if (Array.isArray(data) && data.length > 0) {
            // Get the first object from the array
            const firstObject = data[0];

            // Get the keys (field names) of the object
            const fieldNames = Object.keys(firstObject);

            // Display the second field in the specified HTML element
            const currentNonRecycledTrashContent = document.getElementById('currentNonRecycledTrashContent');
            if (currentNonRecycledTrashContent && fieldNames.length > 1) {
                currentNonRecycledTrashContent.textContent = firstObject[fieldNames[1]]; // Display the second field
            }
        }
    }
    

    function getAllLifetimeTrash() {
        fetch('/trashcan:all?current=false')
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                // Call the display functions to show the data
                displayRecycledTrash(data);
                displayNonRecycledTrash(data);
            })
            .catch(error => {
                // Handle errors here
                console.error('There was a problem with the fetch operation:', error);
            });
    }

// Call the function to populate the widget when the page loads
    window.addEventListener('load', () => {
        getAllLifetimeTrash();
    });

    let currentView = 'recycled'; // Initial view


    // Function to display recycled trash data
    function displayRecycledTrash(data) {
        // Assuming the data is an object with a "recycled" property containing the recycled trash number
        const recycledTrashNumber = data.recycled;

        // Replace 'recycledContent' with the ID of the HTML element where you want to display the recycled data
        const recycledContent = document.getElementById('recycledContent');

        // Update the content of the HTML element
        recycledContent.innerHTML = `<p>Recycled Trash: ${recycledTrashNumber}</p>`;
    }

    // Function to display non-recycled trash data
    function displayNonRecycledTrash(data) {
        // Assuming the data is an object with a "nonRecycled" property containing the non-recycled trash number
        const nonRecycledTrashNumber = data.nonRecycled;

        // Replace 'nonRecycledContent' with the ID of the HTML element where you want to display the non-recycled data
        const nonRecycledContent = document.getElementById('nonRecycledContent');

        // Update the content of the HTML element
        nonRecycledContent.innerHTML = `<p>Non-Recycled Trash: ${nonRecycledTrashNumber}</p>`;
    }
});
