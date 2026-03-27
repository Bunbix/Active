# Copilot Instructions for Web Dev Projects

## Project Overview
This workspace contains two distinct projects:
1. **Java OOP Learning Project** - Basic Java OOP concepts (Product.java, ProductDemo.java)
2. **Loan Amortization Calculator** - Interactive web application for calculating mortgage payments

## Architecture & Key Components

### Java Project Structure
- **Product.java**: Core OOP demonstration featuring:
  - Instance variables (name, price, productCode)
  - Static variable tracking (productCount) and static methods
  - Overloaded `printProductInfo()` methods (no-arg and boolean variations)
  - Proper `equals()` override for object comparison using all three attributes
  - Named constant `TAX_RATE = 0.1` for financial calculations
- **ProductDemo.java**: Comprehensive demonstration executing all OOP concepts in sequence

**Build & Run**: `javac ProductDemo.java && java ProductDemo`

### Loan Calculator Architecture
Single-page HTML application with embedded CSS and vanilla JavaScript. Structure follows MVC-like pattern:

**Key Data Structures:**
- `countrySettings`: Country-specific configuration (CA: $, max 30-year amortization; US: $, max 40-year amortization)
- `frequencyLabels`: Payment frequency metadata (Annual, Monthly, Bi-Weekly, etc.)
- `amortizationData`: Array of period objects containing {period, payment, principal, interest, balance, rate}

**Rate Handling Logic:**
- Two rate types: Fixed (single rate) and Variable (initial rate → new rate after X years)
- Variable rates create two rate blocks with different periods
- Payment calculation uses standard amortization formula: `P * (r(1+r)^n) / ((1+r)^n - 1)` where r = periodic rate
- Adjusts last payment to handle rounding

**Key Calculation Functions:**
- `calculateAmortization()`: Core logic—iterates through rate periods, applies periodic interest, tracks balance
- `updateSummary()`: Displays periodic payment, total interest, total paid, payoff date
- `updateAmortizationTable()`: Populates table with full amortization schedule
- `updatePaymentChart()`: Canvas-based pie chart showing principal vs. interest breakdown

**Input Synchronization Pattern:**
Each numeric input has paired controls:
- Text input + Range slider (kept in sync via event listeners)
- Both update display value and trigger `calculateAmortization()`
- Example: `loanAmount` ↔ `loanAmountSlider` → `updateLoanAmountDisplay()`

**Export Features:**
- CSV export uses standard comma-separated format with headers
- PDF export (implementation details in script section)
- Both generate downloadable files with full amortization schedule

## Project-Specific Patterns & Conventions

### Java Conventions
- Use JSDoc comments for public methods (see Product.java pattern)
- Leverage static methods for utility operations (Product.getProductCount())
- Override equals() comparing all relevant attributes with proper null/type checks

### JavaScript Conventions (Loan Calculator)
- **Module-level organization**: All UI elements cached at startup in `elements` object
- **Event delegation**: Country/rate-type selectors use `data-*` attributes with click handlers
- **Immutable country settings**: `currentCountry` and `currentRateType` variables control UI state
- **Currency formatting**: Always use `formatCurrency()` helper and respect country symbol
- **Table generation**: Build rows dynamically from `amortizationData`—never edit HTML directly
- **Validation**: Input constraints enforced via HTML5 attributes (min, max, step)

### Calculation Precision
- Interest calculations use full JavaScript floating-point precision
- Final payment adjusted to prevent rounding errors (balance-to-zero)
- Display uses `toLocaleString()` for proper currency formatting with 2 decimals

## Common Workflows

### Modifying Loan Calculation
1. Locate rate logic in `calculateAmortization()` → rates array construction
2. Update `amortizationData` push logic if new period properties needed
3. Add corresponding table columns in `updateAmortizationTable()`
4. Update summary/chart if new metrics displayed

### Adding Payment Frequency
1. Add entry to `frequencyLabels` object with singular/plural forms
2. Add `<option>` in HTML select element
3. Calculation auto-adjusts via `paymentFrequency` parameter—no code changes needed

### Responsive Design
Breakpoint at 768px converts two-column layout (input left, results right) to single column. Uses CSS flexbox with `flex-direction: column`.

## Dependencies & External Tools
- **Chart rendering**: Canvas API (no external library)
- **Export**: Native browser File APIs
- **Styling**: Pure CSS (Segoe UI font, gradient backgrounds, flexbox layout)
- No external JavaScript libraries used

## Key Files to Understand
- [Product.java](Product.java) - OOP fundamentals
- [ProductDemo.java](ProductDemo.java) - OOP testing/demonstration
- [loan-calculator.html](LoanAmortization%20Project/loan-calculator.html) - Complete calculator application (~1100 lines, embedded CSS/JS)
