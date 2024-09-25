// New e-commerce platform payment interface
interface NewPaymentSystem {
    boolean processPayment(String paymentDetails, double amount);
    String getPaymentStatus(String transactionId);
}

// Legacy payment system
class LegacyPaymentSystem {
    public int makePayment(double amount, String accountDetails) {
        System.out.println("Processing payment of $" + amount + " using legacy system");
        // Simulate payment processing
        return (int) (Math.random() * 1000000);  // Return a transaction ID
    }

    public boolean isPaymentComplete(int transactionId) {
        System.out.println("Checking payment status for transaction " + transactionId + " using legacy system");
        // Simulate status check
        return Math.random() < 0.8;  // 80% chance of payment being complete
    }
}

// Adapter class
class PaymentSystemAdapter implements NewPaymentSystem {
    private LegacyPaymentSystem legacySystem;

    public PaymentSystemAdapter(LegacyPaymentSystem legacySystem) {
        this.legacySystem = legacySystem;
    }

    @Override
    public boolean processPayment(String paymentDetails, double amount) {
        int transactionId = legacySystem.makePayment(amount, paymentDetails);
        return legacySystem.isPaymentComplete(transactionId);
    }

    @Override
    public String getPaymentStatus(String transactionId) {
        boolean isComplete = legacySystem.isPaymentComplete(Integer.parseInt(transactionId));
        return isComplete ? "Completed" : "Pending";
    }
}

// E-commerce platform using the new payment system interface
class EcommercePlatform {
    private NewPaymentSystem paymentSystem;

    public EcommercePlatform(NewPaymentSystem paymentSystem) {
        this.paymentSystem = paymentSystem;
    }

    public void processOrder(String item, double price, String paymentDetails) {
        System.out.println("Processing order for " + item + " priced at $" + price);
        boolean paymentSuccess = paymentSystem.processPayment(paymentDetails, price);
        if (paymentSuccess) {
            System.out.println("Order processed successfully");
        } else {
            System.out.println("Order processing failed due to payment issues");
        }
    }
}

// Client code
public class EcommerceApp {
    public static void main(String[] args) {
        // Create the legacy payment system
        LegacyPaymentSystem legacySystem = new LegacyPaymentSystem();

        // Create the adapter
        NewPaymentSystem adapter = new PaymentSystemAdapter(legacySystem);

        // Create the e-commerce platform with the adapter
        EcommercePlatform platform = new EcommercePlatform(adapter);

        // Process some orders
        platform.processOrder("Laptop", 999.99, "4111111111111111");
        platform.processOrder("Headphones", 99.99, "5555555555554444");

        // Check payment status
        String transactionId = "123456";
        String status = adapter.getPaymentStatus(transactionId);
        System.out.println("Payment status for transaction " + transactionId + ": " + status);
    }
}