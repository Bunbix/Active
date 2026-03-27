#nullable disable
using System;
using System.Collections.Generic;

namespace BookLibrary
{
    // Interface
    public interface IBook
    {
        string Title { get; set; }
        bool IsBorrowed { get; set; }
        void MarkAsBorrowed();
        void MarkAsReturned();
        string GetLocation();
    }

    // Ebook class
    public class Ebook : IBook
    {
        public string Title { get; set; }
        public bool IsBorrowed { get; set; }
        public string Location { get; set; }

        public Ebook(string title)
        {
            Title = title;
            IsBorrowed = false;
            Location = "Web";
        }

        public void MarkAsBorrowed() => IsBorrowed = true;
        public void MarkAsReturned() => IsBorrowed = false;
        public string GetLocation() => Location;
    }

    // HardCover class
    public class HardCover : IBook
    {
        public string Title { get; set; }
        public bool IsBorrowed { get; set; }
        public string Location { get; set; }

        public HardCover(string title)
        {
            Title = title;
            IsBorrowed = false;
            Location = "Library";
        }

        public void MarkAsBorrowed()
        {
            IsBorrowed = true;
            Location = "Client";
        }

        public void MarkAsReturned()
        {
            IsBorrowed = false;
            Location = "Library";
        }

        public string GetLocation() => Location;
    }

    // AudioBook class
    public class AudioBook : IBook
    {
        public string Title { get; set; }
        public bool IsBorrowed { get; set; }
        public string Location { get; set; }

        public AudioBook(string title)
        {
            Title = title;
            IsBorrowed = false;
            Location = "Web";
        }

        public void MarkAsBorrowed() => IsBorrowed = true;
        public void MarkAsReturned() => IsBorrowed = false;
        public string GetLocation() => Location;
    }

    // Main program
    class Program
    {
        static List<IBook> library = new List<IBook>();

        static void Main(string[] args)
        {
            bool exit = false;

            Console.WriteLine("\n=========================================");
            Console.WriteLine("   WELCOME TO THE BOOK LIBRARY SYSTEM");
            Console.WriteLine("=========================================");

            while (!exit)
            {
                Console.WriteLine("\n=== MAIN MENU ===");
                Console.WriteLine("0 - Exit");
                Console.WriteLine("1 - Add a new book");
                Console.WriteLine("2 - Find a book");
                Console.WriteLine("3 - Borrow a book");
                Console.WriteLine("4 - Return a book");
                Console.WriteLine("=================");
                Console.Write("Please select an option: ");

                string input = Console.ReadLine();

                if (int.TryParse(input, out int choice))
                {
                    switch (choice)
                    {
                        case 0:
                            exit = true;
                            Console.WriteLine("\nThank you for using the Book Library. Goodbye!");
                            break;
                        case 1:
                            AddBook();
                            break;
                        case 2:
                            FindBook();
                            break;
                        case 3:
                            BorrowBook();
                            break;
                        case 4:
                            ReturnBook();
                            break;
                        default:
                            Console.WriteLine("\nThis operation is not supported, please try again");
                            break;
                    }
                }
                else
                {
                    Console.WriteLine("\nThis operation is not supported, please try again");
                }
            }
        }

        static void AddBook()
        {
            Console.WriteLine("\n--- Add a New Book ---");
            Console.WriteLine("Book Types:");
            Console.WriteLine("  1 - Ebook");
            Console.WriteLine("  2 - HardCover");
            Console.WriteLine("  3 - AudioBook");
            Console.Write("Enter book type (1, 2, or 3): ");
            string typeInput = Console.ReadLine();
            
            Console.Write("Enter book title: ");
            string title = Console.ReadLine();

            if (string.IsNullOrWhiteSpace(title))
            {
                Console.WriteLine("Book title cannot be empty!");
                return;
            }

            IBook newBook = null;

            if (int.TryParse(typeInput, out int bookType))
            {
                switch (bookType)
                {
                    case 1:
                        newBook = new Ebook(title);
                        break;
                    case 2:
                        newBook = new HardCover(title);
                        break;
                    case 3:
                        newBook = new AudioBook(title);
                        break;
                    default:
                        Console.WriteLine("Invalid book type! Please enter 1, 2, or 3.");
                        return;
                }

                library.Add(newBook);
                Console.WriteLine($"\n✓ Book '{title}' added successfully!");
                Console.WriteLine($"  Type: {newBook.GetType().Name}");
                Console.WriteLine($"  Location: {newBook.GetLocation()}");
                Console.WriteLine($"  Status: Available");
                Console.WriteLine($"  Total books: {library.Count}");
            }
            else
            {
                Console.WriteLine("Invalid input! Please enter a number (1, 2, or 3).");
            }
        }

