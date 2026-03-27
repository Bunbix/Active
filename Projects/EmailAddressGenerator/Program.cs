using System;

namespace EmailAddressGenerator
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("========================================");
            Console.WriteLine("   Robertson College Email Generator");
            Console.WriteLine("========================================");
            Console.WriteLine();

            try
            {
                Console.Write("Please enter your first name: ");
                string firstName = Console.ReadLine();
                
                Console.Write("Please enter your last name: ");
                string lastName = Console.ReadLine();

                if (string.IsNullOrWhiteSpace(firstName) || string.IsNullOrWhiteSpace(lastName))
                {
                    Console.WriteLine("First name and last name cannot be empty.");
                    Console.WriteLine("\nPress any key to exit...");
                    Console.ReadKey();
                    return;
                }

                int birthYear = 0;
                bool validYear = false;

                while (!validYear)
                {
                    Console.Write("Please enter your year of birth (e.g., 1980): ");
                    string yearInput = Console.ReadLine();

                    try
                    {
                        birthYear = int.Parse(yearInput);
                        
                        if (birthYear >= 1900 && birthYear <= DateTime.Now.Year)
                        {
                            validYear = true;
                        }
                        else
                        {
                            Console.WriteLine($"Please enter a year between 1900 and {DateTime.Now.Year}");
                        }
                    }
                    catch (FormatException)
                    {
                        Console.WriteLine("Error: Please enter a valid number.");
                    }
                }

                string firstInitial = firstName[0].ToString().ToLower();
                string lowerLastName = lastName.ToLower();
                string email = $"{firstInitial}{lowerLastName}{birthYear}@robertsoncollege.ca";

                Console.WriteLine();
                Console.WriteLine("========================================");
                Console.WriteLine($"Hello {firstName} {lastName}, welcome to Robertson College!");
                Console.WriteLine($"Your new email address is: {email}");
                Console.WriteLine("========================================");
            }
            catch (Exception ex)
            {
                Console.WriteLine($"Error: {ex.Message}");
            }

            Console.WriteLine();
            Console.WriteLine("Press any key to exit...");
            Console.ReadKey();
        }
    }
}
