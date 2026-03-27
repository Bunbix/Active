using System;

namespace CalculatorLibrary
{
    public class CalculatorOperations
    {
        public double Add(double a, double b)
        {
            return a + b;
        }

        public double Subtract(double a, double b)
        {
            return a - b;
        }

        public double Multiply(double a, double b)
        {
            return a * b;
        }

        public double Divide(double a, double b)
        {
            if (b == 0)
            {
                throw new DivideByZeroException("Cannot divide by zero!");
            }
            return a / b;
        }

        public double Power(double baseNumber, int exponent)
        {
            if (exponent < 0)
            {
                throw new ArgumentException("Exponent must be non-negative for this implementation");
            }

            if (exponent == 0)
            {
                return 1;
            }

            double result = baseNumber;
            
            for (int i = 1; i < exponent; i++)
            {
                result = Multiply(result, baseNumber);
            }
            
            return result;
        }

        public double Power(double baseNumber, double exponent)
        {
            return Math.Pow(baseNumber, exponent);
        }
    }
}