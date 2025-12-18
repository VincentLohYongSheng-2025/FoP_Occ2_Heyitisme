import java.util.Scanner;

public class SearchSystem{

    SalesSystem salesSystem = new SalesSystem();


    public static void searchStockInfo(SaleRecord saleRecord)
    {
        if (saleRecord == null) {
            System.out.println("Cannot search: No sales record provided.");
            return;
        }

        Scanner input = new Scanner(System.in);
        System.out.println("=== Search Stock Information ===");

        boolean found = true;

        while (found)
        {
            System.out.print("Search Model Name: ");
            String searchModelName = input.nextLine();
            System.out.println("Searching...");
            System.out.println("Item not found");
            System.out.println();

            for (ItemPurchased item : saleRecord.item)
            {
                if (item.model.equalsIgnoreCase(searchModelName))
                {
                    // A null check for safety
                    if (item != null && item.model.equalsIgnoreCase(searchModelName))
                    {
                        System.out.println("Model: " + item.model);
                        System.out.println("Unit Price: RM" + item.price);
                        found = false;
                    }
                }
            }
        }
    }

    public static void searchSalesInfo(SaleRecord saleRecord)
    {
        if (saleRecord == null) {
            System.out.println("Cannot search: No sales record provided.");
            return;
        }

        Scanner input = new Scanner(System.in);
        System.out.println("=== Search Sales Information ===");
        boolean found = true;

        while (found)
        {
            System.out.print("Search keyword: ");
            String searchCustomerName = input.nextLine();
            System.out.println("Searching...");
            System.out.println("Sales record not found");
            System.out.println();

            // FIX: Replaced undefined 'sale' variable with the parameter 'saleRecord'.
            if (saleRecord.customerName.equalsIgnoreCase(searchCustomerName))
            {
                System.out.println("Sales Record Found:");
                // FIX: Replaced 'sale' with 'saleRecord' in all the following lines.
                System.out.println("Date:" + saleRecord.localDateTime.format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "        Time:" + saleRecord.localDateTime.format(java.time.format.DateTimeFormatter.ofPattern("hh:mm a")));
                System.out.println("Customer Name: " + saleRecord.customerName);
                System.out.println("Transaction Method: " + saleRecord.paymentMethod);
                System.out.println("Employee: " + saleRecord.employeeInCharge);
                System.out.println("Status: " + saleRecord.transactionStatus);
                found = false;
            }
        }
    }

    public static void main(String[] args) {
        searchStockInfo(null);

    }


}