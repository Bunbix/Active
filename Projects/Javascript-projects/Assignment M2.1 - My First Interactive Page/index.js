/**
 * app.js - JavaScript for Interactive Squares Assignment
 * Assignment M2.1: My First Interactive Page
 * This script implements DOM manipulation with event listeners
 * to apply visual effects to three squares when buttons are clicked.
 * 
 * Squares are now: Green (Square 1), Yellow (Square 2), Red (Square 3)
 * 
 * Submitted by: Olabisi Afolabi
 */

// DOM element references
const square1 = document.getElementById('square1');
const square2 = document.getElementById('square2');
const square3 = document.getElementById('square3');
const button1 = document.getElementById('button1');
const button2 = document.getElementById('button2');
const button3 = document.getElementById('button3');
const resetButton = document.getElementById('resetButton');
const description1 = document.getElementById('description1');
const description2 = document.getElementById('description2');
const description3 = document.getElementById('description3');

// Effect descriptions for each square (updated for new colors)
const effectDescriptions = {
    original: {
        square1: "Green Square: Click to change me to purple!",
        square2: "Yellow Square: Click to transform me into a circle!",
        square3: "Red Square: Click to make me almost invisible with pink effect!"
    },
    active: {
        square1: "Now I'm purple! Click again to return to green.",
        square2: "Now I'm a circle! Click again to return to square.",
        square3: "Now I've almost disappeared! Click again to return to red."
    }
};

// State tracking for each square
const squareStates = {
    square1: { isChanged: false },
    square2: { isChanged: false },
    square3: { isChanged: false }
};

/**
 * Function to apply or remove color change effect on square1 (Green to Purple)
 */
function toggleColorChange() {
    // Toggle the color-change CSS class
    square1.classList.toggle('color-change');
    
    // Update square state
    squareStates.square1.isChanged = !squareStates.square1.isChanged;
    
    // Update description
    description1.textContent = squareStates.square1.isChanged 
        ? effectDescriptions.active.square1 
        : effectDescriptions.original.square1;
    
    console.log(`Green Square color changed to purple: ${squareStates.square1.isChanged}`);
}

/**
 * Function to apply or remove circle effect on square2 (Yellow Square to Circle)
 */
function toggleCircle() {
    // Toggle the circle CSS class
    square2.classList.toggle('circle');
    
    // Update square state
    squareStates.square2.isChanged = !squareStates.square2.isChanged;
    
    // Update description
    description2.textContent = squareStates.square2.isChanged 
        ? effectDescriptions.active.square2 
        : effectDescriptions.original.square2;
    
    console.log(`Yellow Square turned into circle: ${squareStates.square2.isChanged}`);
}

/**
 * Function to apply or remove disappear effect on square3 (Red Square to Pink Fade)
 */
function toggleDisappear() {
    // Toggle the disappear CSS class
    square3.classList.toggle('disappear');
    
    // Update square state
    squareStates.square3.isChanged = !squareStates.square3.isChanged;
    
    // Update description
    description3.textContent = squareStates.square3.isChanged 
        ? effectDescriptions.active.square3 
        : effectDescriptions.original.square3;
    
    console.log(`Red Square disappeared (pink fade): ${squareStates.square3.isChanged}`);
}

/**
 * Function to reset all squares to original state
 */
function resetAllSquares() {
    // Remove all effect classes from squares
    square1.classList.remove('color-change');
    square2.classList.remove('circle');
    square3.classList.remove('disappear');
    
    // Reset all square states
    squareStates.square1.isChanged = false;
    squareStates.square2.isChanged = false;
    squareStates.square3.isChanged = false;
    
    // Reset all descriptions
    description1.textContent = effectDescriptions.original.square1;
    description2.textContent = effectDescriptions.original.square2;
    description3.textContent = effectDescriptions.original.square3;
    
    console.log("All squares have been reset to original colors: Green, Yellow, Red");
}

// Event listeners for buttons
button1.addEventListener('click', toggleColorChange);
button2.addEventListener('click', toggleCircle);
button3.addEventListener('click', toggleDisappear);
resetButton.addEventListener('click', resetAllSquares);

// Log initialization to console
console.log("================================================");
console.log("Interactive Squares - Assignment M2.1");
console.log("Submitted by: Olabisi Afolabi");
console.log("================================================");
console.log("Page initialized successfully!");
console.log("Color Theme: Green | Yellow | Red");
console.log("Instructions:");
console.log("1. Click 'Change Color' to toggle green square to purple");
console.log("2. Click 'Make Circle' to toggle yellow square into a circle");
console.log("3. Click 'Disappear' to toggle red square to pink fade effect");
console.log("4. Click 'Reset All Squares' to restore original states");
console.log("================================================");