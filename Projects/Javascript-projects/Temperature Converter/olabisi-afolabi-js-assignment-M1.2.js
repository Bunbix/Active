// Assignment M1.2 - Problem-Solving Practice
// Completed by: [Your First and Last Name]

// Exercise 1: Temperature Converter
console.log("Exercise 1: Temperature Converter");
console.log("================================");

function celsiusToFahrenheit(celsius) {
    return (celsius * 9/5) + 32;
}

function fahrenheitToCelsius(fahrenheit) {
    return (fahrenheit - 32) * 5/9;
}

// Test with the example values
const celsiusTemp = 23.0;
const fahrenheitTemp = 73.4;

const convertedToFahrenheit = celsiusToFahrenheit(celsiusTemp);
const convertedToCelsius = fahrenheitToCelsius(fahrenheitTemp);

console.log(`${celsiusTemp.toFixed(1)} Celsius is ${convertedToFahrenheit.toFixed(1)} Fahrenheit`);
console.log(`${fahrenheitTemp.toFixed(1)} Fahrenheit is ${convertedToCelsius.toFixed(1)} Celsius`);

// Additional test case
console.log("\nAdditional test with 0°C and 32°F:");
console.log(`0.0 Celsius is ${celsiusToFahrenheit(0).toFixed(1)} Fahrenheit`);
console.log(`32.0 Fahrenheit is ${fahrenheitToCelsius(32).toFixed(1)} Celsius`);

// Exercise 2: Find Largest of Three Numbers
console.log("\n\nExercise 2: Find Largest Number");
console.log("==============================");

let a = 3;
let b = 7;
let c = 12;

console.log(`Given numbers: a = ${a}, b = ${b}, c = ${c}`);

let largest;

if (a >= b && a >= c) {
    largest = a;
} else if (b >= a && b >= c) {
    largest = b;
} else {
    largest = c;
}

console.log(`The largest number is: ${largest}`);

// Test with different numbers
console.log("\nTest with different values:");
a = 15;
b = 8;
c = 15;
console.log(`Given numbers: a = ${a}, b = ${b}, c = ${c}`);

if (a >= b && a >= c) {
    largest = a;
} else if (b >= a && b >= c) {
    largest = b;
} else {
    largest = c;
}
console.log(`The largest number is: ${largest}`);

// Exercise 3: Print Odd Numbers from 1 to 50
console.log("\n\nExercise 3: Odd Numbers from 1 to 50");
console.log("==================================");

console.log("Odd numbers from 1 to 50:");
for (let i = 1; i <= 50; i++) {
    if (i % 2 !== 0) {
        console.log(i);
    }
}

// Alternative solution using step increment
console.log("\nAlternative approach (using step of 2):");
for (let i = 1; i <= 50; i += 2) {
    console.log(i);
}

// Exercise 4: Millionaire Savings Account
console.log("\n\nExercise 4: Millionaire Savings Account");
console.log("=====================================");

function calculateYearsToMillion(startingBalance) {
    let balance = startingBalance;
    let yearsTo100k = 0;
    let yearsToMillion = 0;
    let years = 0;
    let reached100k = false;
    
    console.log(`Starting balance: $${balance.toFixed(2)}`);
    
    while (balance < 1000000) {
        years++;
        balance *= 2; // Double the balance each year
        
        if (!reached100k && balance >= 100000) {
            yearsTo100k = years;
            reached100k = true;
        }
        
        if (balance >= 1000000) {
            yearsToMillion = years;
        }
    }
    
    console.log(`Years to reach $100,000: ${yearsTo100k}`);
    console.log(`Years to reach $1,000,000: ${yearsToMillion}`);
    console.log(`Final balance after ${years} years: $${balance.toFixed(2)}`);
    
    return { yearsTo100k, yearsToMillion };
}

// Test with different starting balances
console.log("Test 1 - Starting with $1,000:");
calculateYearsToMillion(1000);

console.log("\nTest 2 - Starting with $10,000:");
calculateYearsToMillion(10000);

console.log("\nTest 3 - Starting with $50,000:");
calculateYearsToMillion(50000);

// Additional test to show edge case
console.log("\nTest 4 - Starting with $100,000 (already at first milestone):");
let balance = 100000;
let years = 0;

console.log(`Starting balance: $${balance.toFixed(2)}`);
console.log(`Years to reach $100,000: 0 (already reached)`);

while (balance < 1000000) {
    years++;
    balance *= 2;
}

console.log(`Years to reach $1,000,000: ${years}`);
console.log(`Final balance after ${years} years: $${balance.toFixed(2)}`);

console.log("\n--- End of Program ---");