        static void FindBook()
        {
            Console.WriteLine("\n--- Find a Book ---");
            Console.Write("Enter book title: ");
            string title = Console.ReadLine();

            if (string.IsNullOrWhiteSpace(title))
            {
                Console.WriteLine("Book title cannot be empty!");
                return;
            }

            IBook foundBook = null;
            foreach (IBook book in library)
            {
                if (book.Title.Equals(title, StringComparison.OrdinalIgnoreCase))
                {
                    foundBook = book;
                    break;
                }
            }

            if (foundBook != null)
            {
                string status = foundBook.IsBorrowed ? "BORROWED" : "AVAILABLE";
                string symbol = foundBook.IsBorrowed ? "🔴" : "🟢";
                Console.WriteLine($"\n{symbol} Book found!");
                Console.WriteLine($"  Title: '{title}'");
                Console.WriteLine($"  Status: {status}");
                Console.WriteLine($"  Location: {foundBook.GetLocation()}");
                Console.WriteLine($"  Type: {foundBook.GetType().Name}");
            }
            else
            {
                Console.WriteLine($"\n✗ The book '{title}' does not exist in the library");
            }
        }

        static void BorrowBook()
        {
            Console.WriteLine("\n--- Borrow a Book ---");
            Console.Write("Enter book title: ");
            string title = Console.ReadLine();

            if (string.IsNullOrWhiteSpace(title))
            {
                Console.WriteLine("Book title cannot be empty!");
                return;
            }

            IBook foundBook = null;
            foreach (IBook book in library)
            {
                if (book.Title.Equals(title, StringComparison.OrdinalIgnoreCase))
                {
                    foundBook = book;
                    break;
                }
            }

            if (foundBook != null)
            {
                if (!foundBook.IsBorrowed)
                {
                    foundBook.MarkAsBorrowed();
                    Console.WriteLine($"\n✓ Successfully borrowed '{title}'!");
                    Console.WriteLine($"  New location: {foundBook.GetLocation()}");
                }
                else
                {
                    Console.WriteLine($"\n✗ Sorry, '{title}' is already borrowed.");
                }
            }
            else
            {
                Console.WriteLine($"\n✗ The book '{title}' does not exist in the library");
            }
        }

        static void ReturnBook()
        {
            Console.WriteLine("\n--- Return a Book ---");
            Console.Write("Enter book title: ");
            string title = Console.ReadLine();

            if (string.IsNullOrWhiteSpace(title))
            {
                Console.WriteLine("Book title cannot be empty!");
                return;
            }

            IBook foundBook = null;
            foreach (IBook book in library)
            {
                if (book.Title.Equals(title, StringComparison.OrdinalIgnoreCase))
                {
                    foundBook = book;
                    break;
                }
            }

            if (foundBook != null)
            {
                if (foundBook.IsBorrowed)
                {
                    foundBook.MarkAsReturned();
                    Console.WriteLine($"\n✓ Successfully returned '{title}'!");
                    Console.WriteLine($"  New location: {foundBook.GetLocation()}");
                }
                else
                {
                    Console.WriteLine($"\n✗ '{title}' is not currently borrowed.");
                }
            }
            else
            {
                Console.WriteLine($"\n✗ The book '{title}' does not exist in the library");
            }
        }
    }
}
