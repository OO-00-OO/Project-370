import java.util.*;
import java.io.*;

public class DrinksMenu
{
    Receipt receipt = new Receipt();
    Main main = new Main();
    
    public void DrinksList()
    {
        // Scans the file and stores the data in the arrays
        Drinks_txt_Scanner();
        // Prompts the user to select a Drinks item
        Drinks_Menu_Selector();
    }



    //************************************ READING .TXT FILE AND FORMATING RELATED - START ************************************


    // The Five Variables That the .txt File Will be Read Into
    int [] Drinks_Item_Index = new int[5];
    String [] Drinks_Item_Name = new String[5];
    float [] Drinks_Item_Price = new float[5];
    String [] Drinks_Item_Description = new String[5];
    String [] Drinks_Item_INFO_Combined= new String[5];
    // Write a method to read menu from a file and store it in an array


    // Stores all the data in a single pritable format
     private String Items_INFO_Combined_Format (int itemIndex, String itemName, Float itemPrice , String itemDescription)
    {
        String item = itemIndex + ". " + itemName + " - " + itemPrice + " - " + itemDescription;
             
     
        return item;
    }

    private void Drinks_txt_Scanner()
    {
        String SectionReader = "";
        try
        {
            //Scanner input = new Scanner(new File("."+System.getProperty("path.separator")+"Drinks.txt"));
            Scanner input = new Scanner(new File("Drinks_Menu.txt"));
            input.useDelimiter(";");
            
            for (int Line = 0; Line < 5 ; Line++)
            {
                for(int Section = 0; Section < 4; Section++)
                {
                    if (Section == 0)
                    {
                        SectionReader = input.next();
                        //System.out.println("This is Index : "  + SectionReader);
                        Drinks_Item_Index[Line] = Integer.parseInt(SectionReader);
                    }
                    else if (Section == 1)
                    {
                        SectionReader = input.next();
                        //System.out.println("This is Name: "  + SectionReader);
                        Drinks_Item_Name[Line] = SectionReader;
                    }
                    else if (Section == 2)
                    {
                        SectionReader = input.next();
                        //System.out.println("This is Price : "  + SectionReader);
                        Drinks_Item_Price[Line] = Float.parseFloat(SectionReader);
                    }
                    else if (Section == 3)
                    {
                        SectionReader = input.next();
                        //System.out.println("This is Description : "  + SectionReader);
                        Drinks_Item_Description[Line] = SectionReader;
                    }
                    Drinks_Item_INFO_Combined[Line] = Items_INFO_Combined_Format(Drinks_Item_Index[Line], Drinks_Item_Name[Line], Drinks_Item_Price[Line], Drinks_Item_Description[Line]);
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
        for (int i = 0; i < Drinks_Item_INFO_Combined.length; i++)
        {
            System.out.println(Drinks_Item_INFO_Combined[i]);
        }
        System.out.println("Press 9 : To Return to Category Order Menu");
        System.out.println("Press 0 : To Exit");
        System.out.println("");
    }
    //************************************ READING .TXT FILE AND FORMATING RELATED - END ************************************


    //************************************ RECIPE  RELATED - START ************************************
    // These 3 var will increment based on the Users Order, then Send to the Receipt Class 

    public static float Drinks_TotalPrice = 0.0f;
    public static int Drinks_TotalQuantity = 0;
    String[] Drinks_Recipt_ItemsList = new String[10];


    private String Receipt_Format(String ItemName, float ItemPrice, int ItemQuantity)
    {
        String Receipt_Format_Item = "";
        Receipt_Format_Item += ItemName + " - " + "$"+ ItemPrice + " - "  + "Q. "+ ItemQuantity + "\n";
        return Receipt_Format_Item;
    }



    private void Send_Items_To_Recipt()
    {
        // Check How many items in the recipt array
       int Items_in_Recipt_Counter = receipt.ItemsCountInRecipt();
       int initalCounter = 0;

       //If the item is not in the recipt, add it to the recipt start from 0
       if(Items_in_Recipt_Counter == 0)
       {
        initalCounter = 0;
           while(Drinks_Recipt_ItemsList[initalCounter] != null)
           {
               receipt.ReceiptTotalItems[initalCounter] = Drinks_Recipt_ItemsList[initalCounter];
               initalCounter++;
           }
       }
       // if there are items already on the recipt, add the new item to the next empty slot
       else
       {
            initalCounter = 0;
            while(Drinks_Recipt_ItemsList[initalCounter] != null)
            {
                receipt.ReceiptTotalItems[Items_in_Recipt_Counter] = Drinks_Recipt_ItemsList[initalCounter];
                Items_in_Recipt_Counter++;
                initalCounter++;
            }
       }
    }
    

    //************************************ RECIPE  RELATED - END ************************************


    //************************************ SELECTION MENU - START ************************************
    private void Drinks_Menu_Selector()
    {        
        int ItemsSlected = 0;
        Scanner Drinks_Reader = new Scanner (System.in);  // Reading from System.in
        System.out.print("Please Enter The Digit that correspondence to what you want: ");
        int b = Drinks_Reader.nextInt();

        while (b != 0)
        {

            if (b== 9)
            {
                System.out.println("");
                System.out.println("Returning to Category Order Menu");
                receipt.ReceiptTotalPrice += Drinks_TotalPrice;
                receipt.ReceiptTotalQuantity += Drinks_TotalQuantity;
                Send_Items_To_Recipt();
                main.CallCategoryMenu();

            }
            // Printers whatever the User ask and the quantity of it. Then it asks again
            else if (b >= 1 && b <= 5)
            {

                float selectedItem_Price = 0.0f;
                System.out.println("You have selected " + Drinks_Item_Name[b-1]);
        
                // quantity of the item
                System.out.print("Please Enter The Quantity: ");
                int quantity = Drinks_Reader.nextInt();

                System.out.println("");
                System.out.println("You have selected " + quantity + " " + Drinks_Item_Name[b-1] + "(s)");
                
                // Append Price To Class Total Price
                selectedItem_Price = Drinks_Item_Price[b-1] * quantity;
                Drinks_TotalPrice += selectedItem_Price;
                Drinks_TotalQuantity += quantity;
               
               
                // Add Items to Receipt Format
                Drinks_Recipt_ItemsList[ItemsSlected] = Receipt_Format(Drinks_Item_Name[b-1], Drinks_Item_Price[b-1], quantity);
                ItemsSlected++;

                System.out.println("");
            }
            else
            {
                System.out.println("You have selected an invalid option");
                System.out.println("");
            }
            System.out.print("Please Enter The Digit that correspondence to what you want: ");
            
            b = Drinks_Reader.nextInt();
        }
     
    }
}