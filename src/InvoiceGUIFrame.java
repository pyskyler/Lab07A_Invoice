import javax.swing.*;
import java.awt.*;

public class InvoiceGUIFrame extends JFrame {

    InvoiceModel invoice = new InvoiceModel();
    JTextArea invoiceTextArea;
    JTextField titleTextField;
    JTextArea addressTextArea;
    JTextField itemNameTextField;
    JTextField itemPriceTextField;
    JTextField itemQuantityTextField;

    public InvoiceGUIFrame() {
        setTitle("Invoice Creator");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new FlowLayout());

        JPanel buildInvoicePanel = BuildInvoicePanel();
        add(buildInvoicePanel, BorderLayout.CENTER);

        JPanel invoicePanel = invoicePanel();
        add(invoicePanel, BorderLayout.SOUTH);
    }

    private JPanel invoicePanel() {
        JPanel invoicePanel = new JPanel();
        invoicePanel.setLayout(new BoxLayout(invoicePanel, BoxLayout.Y_AXIS));

        invoiceTextArea = new JTextArea(25, 55);
        invoiceTextArea.setEditable(false);
        invoiceTextArea.setFont(new Font("Menlo", Font.PLAIN, 12));

        invoicePanel.add(invoiceTextArea);

        return invoicePanel;
    }

    private JPanel BuildInvoicePanel() {
        JPanel buildInvoicePanel = new JPanel();
        buildInvoicePanel.setLayout(new BoxLayout(buildInvoicePanel, BoxLayout.Y_AXIS));

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));
        JLabel titleLabel = new JLabel("Title: ");
        titleTextField = new JTextField(20);
        titlePanel.add(titleLabel);
        titlePanel.add(titleTextField);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        buildInvoicePanel.add(titlePanel);

        JPanel addressPanel = new JPanel();
        addressPanel.setLayout(new BoxLayout(addressPanel, BoxLayout.X_AXIS));
        JLabel addressLabel = new JLabel("Address: ");
        addressTextArea = new JTextArea(3, 20);
        addressPanel.add(addressLabel);
        addressPanel.add(addressTextArea);

        addressPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        buildInvoicePanel.add(addressPanel);

        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.X_AXIS));
        JLabel itemNameLabel = new JLabel("Item Name: ");
        itemNameTextField = new JTextField(20);
        JLabel itemPriceLabel = new JLabel("Price: ");
        itemPriceTextField = new JTextField(5);
        JLabel itemQuantityLabel = new JLabel("Quantity: ");
        itemQuantityTextField = new JTextField(5);

        itemPanel.add(itemNameLabel);
        itemPanel.add(itemNameTextField);
        itemPanel.add(itemPriceLabel);
        itemPanel.add(itemPriceTextField);
        itemPanel.add(itemQuantityLabel);
        itemPanel.add(itemQuantityTextField);

        itemPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        buildInvoicePanel.add(itemPanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        JButton addItemButton = new JButton("Add Item");
        addItemButton.addActionListener(e -> addItem());
        buttonPanel.add(addItemButton);

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> resetInvoice());
        buttonPanel.add(resetButton);

        buttonPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        buildInvoicePanel.add(buttonPanel);

        return buildInvoicePanel;
    }

    private void resetInvoice() {
        titleTextField.setText("");
        addressTextArea.setText("");
        itemNameTextField.setText("");
        itemPriceTextField.setText("");
        itemQuantityTextField.setText("");
        invoiceTextArea.setText("");
        invoice = new InvoiceModel();
    }

    private void addItem() {
        String name = itemNameTextField.getText();
        String price = itemPriceTextField.getText();
        String quantity = itemQuantityTextField.getText();

        String validation = invoice.validate(price, quantity);

        if (!validation.equals(InvoiceModel.VALID_DATA)) {
            JOptionPane.showMessageDialog(null, validation);
            return;
        }

        invoice.addItem(name, Double.parseDouble(price), Integer.parseInt(quantity));

        itemNameTextField.setText("");
        itemPriceTextField.setText("");
        itemQuantityTextField.setText("");

        updateInvoice();
    }

    private void updateInvoice() {
        String title = titleTextField.getText();
        String address = addressTextArea.getText();
        invoice.addTitle(title);
        invoice.addAddress(address);
        invoiceTextArea.setText(invoice.generateInvoice());
    }

}
