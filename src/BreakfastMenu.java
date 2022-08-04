import java.util.*;
import java.io.*;

public class BreakfastMenu
{
    //************************************************************************************************************************************************ */
    // This Float will calculate the total price of the order from breakfast menu
    // It will then append to the Order Total Price then get set back to 0 incase user come back to this section to order again
    
    //add one more int that counts the total quanity of the order
    public static float BreakfastTotalPrice;
    
    String[] BreakfastReciptItemsList = new String[5];
    private String ReceiptItems(String ItemName, float ItemPrice, int ItemQuantity)
    {
        String ReceiptItem = "";
        ReceiptItem += ItemName + " - " + "$"+ ItemPrice + " - "  + "Q. "+ ItemQuantity + "\n";
        return ReceiptItem;
    }
    //************************************************************************************************************************************************ */






    int [] breakfastItemIndex = new int[5];
    String [] breakfastItemName = new String[5];
    float [] breakfastItemPrice = new float[5];
    String [] breakfastItemDescription = new String[5];
    String [] breakfastItemINFO= new String[5];
    
    public void BreakFastList()
    {
        // Scans the file and stores the data in the arrays
        BreakfastScanner();
        // Prompts the user to select a breakfast item
        BreakFastSelector();
    }

     // Stores all the data in a single pritable format
    private String itemList (int itemIndex, String itemName, Float itemPrice , String itemDescription)
    {
        String item = itemIndex + ". " + itemName + " - " + itemPrice + " - " + itemDescription;
        

        return item;
    }

    // Write a method to read menu from a file and store it in an array
    private void BreakfastScanner()
    {
        String SectionReader = "";
        try
        {
            //Scanner input = new Scanner(new File("."+System.getProperty("path.separator")+"Breakfast.txt"));
            Scanner input = new Scanner(new File("Breakfast_Menu.txt"));
            input.useDelimiter(";");
            
            for (int Line = 0; Line < 5 ; Line++)
            {
                for(int Section = 0; Section < 4; Section++)
                {
                    if (Section == 0)
                    {
                        SectionReader = input.next();
                        //System.out.println("This is Index : "  + SectionReader);
                        breakfastItemIndex[Line] = Integer.parseInt(SectionReader);
                    }
                    else if (Section == 1)
                    {
                        SectionReader = input.next();
                        //System.out.println("This is Name: "  + SectionReader);
                        breakfastItemName[Line] = SectionReader;
                    }
                    else if (Section == 2)
                    {
                        SectionReader = input.next();
                        //System.out.println("This is Price : "  + SectionReader);
                        breakfastItemPrice[Line] = Float.parseFloat(SectionReader);
                    }
                    else if (Section == 3)
                    {
                        SectionReader = input.next();
                        //System.out.println("This is Description : "  + SectionReader);
                        breakfastItemDescription[Line] = SectionReader;
                    }
                    breakfastItemINFO[Line] = itemList(breakfastItemIndex[Line], breakfastItemName[Line], breakfastItemPrice[Line], breakfastItemDescription[Line]);
                }
                input.nextLine();
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File Not Found");
        }

        PrintMenu();
    }

    // Write a method to display the menu
    private void PrintMenu()
    {
        for (int i = 0; i < breakfastItemINFO.length; i++)
        {
            System.out.println(breakfastItemINFO[i]);
        }
        System.out.println("Press 8 : To Return to Category Order");
        System.out.println("Press 9 : To Confirm Order");
        System.out.println("Press 0 : To Exit");
        System.out.println("");
    }

    private void BreakFastSelector()
    {        
        //************************************************************************************************************************************************ */
        int ItemsSlected = 0;
        //************************************************************************************************************************************************ */
        Scanner breakfastReader = new Scanner (System.in);  // Reading from System.in
        System.out.println("Please Enter The Digit that correspondence to what you want: ");
        int b = breakfastReader.nextInt();
       // breakfastReader.close(); // closing reader it will not allow u to read again

        while (b != 0)
        {

            // Exit the Program and Show recipt
            if (b== 9)
            {
                System.out.println("You have selected Place Order and Exit");

                //************************************************************************************************************************************************ */
                for(int  i = 0; i < BreakfastReciptItemsList.length; i++)
                {
                    System.out.println(BreakfastReciptItemsList[i]);
                    System.out.println("");   
                }
                System.out.println("Your Total Price is : " + BreakfastTotalPrice);
                //************************************************************************************************************************************************ */
                breakfastReader.close();
                System.exit(0);

            }
            if (b == 8)
            {
                //************************************************************************************************************************************************ */
                Main main = new Main();
                main.CallCategoryMenu();


                //************************************************************************************************************************************************ */
                // Add That Price Append to the total price
                // Add That Items Added To item ordred
            }
            // Printers whatever the User ask and the quantity of it. Then it asks again
            else if (b >= 1 && b <= 5)
            {
                float selectedItemPrice = 0.0f;
                System.out.println("You have selected " + breakfastItemName[b-1]);
                //option to add  Quantity, you may remove this option if you wanna make it simpler
                System.out.println("Please Enter The Quantity: ");
                int quantity = breakfastReader.nextInt();
                System.out.println("You have selected " + quantity + " " + breakfastItemName[b-1]);
                // Total Price For the Item

                //************************************************************************************************************************************************ */

                selectedItemPrice = breakfastItemPrice[b-1] * quantity;
                BreakfastTotalPrice += selectedItemPrice;
                // Add Items to Receipt Format
                BreakfastReciptItemsList[ItemsSlected] = ReceiptItems(breakfastItemName[b-1], breakfastItemPrice[b-1], quantity);
                ItemsSlected++;
                //************************************************************************************************************************************************ */
                
                // Now it should send the Item name and price to the Receipt
                // And Should send The item price to the Total Price  
                System.out.println("");
            }
            else
            {
                System.out.println("You have selected an invalid option");
            }
            System.out.println("Please Enter The Digit that correspondence to what you want: ");
            b = breakfastReader.nextInt();
        }
        
     //   reader.close();}
    
    }
}