import java.util.*;

public class Menu
{
    BreakfastMenu breakfast = new BreakfastMenu();
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
            System.out.println("4. Drinks");
            System.out.println("");
            System.out.println("0. Exit");
        }

    private void categorySelection()
        {
            Scanner MenuReader = new Scanner(System.in);  // Reading from System.in
            System.out.println("Please Enter The Digit that correspondence to what you want: ");
            int n = MenuReader.nextInt();
            //MenuReader.close(); 

            if (n == 1)
            {
                System.out.println("You have selected Breakfast");
                breakfast.BreakFastList();
            }
            else if (n == 2)
            {
                System.out.println("You have selected Lunch");
            }
            else if (n == 3)
            {
                System.out.println("You have selected Dinner");
            }
            else if (n == 4)
            {
                System.out.println("You have selected Drinks");
            }
            else if (n == 0)
            {
                System.out.println("You have selected Exit");
                System.exit(0);
                MenuReader.close();
            }
            else
            {
                System.out.println("You have selected an invalid option");
            }
        }







}
