import java.util.*;
import java.io.*;

public class DessertMenu
{
    Receipt receipt = new Receipt();
    Main main = new Main();
    
    public void DessertList()
    {
        // Scans the file and stores the data in the arrays
        Dessert_txt_Scanner();
        // Prompts the user to select a Dessert item
        Dessert_Menu_Selector();
    }



    //************************************ READING .TXT FILE AND FORMATING RELATED - START ************************************


    // The Five Variables That the .txt File Will be Read Into
    int [] Dessert_Item_Index = new int[5];
    String [] Dessert_Item_Name = new String[5];
    float [] Dessert_Item_Price = new float[5];
    String [] Dessert_Item_Description = new String[5];
    String [] Dessert_Item_INFO_Combined= new String[5];
    // Write a method to read menu from a file and store it in an array


    // Stores all the data in a single pritable format
     private String Items_INFO_Combined_Format (int itemIndex, String itemName, Float itemPrice , String itemDescription)
    {
        String item = itemIndex + ". " + itemName + " - " + itemPrice + " - " + itemDescription;
             
     
        return item;
    }

    private void Dessert_txt_Scanner()
    {
        String SectionReader = "";
        try
        {
            //Scanner input = new Scanner(new File("."+System.getProperty("path.separator")+"Dessert.txt"));
            Scanner input = new Scanner(new File("Dessert_Menu.txt"));
            input.useDelimiter(";");
            
            for (int Line = 0; Line < 5 ; Line++)
            {
                for(int Section = 0; Section < 4; Section++)
                {
                    if (Section == 0)
                    {
                        SectionReader = input.next();
                        //System.out.println("This is Index : "  + SectionReader);
                        Dessert_Item_Index[Line] = Integer.parseInt(SectionReader);
                    }
                    else if (Section == 1)
                    {
                        SectionReader = input.next();
                        //System.out.println("This is Name: "  + SectionReader);
                        Dessert_Item_Name[Line] = SectionReader;
                    }
                    else if (Section == 2)
                    {
                        SectionReader = input.next();
                        //System.out.println("This is Price : "  + SectionReader);
                        Dessert_Item_Price[Line] = Float.parseFloat(SectionReader);
                    }
                    else if (Section == 3)
                    {
                        SectionReader = input.next();
                        //System.out.println("This is Description : "  + SectionReader);
                        Dessert_Item_Description[Line] = SectionReader;
                    }
                    Dessert_Item_INFO_Combined[Line] = Items_INFO_Combined_Format(Dessert_Item_Index[Line], Dessert_Item_Name[Line], Dessert_Item_Price[Line], Dessert_Item_Description[Line]);
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
        for (int i = 0; i < Dessert_Item_INFO_Combined.length; i++)
        {
            System.out.println(Dessert_Item_INFO_Combined[i]);
        }
        System.out.println("Press 9 : To Return to Category Order Menu");
        System.out.println("Press 0 : To Exit");
        System.out.println("");
    }
    //************************************ READING .TXT FILE AND FORMATING RELATED - END ************************************


    //************************************ RECIPE  RELATED - START ************************************
    // These 3 var will increment based on the Users Order, then Send to the Receipt Class 

    public static float Dessert_TotalPrice = 0.0f;
    public static int Dessert_TotalQuantity = 0;
    String[] Dessert_Recipt_ItemsList = new String[10];


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
           while(Dessert_Recipt_ItemsList[initalCounter] != null)
           {
               receipt.ReceiptTotalItems[initalCounter] = Dessert_Recipt_ItemsList[initalCounter];
               initalCounter++;
           }
       }
       // if there are items already on the recipt, add the new item to the next empty slot
       else
       {
            initalCounter = 0;
            while(Dessert_Recipt_ItemsList[initalCounter] != null)
            {
                receipt.ReceiptTotalItems[Items_in_Recipt_Counter] = Dessert_Recipt_ItemsList[initalCounter];
                Items_in_Recipt_Counter++;
                initalCounter++;
            }
       }
    }
    

    //************************************ RECIPE  RELATED - END ************************************


    //************************************ SELECTION MENU - START ************************************
    private void Dessert_Menu_Selector()
    {        
        int ItemsSlected = 0;
        Scanner Dessert_Reader = new Scanner (System.in);  // Reading from System.in
        System.out.print("Please Enter The Digit that correspondence to what you want: ");
        int b = Dessert_Reader.nextInt();

        while (b != 0)
        {

            if (b== 9)
            {
                System.out.println("");
                System.out.println("Returning to Category Order Menu");
                receipt.ReceiptTotalPrice += Dessert_TotalPrice;
                receipt.ReceiptTotalQuantity += Dessert_TotalQuantity;
                Send_Items_To_Recipt();
                main.CallCategoryMenu();

            }
            // Printers whatever the User ask and the quantity of it. Then it asks again
            else if (b >= 1 && b <= 5)
            {

                float selectedItem_Price = 0.0f;
                System.out.println("You have selected " + Dessert_Item_Name[b-1]);
        
                // quantity of the item
                System.out.print("Please Enter The Quantity: ");
                int quantity = Dessert_Reader.nextInt();

                System.out.println("");
                System.out.println("You have selected " + quantity + " " + Dessert_Item_Name[b-1] + "(s)");
                
                // Append Price To Class Total Price
                selectedItem_Price = Dessert_Item_Price[b-1] * quantity;
                Dessert_TotalPrice += selectedItem_Price;
                Dessert_TotalQuantity += quantity;
               
               
                // Add Items to Receipt Format
                Dessert_Recipt_ItemsList[ItemsSlected] = Receipt_Format(Dessert_Item_Name[b-1], Dessert_Item_Price[b-1], quantity);
                ItemsSlected++;

                System.out.println("");
            }
            else
            {
                System.out.println("You have selected an invalid option");
                System.out.println("");
            }
            System.out.print("Please Enter The Digit that correspondence to what you want: ");
            b = Dessert_Reader.nextInt();
        }
     
    }
}