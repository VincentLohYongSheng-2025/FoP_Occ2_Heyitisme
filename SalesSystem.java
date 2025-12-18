import java.util.*;
import java.time.LocalDateTime;
import java.io.*;


class ItemPurchased
{
    String model;
    int quantity;
    double price;

    public ItemPurchased(String model, int quantity, double price)
    {
        this.model = model;
        this.quantity = quantity;
        this.price = price;
    }

    public double subtotal(int quantity, double price)
    {
        return (quantity * price);
    }
}


class SaleRecord {
    LocalDateTime localDateTime;
    String customerName;
    List<ItemPurchased> item;
    String paymentMethod;
    double totalAmount;
    String employeeInCharge;
    String transactionStatus;

    public SaleRecord(LocalDateTime localDateTime, String customerName, List<ItemPurchased> item, String paymentMethod, double totalAmount, String employeeInCharge, String transactionStatus) {
        this.localDateTime = localDateTime;
        this.customerName = customerName;
        this.item = item;
        this.paymentMethod = paymentMethod;
        this.totalAmount = totalAmount;
        this.employeeInCharge = employeeInCharge;
        this.transactionStatus = transactionStatus;
    }

    public String toString() {
        String date = localDateTime.format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String time = localDateTime.format(java.time.format.DateTimeFormatter.ofPattern("hh:mm a"));

        StringBuilder receipt = new StringBuilder();
        receipt.append("Date: ").append(date).append(" Time: ").append(time).append("\n");
        receipt.append("Customer: ").append(customerName).append("\n");
        receipt.append("Item(s):\n");
        for (ItemPurchased items : item) {
            receipt.append("  - ").append(items.model).append(" Quantity: ").append(items.quantity).append("\n");
        }
        receipt.append("Total: RM").append(totalAmount).append("\n");
        receipt.append("Transaction Method: ").append(paymentMethod).append("\n");
        receipt.append("Employee: ").append(employeeInCharge).append("\n");
        return receipt.toString();
    }

    public String toSalesCsvString()
    {
        StringBuilder csv = new StringBuilder();
        for (ItemPurchased items : this.item)
        {
            csv.append(this.localDateTime.toString()).append(",");
            csv.append(this.customerName).append(",");
            csv.append(items.model).append(",");
            csv.append(items.quantity).append(",");
            csv.append(this.totalAmount).append("\n");
        }
        return csv.toString();
    }

    public String toModelCsvString()
    {
        StringBuilder csv = new StringBuilder();
        for (ItemPurchased items : this.item)
        {
            csv.append(items.model).append(",");
            csv.append(items.price).append(",");
            csv.append(items.quantity).append("\n");
        }
        return csv.toString();
    }


}

public class SalesSystem
{

    public static String getValidPaymentMethod(String paymentMethod)
    {
        Scanner input = new Scanner(System.in);
         while (!(paymentMethod.equalsIgnoreCase("Credit card") || paymentMethod.equalsIgnoreCase("Cash") || paymentMethod.equalsIgnoreCase("Check")))
         {
             System.out.println("Invalid Payment Method");
             System.out.print("Enter transaction method: ");
             paymentMethod = input.nextLine();

         }
         return paymentMethod;
    }


    public static SaleRecord recordNewSale(Scanner input, String employeeInCharge) throws IOException
    {


        // getting the Data and the Time locally
        LocalDateTime localDateTime = LocalDateTime.now();

        String date = localDateTime.format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String time = localDateTime.format(java.time.format.DateTimeFormatter.ofPattern("hh:mm a"));

        // main function in the record New Sale System
        System.out.println("Date: " + date);
        System.out.println("Time: " + time);
        System.out.print("Customer Name: ");
        String customerName = input.nextLine();
        System.out.println("Item(s) Purchased: ");


        // way to study arraylist first to continue
        List<ItemPurchased> itemPurchased = new ArrayList<>();

        // declare and initialize variables
        double subtotal = 0;
        String moreItems ;

        //loop for items purchased
        do {
            System.out.print("Enter Model: ");
            String model = input.nextLine();

            System.out.print("Enter Quantity: ");
            int quantity = input.nextInt();

            System.out.print("Unit Price: ");
            double price = input.nextDouble();

            input.nextLine();

            itemPurchased.add(new ItemPurchased(model,quantity,price));
            subtotal += (double)(price * quantity);

            System.out.print("Are there more items purchased? (Y/N) : ");
            moreItems = input.nextLine();

        }while(moreItems.equalsIgnoreCase("Y"));

        // payment method

        // Get a valid payment method using the helper function
        System.out.print("Enter transaction method: ");
        String transactionMethod = getValidPaymentMethod(input.nextLine());
        String transactionStatus = "Transaction verified.";


        System.out.println("Subtotal: "+ subtotal);
        System.out.println();

        // To record the Sales, pass the list of items
        SaleRecord newSale = new SaleRecord(localDateTime, customerName, itemPurchased, transactionMethod, subtotal, employeeInCharge,transactionStatus);


        System.out.println("Transaction successful.");


        System.out.println("Sale recorded successfully.");
        saveToSalesCsv(newSale);

        // TODO : to update the model csv
        System.out.println("Model quantities updated successfully.");
        saveToModelCsv(newSale);

        generateReceipt(newSale);


        return newSale;

    }

    public static void generateReceipt(SaleRecord saleRecord) throws IOException
    {
        System.out.println("Receipt generated: sales_" + saleRecord.localDateTime.toLocalDate() + ".txt");
        String filename = "Sales_" + saleRecord.localDateTime.toLocalDate() + ".txt";

        try (FileWriter writer = new FileWriter(filename,true))
        {
            writer.write("============================================== \n");
            writer.write(saleRecord.toString());
            writer.write("============================================== \n");
        }
    }

    public static void saveToSalesCsv(SaleRecord saleRecord)
    {
        String fileName = "sales_data.csv";

        try(FileWriter writer = new FileWriter(fileName,true))
            {
                writer.write(saleRecord.toSalesCsvString());
            } catch (IOException e)
            {
                System.out.println(e.getMessage());
            }

    }

    public static void saveToModelCsv(SaleRecord saleRecord)
    {
        String fileName = "model.csv";

        try(FileWriter writer = new FileWriter(fileName,true))
            {
                writer.write(saleRecord.toModelCsvString());
            }
            catch (IOException e)
            {
                System.out.println(e.getMessage());
            }
    }



    public static void main(String[] args) throws IOException{

        Scanner input = new Scanner(System.in);



        //here to create a searchsystem object for SearchSystem class
        SearchSystem SearchSystem = new SearchSystem();


        SaleRecord saleRecord = recordNewSale(input, /*this employee will import from norman*/"John Doe");


        SearchSystem.searchStockInfo(saleRecord);
        System.out.println();
        SearchSystem.searchSalesInfo(saleRecord);
        System.out.println();

        //here to create a editsystem object for EditSystem class
        EditSystem EditSystem = new EditSystem();
        EditSystem.EditStockInfo(saleRecord);
        System.out.println();
        EditSystem.EditSalesInfo(saleRecord);
        System.out.println();

        SearchSystem.searchStockInfo(saleRecord);
        System.out.println();
        SearchSystem.searchSalesInfo(saleRecord);
        System.out.println();



    }

}
