import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections; // Import for Sorting
import java.util.Comparator;  // Import for Custom Sorting logic

public class DashboardGUI extends JFrame implements ActionListener {

    private JTextArea displayArea;
    private JButton loadButton;
    private JButton analyticsButton;
    private JButton sortButton; // <--- NEW BUTTON FOR SORTING
    private JLabel statusLabel;

    public DashboardGUI() {
        super("GoldenHour System - Manager Dashboard");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        
        loadButton = new JButton("Load Data");
        analyticsButton = new JButton("Analytics");
        sortButton = new JButton("Sort by Price (High->Low)"); // <--- NEW

        loadButton.addActionListener(this);
        analyticsButton.addActionListener(this);
        sortButton.addActionListener(this); // <--- Add Listener

        topPanel.add(loadButton);
        topPanel.add(analyticsButton);
        topPanel.add(sortButton); // <--- Add to Panel

        displayArea = new JTextArea();
        displayArea.setEditable(false);

        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 12));

        JScrollPane scrollPane = new JScrollPane(displayArea);

        statusLabel = new JLabel("Status: Ready");

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(statusLabel, BorderLayout.SOUTH);
        
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ArrayList<SalesRecord> records = StorageManager.loadSales();

        if (e.getSource() == loadButton) {
            displaySales(records);
            statusLabel.setText("Status: Data Loaded.");
        } 
        else if (e.getSource() == analyticsButton) {
            // Now this button actually has the 'records' data to work with
            performAnalytics(records);
            statusLabel.setText("Status: Analytics Report Generated.");
        }
        else if (e.getSource() == sortButton) {
            // Sort Logic
            Collections.sort(records, new Comparator<SalesRecord>() {
                @Override
                public int compare(SalesRecord r1, SalesRecord r2) {
                    return Double.compare(r2.getAmount(), r1.getAmount());
                }
            });
            displaySales(records);
            statusLabel.setText("Status: Data Sorted by Price.");
        }
    }

    // Helper method to print the list to the screen
    private void displaySales(ArrayList<SalesRecord> records) {
        displayArea.setText("=== SALES RECORDS ===\n");
        for (SalesRecord record : records) {
            displayArea.append(record.toString() + "\n");
        }
    }

    // (Keep your performAnalytics method here...)
    private void performAnalytics(ArrayList<SalesRecord> records) {
        // Safety check: If no data, stop.
        if (records == null || records.isEmpty()) {
            displayArea.setText("No records found to analyze.");
            return;
        }

        double totalSales = 0;
        double maxSale = 0;
        int count = 0;

        // Calculate
        for (SalesRecord record : records) {
            totalSales += record.getAmount();
            count++;
            if (record.getAmount() > maxSale) {
                maxSale = record.getAmount();
            }
        }

        // FIX: Clear the screen and show the report
        displayArea.setText(""); 
        displayArea.append("=== DAILY ANALYTICS REPORT ===\n\n");
        displayArea.append("Total Transactions : " + count + "\n");
        displayArea.append("Total Revenue      : RM " + String.format("%.2f", totalSales) + "\n");
        displayArea.append("Highest Single Sale: RM " + String.format("%.2f", maxSale) + "\n");
    }

    public static void main(String[] args) {
        new DashboardGUI();
    }
}