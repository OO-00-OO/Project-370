import java.util.*;

public class Menu
{
    // Receipt
    Receipt receipt = new Receipt();


    //Catagory Classes
    BreakfastMenu breakfast = new BreakfastMenu();
    LunchMenu lunch = new LunchMenu();
    DinnerMenu dinner = new DinnerMenu();
    DrinksMenu drink = new DrinksMenu();
    DessertMenu dessert = new DessertMenu();

    public void WelcomeList()
        {
            categoryList();
            categorySelection();
        }
    
    private void categoryList()
        {
            System.out.println("");
            System.out.println("Welcome to Our Restaurants" );
            System.out.println("");
            System.out.println("1. Breakfast");
            System.out.println("2. Lunch");
            System.out.println("3. Dinner");
            System.out.println("4. Desserts");
            System.out.println("5. Drinks");
            System.out.println("");
            System.out.println("9. To Cancel Order");
            System.out.println("0. To Confirm Your Order");
        }

    private void categorySelection()
    {
        Scanner MenuReader = new Scanner(System.in);  // Reading from System.in
        System.out.print("Please Enter The Digit that correspondence to what you want: ");
        int n = MenuReader.nextInt();
        //MenuReader.close(); 
        while (n != 9)
        {
            if (n == 1)
            {
                System.out.println("You have selected Breakfast");
                System.out.println("");
                breakfast.BreakFastList();
            }
            else if (n == 2)
            {
                System.out.println("You have selected Lunch");
                System.out.println("");
                lunch.LunchList();
            }
            else if (n == 3)
            {
                System.out.println("You have selected Dinner");
                System.out.println("");
                dinner.DinnerList();
            }
            else if(n == 4)
            {
                System.out.println("You have selected Desserts");
                System.out.println("");
                dessert.DessertList();
            }
            else if (n == 5)
            {
                System.out.println("You have selected Drinks");
                System.out.println("");
                drink.DrinksList();
            }
            else if (n == 0)
            {
                break;
            }
            else
            {
                System.out.println("You have selected an invalid option");
                System.out.println("");
            }
                System.out.println("Please Enter The Digit that correspondence to what you want: ");
                n = MenuReader.nextInt();
        }
        if ( n == 0)
        {
            System.out.println("Your Order Has Been Confirmed");
            receipt.PrintReceipt();
        }
        if (n == 9)
        {
            System.out.println("Your Order Has Been Cancelled");
             System.out.println("We Hope You Come Back Soon");

        }
        
    }
 }

