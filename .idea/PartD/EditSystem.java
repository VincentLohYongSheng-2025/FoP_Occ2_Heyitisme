import java.util.Scanner;

public class EditSystem
{

    public static SaleRecord EditStockInfo(SaleRecord saleRecord)
    {
        Scanner input = new Scanner(System.in);

        System.out.println("=== Edit Stock Information === ");
        System.out.print("Enter Model Name: ");
        String editModelName = input.nextLine();

        for (ItemPurchased item : saleRecord.item)
        {
            // FIX: Changed 'searchModelName' to the declared variable 'editModelName'.
            if (item.model.equalsIgnoreCase(editModelName))
            {
                // A null check for safety
                if (item != null && item.model.equalsIgnoreCase(editModelName))
                {
                    System.out.println("Current Stock: " + item.quantity);
                    System.out.print("Enter New Stock: ");
                    int newStock = input.nextInt();
                    item.quantity = newStock;
                    return saleRecord;
                }
            }
        }
        // Added a return statement here for the case where the item is not found
        return saleRecord;
    }

    public static SaleRecord EditSalesInfo(SaleRecord saleRecord)
    {
        Scanner input = new Scanner(System.in);

        System.out.println("=== Edit Sales Information === ");
        System.out.print("Enter Transaction Date: ");
        String editTransacDate = input.nextLine();
        System.out.print("Enter Customer Name: ");
        String editCustomerName = input.nextLine();

        //add logic to detect with records
        System.out.println("Sales Record Found:");
        // FIX: Accessing the first item in the list using .get(0)
        System.out.println("Model: " + saleRecord.item.get(0).model + "   Quantity: " + saleRecord.item.get(0).quantity );
        System.out.println("Total: RM" + (saleRecord.item.get(0).price*saleRecord.item.get(0).quantity));
        System.out.println("Transaction Method: "+ saleRecord.paymentMethod);

        System.out.println("Select number to edit:");
        System.out.println("1.Name 2.Model 3.Quantity 4.Total 5.Transaction Method");
        System.out.print(">");
        int choice = input.nextInt();
        input.nextLine(); // Consume the newline character

        switch (choice)
        {
            case 1 -> {
                System.out.print("Enter New Name: ");
                String newName = input.nextLine();
                System.out.print("Confirm Update? (Y/N): ");
                if (input.nextLine().equalsIgnoreCase("Y")) {
                    saleRecord.customerName = newName;
                }
            }
            case 2 -> {
                System.out.print("Enter New Model Name: ");
                String newModel = input.nextLine();
                System.out.print("Confirm Update? (Y/N) :");
                if (input.nextLine().equalsIgnoreCase("Y"))
                {
                    // FIX: Accessing the first item in the list using .get(0)
                    saleRecord.item.get(0).model = newModel;
                }
            }
            case 3 -> {
                System.out.print("Enter New Quantity: ");
                int newQuantity = input.nextInt();
                input.nextLine(); // Consume newline
                System.out.print("Confirm Update? (Y/N): ");
                if (input.nextLine().equalsIgnoreCase("Y")) {
                    // FIX: Edit the quantity of the FIRST item in the list.
                    saleRecord.item.get(0).quantity = newQuantity;
                }
            }
            case 4 -> {
                System.out.print("Enter New Total: ");
                double newTotal = input.nextDouble();
                input.nextLine(); // Consume newline
                System.out.print("Confirm Update? (Y/N): ");
                if (input.nextLine().equalsIgnoreCase("Y")) {
                    saleRecord.totalAmount = newTotal;
                }
            }
            case 5 -> {
                System.out.print("Enter New Transaction Method: ");
                String newTransactionMethod = input.nextLine();
                System.out.print("Confirm Update? (Y/N): ");
                if (input.nextLine().equalsIgnoreCase("Y")) {
                    saleRecord.paymentMethod = newTransactionMethod;
                }
            }
        }

        System.out.println("Sales information updated successfully.");
        return saleRecord;
    }
}