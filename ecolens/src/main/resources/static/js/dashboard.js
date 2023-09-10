// Initialize the trashcanId variable with a default value
let trashcanId = 1;

// Add an event listener to the dropdown to update the trashcanId variable when the selection changes
document.addEventListener('DOMContentLoaded', function () {
    // Initialize the trashcanId variable with a default value
    let trashcanId = 1;
    const toggleButton = document.getElementById('toggleButton');
    toggleButton.addEventListener('click', toggleView);

    // Add an event listener to the dropdown to update the trashcanId variable when the selection changes
    const dropdown = document.getElementById('trashcanselector');
    dropdown.addEventListener('change', function () {
        trashcanId = dropdown.value;
    })
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
    fetch('/trashcan?current=true')
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

function displayCurrentTrash(data) {
    const currentTrashWidget = document.getElementById('currentTrashWidget');
    const currentTrashContent = document.getElementById('currentTrashContent');

    // Clear any previous content in the widget
    currentTrashContent.innerHTML = '';

    // Create and append elements to display the data (customize as needed)
    const ul = document.createElement('ul');
    for (const item of data) {
        const li = document.createElement('li');
        li.textContent = `${item.name}: ${item.description}`;
        ul.appendChild(li);
    }

    // Append the list to the widget content
    currentTrashContent.appendChild(ul);
}

// Call the function to populate the widget when the page loads
window.addEventListener('load', () => {
    getAllCurrentTrash();
});

let currentView = 'recycled'; // Initial view

// Function to toggle between views
function toggleView() {
    if (currentView === 'recycled') {
        // Toggle to non-recycled view
        displayNonRecycledTrash(data); // Pass the data here
        currentView = 'non-recycled';
    } else {
        // Toggle to recycled view
        displayRecycledTrash(data); // Pass the data here
        currentView = 'recycled';
    }
}

// Attach a click event listener to the toggle button
const toggleButton = document.getElementById('toggleButton');
toggleButton.addEventListener('click', toggleView);

// Function to display recycled trash data
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