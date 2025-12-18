import java.util.*;


public class EditSystem
{
    public void EditStockInfo(List<SaleRecord> saleRecords)
    {
        Scanner input = new Scanner(System.in);
        System.out.println("=== Edit Item Quantity in a Sale ===");
        System.out.print("Enter Customer Name to find the sale: ");
        String customerName = input.nextLine();
        System.out.print("Enter Model Name to edit: ");
        String editModelName = input.nextLine();

        boolean recordFound = false;
        for (SaleRecord saleRecord : saleRecords) {
            if (saleRecord.customerName.equalsIgnoreCase(customerName)) {
                recordFound = true;
                for (ItemPurchased item : saleRecord.item) {
                    if (item.model.equalsIgnoreCase(editModelName)) {
                        System.out.println("Current Quantity for " + item.model + " in this sale: " + item.quantity);
                        System.out.print("Enter New Quantity: ");
                        int newStock = input.nextInt();
                        input.nextLine(); // Consume newline

                        System.out.print("Confirm Update? (Y/N): ");
                        if (input.nextLine().equalsIgnoreCase("Y")) {
                            item.quantity = newStock;
                            System.out.println("Quantity updated successfully in the record.");
                            // TODO: You would need to re-save the entire sales list to a file here.
                        }
                        return; // Exit after finding and attempting update
                    }
                }
            }
        }
        if (!recordFound) {
            System.out.println("No sales record found for that customer.");
        } else {
            System.out.println("Model not found in that customer's sales record.");
        }
    }

    public void EditSalesInfo(List<SaleRecord> saleRecords)
    {
        if (saleRecords == null || saleRecords.isEmpty()) {
            System.out.println("Cannot edit: No sales records provided.");
            return;
        }
        Scanner input = new Scanner(System.in);

        System.out.println("=== Edit Sales Information === ");
        System.out.print("Enter Customer Name to find record: ");
        String editCustomerName = input.nextLine();

        SaleRecord recordToEdit = null;
        for (SaleRecord saleRecord : saleRecords) {
            if (saleRecord.customerName.equalsIgnoreCase(editCustomerName)) {
                recordToEdit = saleRecord;
                break;
            }
        }

        if (recordToEdit == null) {
            System.out.println("Sales Record Not Found.");
            return;
        }

        System.out.println("Sales Record Found:");
        // Assuming you want to display the first item's details for editing
        ItemPurchased firstItem = recordToEdit.item.get(0);

        System.out.println("Model: " + firstItem.model + "   Quantity: " + firstItem.quantity);
        System.out.println("Total: RM" + recordToEdit.totalAmount);
        System.out.println("Transaction Method: " + recordToEdit.paymentMethod);

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
                    // FIX: Use the correct variable 'recordToEdit'.
                    recordToEdit.customerName = newName;
                }
            }
            case 2 -> {
                System.out.print("Enter New Model Name: ");
                String newModel = input.nextLine();
                System.out.print("Confirm Update? (Y/N) :");
                if (input.nextLine().equalsIgnoreCase("Y"))
                {
                    // FIX: Use 'recordToEdit' and access the first item in the list.
                    recordToEdit.item.get(0).model = newModel;
                }
            }
            case 3 -> {
                System.out.print("Enter New Quantity: ");
                int newQuantity = input.nextInt();
                input.nextLine(); // Consume newline
                System.out.print("Confirm Update? (Y/N): ");
                if (input.nextLine().equalsIgnoreCase("Y")) {
                    // FIX: Use 'recordToEdit' and access the first item in the list.
                    recordToEdit.item.get(0).quantity = newQuantity;
                }
            }
            case 4 -> {
                System.out.print("Enter New Total: ");
                double newTotal = input.nextDouble();
                input.nextLine(); // Consume newline
                System.out.print("Confirm Update? (Y/N): ");
                if (input.nextLine().equalsIgnoreCase("Y")) {
                    recordToEdit.totalAmount = newTotal;
                }
            }
            case 5 -> {
                System.out.print("Enter New Transaction Method: ");
                String newTransactionMethod = input.nextLine();
                System.out.print("Confirm Update? (Y/N): ");
                if (input.nextLine().equalsIgnoreCase("Y")) {
                    recordToEdit.paymentMethod = newTransactionMethod;
                }
            }
        }

        System.out.println("Sales information updated successfully.");
    }
}