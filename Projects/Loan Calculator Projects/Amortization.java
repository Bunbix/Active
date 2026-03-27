<script>
    // ============================================
    // FINANCIAL CALCULATOR SUITE - COMPLETE IMPLEMENTATION
    // ============================================

    class FinancialCalculatorSuite {
        constructor() {
            this.currentTab = 'loan-calc';
            this.theme = localStorage.getItem('theme') || 'light';
            this.settings = {
                autoCalculate: true,
                saveHistory: true,
                defaultCurrency: 'USD',
                decimalPlaces: 2
            };
            this.charts = {};
            this.calculationHistory = [];
            
            this.loadSettings();
            this.initializeApp();
            this.setupEventListeners();
            this.initializeCharts();
            this.initialCalculation();
            this.switchTab('loan-calc');
        }
        
        loadSettings() {
            const saved = localStorage.getItem('financialCalculatorSettings');
            if (saved) {
                this.settings = JSON.parse(saved);
            }
            
            // Load calculation history
            const history = localStorage.getItem('calculationHistory');
            if (history && this.settings.saveHistory) {
                this.calculationHistory = JSON.parse(history);
            }
        }
        
        saveSettings() {
            localStorage.setItem('financialCalculatorSettings', JSON.stringify(this.settings));
            
            if (this.settings.saveHistory) {
                localStorage.setItem('calculationHistory', JSON.stringify(this.calculationHistory));
            }
        }
        
        initializeApp() {
            // Set theme
            document.documentElement.setAttribute('data-theme', this.theme);
            this.updateThemeIcon();
            
            // Set default dates
            const today = new Date();
            const nextMonth = new Date(today.getFullYear(), today.getMonth() + 1, 1);
            document.getElementById('startDate').valueAsDate = today;
            
            // Apply settings to UI
            this.applySettings();
        }
        
        applySettings() {
            // Apply settings to UI elements
            document.getElementById('autoCalculate').checked = this.settings.autoCalculate;
            document.getElementById('saveHistory').checked = this.settings.saveHistory;
            document.getElementById('defaultCurrency').value = this.settings.defaultCurrency;
            document.getElementById('decimalPlaces').value = this.settings.decimalPlaces;
            
            // Update settings display
            document.getElementById('settingAutoCalc').textContent = this.settings.autoCalculate ? 'Enabled' : 'Disabled';
            document.getElementById('settingHistory').textContent = this.settings.saveHistory ? 'Enabled' : 'Disabled';
            document.getElementById('settingCurrency').textContent = this.settings.defaultCurrency + 
                (this.settings.defaultCurrency === 'USD' ? ' ($)' : 
                 this.settings.defaultCurrency === 'CAD' ? ' (C$)' :
                 this.settings.defaultCurrency === 'EUR' ? ' (€)' : ' (£)');
            document.getElementById('settingDecimals').textContent = this.settings.decimalPlaces;
            
            // Format all currency displays
            this.formatAllCurrencyDisplays();
        }
        
        formatCurrency(amount) {
            const formatter = new Intl.NumberFormat('en-US', {
                style: 'currency',
                currency: this.settings.defaultCurrency,
                minimumFractionDigits: this.settings.decimalPlaces,
                maximumFractionDigits: this.settings.decimalPlaces
            });
            
            // Handle currency symbols for non-USD
            let formatted = formatter.format(Math.abs(amount));
            if (this.settings.defaultCurrency === 'EUR') {
                formatted = '€' + formatted.replace('€', '');
            } else if (this.settings.defaultCurrency === 'GBP') {
                formatted = '£' + formatted.replace('£', '');
            } else if (this.settings.defaultCurrency === 'CAD') {
                formatted = 'C$' + formatted.replace('C$', '');
            }
            
            return amount < 0 ? '-' + formatted : formatted;
        }
        
        formatAllCurrencyDisplays() {
            // Format all elements with data-currency attribute
            document.querySelectorAll('[data-currency]').forEach(el => {
                const value = parseFloat(el.textContent.replace(/[^0-9.-]+/g, ''));
                if (!isNaN(value)) {
                    el.textContent = this.formatCurrency(value);
                }
            });
        }
        
        setupEventListeners() {
            // Theme toggle
            document.getElementById('themeToggle').addEventListener('click', () => this.toggleTheme());
            
            // Navigation
            document.querySelectorAll('.nav-item').forEach(item => {
                item.addEventListener('click', (e) => {
                    e.preventDefault();
                    const tab = item.getAttribute('data-tab');
                    this.switchTab(tab);
                });
            });
            
            // Header buttons
            document.getElementById('saveBtn').addEventListener('click', () => this.saveCalculation());
            document.getElementById('exportBtn').addEventListener('click', () => this.exportData());
            document.getElementById('printBtn').addEventListener('click', () => window.print());
            
            // Loan calculator
            document.getElementById('calculateBtn').addEventListener('click', () => this.calculateLoan());
            document.getElementById('resetBtn').addEventListener('click', () => this.resetLoanCalculator());
            
            // TVM calculator
            document.getElementById('calculatePV').addEventListener('click', () => this.calculateTVM('pv'));
            document.getElementById('calculateFV').addEventListener('click', () => this.calculateTVM('fv'));
            
            // Investment calculator
            document.getElementById('calculateInvestment').addEventListener('click', () => this.calculateInvestment());
            
            // Auto loan calculator
            document.getElementById('calculateAuto').addEventListener('click', () => this.calculateAutoLoan());
            
            // Settings
            document.getElementById('saveSettings').addEventListener('click', () => this.saveSettingsToStorage());
            document.getElementById('resetSettings').addEventListener('click', () => this.resetSettings());
            
            // Auto-calculate on input change
            if (this.settings.autoCalculate) {
                this.setupAutoCalculate();
            }
        }
        
        setupAutoCalculate() {
            // Loan calculator inputs
            const loanInputs = ['loanAmount', 'interestRate', 'loanTerm', 'paymentFrequency', 
                               'amortizationMethod', 'extraPayment', 'extraStartMonth'];
            loanInputs.forEach(id => {
                const element = document.getElementById(id);
                if (element) {
                    element.addEventListener('input', () => {
                        if (this.currentTab === 'loan-calc') this.calculateLoan();
                    });
                }
            });
            
            // TVM calculator inputs
            const tvmInputs = ['tvmPV', 'tvmFV', 'tvmRate', 'tvmPeriods', 'tvmPMT', 'tvmType'];
            tvmInputs.forEach(id => {
                const element = document.getElementById(id);
                if (element) {
                    element.addEventListener('input', () => {
                        if (this.currentTab === 'tvm') this.calculateTVM();
                    });
                }
            });
            
            // Investment calculator inputs
            const investInputs = ['investInitial', 'investMonthly', 'investReturn', 'investPeriod'];
            investInputs.forEach(id => {
                const element = document.getElementById(id);
                if (element) {
                    element.addEventListener('input', () => {
                        if (this.currentTab === 'investment') this.calculateInvestment();
                    });
                }
            });
            
            // Auto loan inputs
            const autoInputs = ['autoPrice', 'autoDownPayment', 'autoTradeIn', 'autoTax', 'autoRate', 'autoTerm'];
            autoInputs.forEach(id => {
                const element = document.getElementById(id);
                if (element) {
                    element.addEventListener('input', () => {
                        if (this.currentTab === 'auto-loan') this.calculateAutoLoan();
                    });
                }
            });
        }
        
        toggleTheme() {
            this.theme = this.theme === 'light' ? 'dark' : 'light';
            document.documentElement.setAttribute('data-theme', this.theme);
            localStorage.setItem('theme', this.theme);
            this.updateThemeIcon();
        }
        
        updateThemeIcon() {
            const icon = document.querySelector('#themeToggle i');
            if (this.theme === 'dark') {
                icon.className = 'fas fa-sun';
            } else {
                icon.className = 'fas fa-moon';
            }
        }
        
        switchTab(tabId) {
            // Update active navigation item
            document.querySelectorAll('.nav-item').forEach(item => {
                item.classList.remove('active');
                if (item.getAttribute('data-tab') === tabId) {
                    item.classList.add('active');
                }
            });
            
            // Show active tab content
            document.querySelectorAll('.tab-content').forEach(tab => {
                tab.classList.remove('active');
            });
            
            const activeTab = document.getElementById(`${tabId}-tab`);
            if (activeTab) {
                activeTab.classList.add('active');
            }
            
            this.currentTab = tabId;
            
            // Perform calculation for current tab
            switch(tabId) {
                case 'loan-calc':
                    this.calculateLoan();
                    break;
                case 'tvm':
                    this.calculateTVM();
                    break;
                case 'investment':
                    this.calculateInvestment();
                    break;
                case 'auto-loan':
                    this.calculateAutoLoan();
                    break;
            }
        }
        
        // ============================================
        // LOAN CALCULATOR
        // ============================================
        
        calculateLoan() {
            // Get input values
            const loanAmount = parseFloat(document.getElementById('loanAmount').value) || 0;
            const annualInterestRate = parseFloat(document.getElementById('interestRate').value) || 0;
            const loanTermMonths = parseInt(document.getElementById('loanTerm').value) || 0;
            const paymentFrequency = parseInt(document.getElementById('paymentFrequency').value) || 12;
            const extraPayment = parseFloat(document.getElementById('extraPayment').value) || 0;
            const extraStartMonth = parseInt(document.getElementById('extraStartMonth').value) || 1;
            const startDate = new Date(document.getElementById('startDate').value);
            
            if (loanAmount <= 0 || annualInterestRate <= 0 || loanTermMonths <= 0) {
                this.showError('Please enter valid loan parameters');
                return;
            }
            
            // Calculate monthly interest rate
            const monthlyInterestRate = annualInterestRate / 100 / paymentFrequency;
            
            // Calculate monthly payment using standard formula
            let monthlyPayment = 0;
            if (monthlyInterestRate > 0) {
                monthlyPayment = loanAmount * 
                    (monthlyInterestRate * Math.pow(1 + monthlyInterestRate, loanTermMonths)) / 
                    (Math.pow(1 + monthlyInterestRate, loanTermMonths) - 1);
            } else {
                monthlyPayment = loanAmount / loanTermMonths;
            }
            
            // Generate amortization schedule
            const schedule = this.generateAmortizationSchedule(
                loanAmount, annualInterestRate, loanTermMonths, 
                monthlyPayment, extraPayment, extraStartMonth, startDate
            );
            
            // Calculate totals
            const totalPayment = monthlyPayment * loanTermMonths + (extraPayment * Math.max(0, loanTermMonths - extraStartMonth + 1));
            const totalInterest = schedule.totalInterest;
            const actualTerm = schedule.actualTerm;
            const payoffDate = schedule.payoffDate;
            
            // Update UI
            document.getElementById('monthlyPaymentResult').textContent = this.formatCurrency(monthlyPayment);
            document.getElementById('totalInterestResult').textContent = this.formatCurrency(totalInterest);
            document.getElementById('totalPaymentResult').textContent = this.formatCurrency(totalPayment);
            document.getElementById('payoffDateResult').textContent = payoffDate;
            
            // Update amortization table
            this.updateAmortizationTable(schedule.schedule);
            
            // Update chart
            this.updateLoanChart(loanAmount, totalInterest, schedule.schedule);
            
            // Save to history
            this.addToHistory({
                type: 'loan',
                description: document.getElementById('loanDescription').value,
                amount: loanAmount,
                interestRate: annualInterestRate,
                term: loanTermMonths,
                monthlyPayment: monthlyPayment,
                totalInterest: totalInterest,
                timestamp: new Date().toISOString()
            });
        }
        
        generateAmortizationSchedule(loanAmount, annualRate, termMonths, monthlyPayment, extraPayment, extraStartMonth, startDate) {
            const schedule = [];
            let balance = loanAmount;
            let totalInterest = 0;
            let currentDate = new Date(startDate);
            
            for (let month = 1; month <= termMonths && balance > 0.01; month++) {
                const monthlyRate = annualRate / 100 / 12;
                const interest = balance * monthlyRate;
                let principal = monthlyPayment - interest;
                
                // Apply extra payment if applicable
                if (extraPayment > 0 && month >= extraStartMonth) {
                    principal += extraPayment;
                }
                
                // Adjust principal if it's more than remaining balance
                if (principal > balance) {
                    principal = balance;
                }
                
                balance -= principal;
                totalInterest += interest;
                
                // Format date for display
                const paymentDate = new Date(currentDate);
                const dateStr = paymentDate.toLocaleDateString('en-US', { 
                    month: 'short', 
                    year: 'numeric' 
                });
                
                schedule.push({
                    month: month,
                    date: dateStr,
                    payment: monthlyPayment + (month >= extraStartMonth ? extraPayment : 0),
                    principal: principal,
                    interest: interest,
                    balance: Math.max(0, balance)
                });
                
                // Move to next month
                currentDate.setMonth(currentDate.getMonth() + 1);
            }
            
            const payoffDate = new Date(startDate);
            payoffDate.setMonth(payoffDate.getMonth() + schedule.length - 1);
            
            return {
                schedule: schedule,
                totalInterest: totalInterest,
                actualTerm: schedule.length,
                payoffDate: payoffDate.toLocaleDateString('en-US', { 
                    month: 'long', 
                    year: 'numeric' 
                })
            };
        }
        
        updateAmortizationTable(schedule) {
            const tbody = document.getElementById('amortizationTableBody');
            tbody.innerHTML = '';
            
            // Show only first 12 months and last 12 months for performance
            const displaySchedule = schedule.length <= 24 ? 
                schedule : 
                [...schedule.slice(0, 12), ...schedule.slice(-12)];
            
            displaySchedule.forEach(row => {
                const tr = document.createElement('tr');
                tr.innerHTML = `
                    <td>${row.month}<br><small>${row.date}</small></td>
                    <td>${this.formatCurrency(row.payment)}</td>
                    <td>${this.formatCurrency(row.principal)}</td>
                    <td>${this.formatCurrency(row.interest)}</td>
                    <td>${this.formatCurrency(row.balance)}</td>
                `;
                tbody.appendChild(tr);
            });
            
            // Add summary row if schedule was truncated
            if (schedule.length > 24) {
                const tr = document.createElement('tr');
                tr.className = 'summary-row';
                tr.innerHTML = `
                    <td colspan="5" style="text-align: center; font-style: italic; padding: 20px;">
                        Showing first 12 and last 12 payments of ${schedule.length} total payments
                    </td>
                `;
                tbody.appendChild(tr);
            }
        }
        
        // ============================================
        // TVM CALCULATOR
        // ============================================
        
        calculateTVM(mode) {
            // Get input values
            const pv = parseFloat(document.getElementById('tvmPV').value) || 0;
            const fv = parseFloat(document.getElementById('tvmFV').value) || 0;
            const rate = parseFloat(document.getElementById('tvmRate').value) || 0;
            const periods = parseFloat(document.getElementById('tvmPeriods').value) || 0;
            const pmt = parseFloat(document.getElementById('tvmPMT').value) || 0;
            const type = document.getElementById('tvmType').value;
            
            let resultPV = pv;
            let resultFV = fv;
            
            if (mode === 'pv' || (!mode && pv === 0 && fv !== 0)) {
                // Calculate Present Value
                const ratePerPeriod = rate / 100;
                resultPV = this.calculatePresentValue(fv, ratePerPeriod, periods, pmt, type);
                document.getElementById('tvmPV').value = resultPV.toFixed(2);
            } else if (mode === 'fv' || (!mode && fv === 0 && pv !== 0)) {
                // Calculate Future Value
                const ratePerPeriod = rate / 100;
                resultFV = this.calculateFutureValue(pv, ratePerPeriod, periods, pmt, type);
                document.getElementById('tvmFV').value = resultFV.toFixed(2);
            }
            
            // Update display
            document.getElementById('tvmResultPV').textContent = this.formatCurrency(resultPV);
            document.getElementById('tvmResultFV').textContent = this.formatCurrency(resultFV);
            
            // Update chart
            this.updateTVMChart(pv, resultFV, periods, rate);
            
            // Save to history
            this.addToHistory({
                type: 'tvm',
                presentValue: resultPV,
                futureValue: resultFV,
                rate: rate,
                periods: periods,
                payment: pmt,
                timestamp: new Date().toISOString()
            });
        }
        
        calculatePresentValue(fv, rate, periods, pmt = 0, type = 'end') {
            if (rate === 0) {
                return -fv - (pmt * periods);
            }
            
            const typeFactor = type === 'beginning' ? (1 + rate) : 1;
            let pv = 0;
            
            if (pmt !== 0) {
                pv = pmt * typeFactor * (1 - Math.pow(1 + rate, -periods)) / rate;
            }
            
            if (fv !== 0) {
                pv += fv / Math.pow(1 + rate, periods);
            }
            
            return -pv;
        }
        
        calculateFutureValue(pv, rate, periods, pmt = 0, type = 'end') {
            if (rate === 0) {
                return -pv - (pmt * periods);
            }
            
            const typeFactor = type === 'beginning' ? (1 + rate) : 1;
            let fv = 0;
            
            if (pmt !== 0) {
                fv = pmt * typeFactor * (Math.pow(1 + rate, periods) - 1) / rate;
            }
            
            if (pv !== 0) {
                fv += -pv * Math.pow(1 + rate, periods);
            }
            
            return -fv;
        }
        
        // ============================================
        // INVESTMENT CALCULATOR
        // ============================================
        
        calculateInvestment() {
            // Get input values
            const initial = parseFloat(document.getElementById('investInitial').value) || 0;
            const monthly = parseFloat(document.getElementById('investMonthly').value) || 0;
            const annualReturn = parseFloat(document.getElementById('investReturn').value) || 0;
            const years = parseFloat(document.getElementById('investPeriod').value) || 0;
            
            if (years <= 0) {
                this.showError('Please enter a valid investment period');
                return;
            }
            
            // Calculate future value
            const monthlyRate = annualReturn / 100 / 12;
            const months = years * 12;
            
            let futureValue = initial * Math.pow(1 + monthlyRate, months);
            
            if (monthly > 0) {
                futureValue += monthly * (Math.pow(1 + monthlyRate, months) - 1) / monthlyRate;
            }
            
            // Calculate totals
            const totalContributions = initial + (monthly * months);
            const investmentEarnings = futureValue - totalContributions;
            
            // Update UI
            document.getElementById('investResultFV').textContent = this.formatCurrency(futureValue);
            document.getElementById('investContributions').textContent = this.formatCurrency(totalContributions);
            document.getElementById('investEarnings').textContent = this.formatCurrency(investmentEarnings);
            
            // Update chart
            this.updateInvestmentChart(initial, monthly, futureValue, years, annualReturn);
            
            // Save to history
            this.addToHistory({
                type: 'investment',
                initial: initial,
                monthly: monthly,
                annualReturn: annualReturn,
                years: years,
                futureValue: futureValue,
                earnings: investmentEarnings,
                timestamp: new Date().toISOString()
            });
        }
        
        // ============================================
        // AUTO LOAN CALCULATOR
        // ============================================
        
        calculateAutoLoan() {
            // Get input values
            const price = parseFloat(document.getElementById('autoPrice').value) || 0;
            const downPayment = parseFloat(document.getElementById('autoDownPayment').value) || 0;
            const tradeIn = parseFloat(document.getElementById('autoTradeIn').value) || 0;
            const taxRate = parseFloat(document.getElementById('autoTax').value) || 0;
            const interestRate = parseFloat(document.getElementById('autoRate').value) || 0;
            const termMonths = parseInt(document.getElementById('autoTerm').value) || 0;
            
            if (price <= 0 || termMonths <= 0) {
                this.showError('Please enter valid vehicle price and loan term');
                return;
            }
            
            // Calculate loan amount
            const taxableAmount = price - tradeIn;
            const taxAmount = taxableAmount * (taxRate / 100);
            const totalPrice = price + taxAmount;
            const loanAmount = totalPrice - downPayment;
            
            // Calculate monthly payment
            const monthlyRate = interestRate / 100 / 12;
            let monthlyPayment = 0;
            
            if (monthlyRate > 0) {
                monthlyPayment = loanAmount * 
                    (monthlyRate * Math.pow(1 + monthlyRate, termMonths)) / 
                    (Math.pow(1 + monthlyRate, termMonths) - 1);
            } else {
                monthlyPayment = loanAmount / termMonths;
            }
            
            // Calculate totals
            const totalInterest = (monthlyPayment * termMonths) - loanAmount;
            const totalCost = totalPrice + totalInterest;
            
            // Update UI
            document.getElementById('autoPayment').textContent = this.formatCurrency(monthlyPayment);
            document.getElementById('autoLoanAmount').textContent = this.formatCurrency(loanAmount);
            document.getElementById('autoInterest').textContent = this.formatCurrency(totalInterest);
            
            // Update chart
            this.updateAutoLoanChart(loanAmount, totalInterest, totalCost);
            
            // Save to history
            this.addToHistory({
                type: 'auto-loan',
                description: document.getElementById('autoDescription').value,
                price: price,
                downPayment: downPayment,
                loanAmount: loanAmount,
                interestRate: interestRate,
                term: termMonths,
                monthlyPayment: monthlyPayment,
                totalInterest: totalInterest,
                timestamp: new Date().toISOString()
            });
        }
        
        // ============================================
        // CHART FUNCTIONS
        // ============================================
        
        initializeCharts() {
            // Initialize chart contexts
            this.charts.paymentChart = this.createChart('paymentChart', 'doughnut', 
                ['Principal', 'Interest'], 
                [25000, 3722], 
                ['#3b82f6', '#8b5cf6']
            );
            
            this.charts.tvmChart = this.createChart('tvmChart', 'line', [], [], []);
            this.charts.investmentChart = this.createChart('investmentChart', 'line', [], [], []);
            this.charts.autoChart = this.createChart('autoChart', 'doughnut', [], [], []);
        }
        
        createChart(canvasId, type, labels, data, colors) {
            const ctx = document.getElementById(canvasId).getContext('2d');
            
            const chart = new Chart(ctx, {
                type: type,
                data: {
                    labels: labels,
                    datasets: [{
                        data: data,
                        backgroundColor: colors,
                        borderColor: colors.map(c => c.replace('0.8', '1')),
                        borderWidth: 1
                    }]
                },
                options: {
                    responsive: true,
                    maintainAspectRatio: false,
                    plugins: {
                        legend: {
                            position: 'top',
                            labels: {
                                color: getComputedStyle(document.documentElement).getPropertyValue('--dark')
                            }
                        }
                    }
                }
            });
            
            return chart;
        }
        
        updateLoanChart(principal, interest, schedule) {
            if (this.charts.paymentChart) {
                this.charts.paymentChart.data.datasets[0].data = [principal, interest];
                this.charts.paymentChart.data.labels = ['Principal', 'Interest'];
                this.charts.paymentChart.data.datasets[0].backgroundColor = ['#3b82f6', '#8b5cf6'];
                this.charts.paymentChart.update();
            }
        }
        
        updateTVMChart(pv, fv, periods, rate) {
            // Create line chart showing growth over time
            const labels = [];
            const data = [];
            
            for (let i = 0; i <= periods; i += Math.max(1, Math.floor(periods / 10))) {
                labels.push(`Year ${i}`);
                const value = this.calculateFutureValue(pv, rate / 100, i, 0, 'end');
                data.push(-value);
            }
            
            if (this.charts.tvmChart) {
                this.charts.tvmChart.data.labels = labels;
                this.charts.tvmChart.data.datasets = [{
                    label: 'Investment Growth',
                    data: data,
                    borderColor: '#10b981',
                    backgroundColor: 'rgba(16, 185, 129, 0.1)',
                    fill: true,
                    tension: 0.4
                }];
                this.charts.tvmChart.update();
            }
        }
        
        updateInvestmentChart(initial, monthly, fv, years, rate) {
            // Create projection chart
            const labels = [];
            const data = [];
            let balance = initial;
            
            for (let year = 0; year <= years; year++) {
                labels.push(`Year ${year}`);
                data.push(balance);
                
                // Project to next year
                if (year < years) {
                    balance = this.calculateFutureValue(balance, rate / 100, 1, monthly * 12, 'end');
                    balance = -balance;
                }
            }
            
            if (this.charts.investmentChart) {
                this.charts.investmentChart.data.labels = labels;
                this.charts.investmentChart.data.datasets = [{
                    label: 'Portfolio Value',
                    data: data,
                    borderColor: '#f59e0b',
                    backgroundColor: 'rgba(245, 158, 11, 0.1)',
                    fill: true,
                    tension: 0.4
                }];
                this.charts.investmentChart.update();
            }
        }
        
        updateAutoLoanChart(loanAmount, totalInterest, totalCost) {
            if (this.charts.autoChart) {
                this.charts.autoChart.data.datasets[0].data = [loanAmount, totalInterest];
                this.charts.autoChart.data.labels = ['Loan Amount', 'Total Interest'];
                this.charts.autoChart.data.datasets[0].backgroundColor = ['#ef4444', '#f59e0b'];
                this.charts.autoChart.update();
            }
        }
        
        // ============================================
        // UTILITY FUNCTIONS
        // ============================================
        
        resetLoanCalculator() {
            document.getElementById('loanAmount').value = '25000';
            document.getElementById('interestRate').value = '5.5';
            document.getElementById('loanTerm').value = '60';
            document.getElementById('extraPayment').value = '0';
            document.getElementById('extraStartMonth').value = '1';
            document.getElementById('loanDescription').value = 'Car loan for new vehicle purchase';
            
            this.calculateLoan();
        }
        
        saveSettingsToStorage() {
            this.settings.autoCalculate = document.getElementById('autoCalculate').checked;
            this.settings.saveHistory = document.getElementById('saveHistory').checked;
            this.settings.defaultCurrency = document.getElementById('defaultCurrency').value;
            this.settings.decimalPlaces = parseInt(document.getElementById('decimalPlaces').value);
            
            this.saveSettings();
            this.applySettings();
            
            this.showNotification('Settings saved successfully!');
        }
        
        resetSettings() {
            this.settings = {
                autoCalculate: true,
                saveHistory: true,
                defaultCurrency: 'USD',
                decimalPlaces: 2
            };
            
            this.applySettings();
            this.saveSettings();
            
            this.showNotification('Settings reset to defaults');
        }
        
        saveCalculation() {
            const calculation = {
                type: this.currentTab,
                timestamp: new Date().toISOString(),
                data: this.getCurrentCalculationData()
            };
            
            this.addToHistory(calculation);
            this.showNotification('Calculation saved to history');
        }
        
        getCurrentCalculationData() {
            switch(this.currentTab) {
                case 'loan-calc':
                    return {
                        loanAmount: document.getElementById('loanAmount').value,
                        interestRate: document.getElementById('interestRate').value,
                        loanTerm: document.getElementById('loanTerm').value,
                        monthlyPayment: document.getElementById('monthlyPaymentResult').textContent
                    };
                case 'tvm':
                    return {
                        presentValue: document.getElementById('tvmResultPV').textContent,
                        futureValue: document.getElementById('tvmResultFV').textContent
                    };
                case 'investment':
                    return {
                        futureValue: document.getElementById('investResultFV').textContent,
                        contributions: document.getElementById('investContributions').textContent,
                        earnings: document.getElementById('investEarnings').textContent
                    };
                case 'auto-loan':
                    return {
                        monthlyPayment: document.getElementById('autoPayment').textContent,
                        loanAmount: document.getElementById('autoLoanAmount').textContent,
                        totalInterest: document.getElementById('autoInterest').textContent
                    };
                default:
                    return {};
            }
        }
        
        addToHistory(calculation) {
            if (this.settings.saveHistory) {
                this.calculationHistory.unshift(calculation);
                
                // Keep only last 50 calculations
                if (this.calculationHistory.length > 50) {
                    this.calculationHistory = this.calculationHistory.slice(0, 50);
                }
                
                this.saveSettings();
            }
        }
        
        exportData() {
            const data = {
                settings: this.settings,
                history: this.calculationHistory,
                exportDate: new Date().toISOString()
            };
            
            const blob = new Blob([JSON.stringify(data, null, 2)], { type: 'application/json' });
            const url = URL.createObjectURL(blob);
            const a = document.createElement('a');
            a.href = url;
            a.download = `financial-suite-export-${new Date().toISOString().split('T')[0]}.json`;
            document.body.appendChild(a);
            a.click();
            document.body.removeChild(a);
            URL.revokeObjectURL(url);
            
            this.showNotification('Data exported successfully!');
        }
        
        showNotification(message) {
            // Remove existing notification
            const existing = document.querySelector('.notification');
            if (existing) existing.remove();
            
            // Create new notification
            const notification = document.createElement('div');
            notification.className = 'notification';
            notification.innerHTML = `
                <div style="position: fixed; top: 20px; right: 20px; background: var(--success); 
                    color: white; padding: 12px 20px; border-radius: 8px; 
                    box-shadow: 0 4px 12px rgba(0,0,0,0.15); z-index: 1000; 
                    animation: slideIn 0.3s ease-out;">
                    <i class="fas fa-check-circle" style="margin-right: 8px;"></i>
                    ${message}
                </div>
            `;
            
            document.body.appendChild(notification);
            
            // Auto-remove after 3 seconds
            setTimeout(() => {
                if (notification.parentNode) {
                    notification.remove();
                }
            }, 3000);
        }
        
        showError(message) {
            // Remove existing notification
            const existing = document.querySelector('.notification');
            if (existing) existing.remove();
            
            // Create error notification
            const notification = document.createElement('div');
            notification.className = 'notification';
            notification.innerHTML = `
                <div style="position: fixed; top: 20px; right: 20px; background: var(--danger); 
                    color: white; padding: 12px 20px; border-radius: 8px; 
                    box-shadow: 0 4px 12px rgba(0,0,0,0.15); z-index: 1000; 
                    animation: slideIn 0.3s ease-out;">
                    <i class="fas fa-exclamation-triangle" style="margin-right: 8px;"></i>
                    ${message}
                </div>
            `;
            
            document.body.appendChild(notification);
            
            // Auto-remove after 3 seconds
            setTimeout(() => {
                if (notification.parentNode) {
                    notification.remove();
                }
            }, 3000);
        }
        
        initialCalculation() {
            // Perform initial calculations
            this.calculateLoan();
            this.calculateTVM();
            this.calculateInvestment();
            this.calculateAutoLoan();
        }
    }

    // Add CSS animation for notifications
    const style = document.createElement('style');
    style.textContent = `
        @keyframes slideIn {
            from {
                transform: translateX(100%);
                opacity: 0;
            }
            to {
                transform: translateX(0);
                opacity: 1;
            }
        }
    `;
    document.head.appendChild(style);

    // Initialize the application when DOM is loaded
    document.addEventListener('DOMContentLoaded', () => {
        window.financialCalculator = new FinancialCalculatorSuite();
    });

</script>