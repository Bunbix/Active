using System;
using CalculatorLibrary;

namespace CalculatorApp
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("=== Simple Calculator ===\n");
            
            bool continueCalculating = true;
            
            while (continueCalculating)
            {
                try
                {
                    OperationType selectedOperation = GetOperationFromUser();
                    
                    double num1 = GetNumberFromUser("Enter the first number: ");
                    double num2 = GetNumberFromUser("Enter the second number: ");
                    
                    PerformCalculation(selectedOperation, num1, num2);
                    
                    continueCalculating = AskToContinue();
                    Console.WriteLine();
                }
                catch (Exception ex)
                {
                    Console.WriteLine($"Error: {ex.Message}");
                    Console.WriteLine("Please try again.\n");
                }
            }
            
            Console.WriteLine("Thank you for using the calculator. Goodbye!");
        }

        static OperationType GetOperationFromUser()
        {
            Console.WriteLine("\nSelect an operation:");
            Console.WriteLine("1 - Add (+)");
            Console.WriteLine("2 - Subtract (-)");
            Console.WriteLine("3 - Multiply (*)");
            Console.WriteLine("4 - Divide (/)");
            Console.WriteLine("5 - Power (^)");
            
            int choice;
            
            while (true)
            {
                Console.Write("Enter your choice (1-5): ");
                string input = Console.ReadLine();
                
                if (int.TryParse(input, out choice) && choice >= 1 && choice <= 5)
                {
                    return (OperationType)choice;
                }
                
                Console.WriteLine("Invalid choice. Please enter a number between 1 and 5.");
            }
        }

        static double GetNumberFromUser(string prompt)
        {
            double number;
            
            while (true)
            {
                Console.Write(prompt);
                string input = Console.ReadLine();
                
                if (double.TryParse(input, out number))
                {
                    return number;
                }
                
                Console.WriteLine("Invalid number. Please try again.");
            }
        }

        static void PerformCalculation(OperationType operation, double num1, double num2)
        {
            CalculatorOperations calculator = new CalculatorOperations();
            double result = 0;
            string operationSymbol = "";
            
            switch (operation)
            {
                case OperationType.Add:
                    result = calculator.Add(num1, num2);
                    operationSymbol = "+";
                    break;
                    
                case OperationType.Subtract:
                    result = calculator.Subtract(num1, num2);
                    operationSymbol = "-";
                    break;
                    
                case OperationType.Multiply:
                    result = calculator.Multiply(num1, num2);
                    operationSymbol = "*";
                    break;
                    
                case OperationType.Divide:
                    result = calculator.Divide(num1, num2);
                    operationSymbol = "/";
                    break;
                    
                case OperationType.Power:
                    if (num2 == (int)num2)
                    {
                        result = calculator.Power(num1, (int)num2);
                    }
                    else
                    {
                        result = calculator.Power(num1, num2);
                    }
                    operationSymbol = "^";
                    break;
            }
            
            Console.WriteLine($"\n{num1} {operationSymbol} {num2} = {result}");
        }

        static bool AskToContinue()
        {
            Console.Write("\nDo you want to perform another calculation? (y/n): ");
            string response = Console.ReadLine().ToLower();
            
            while (response != "y" && response != "n" && response != "yes" && response != "no")
            {
                Console.Write("Please enter 'y' or 'n': ");
                response = Console.ReadLine().ToLower();
            }
            
            return response == "y" || response == "yes";
        }
    }
}
