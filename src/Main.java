import java.util.*;

interface PaymentMethod {
    void pay(double amount);
}

class CashPayment implements PaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.println("Thanh toán tiền mặt thành công. Số tiền: " + amount + " VNĐ");
    }
}

class CreditCardPayment implements PaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.println("Thanh toán bằng thẻ tín dụng thành công. Số tiền: " + amount + " VNĐ");
    }
}

class MomoPayment implements PaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.println("Thanh toán qua ví Momo thành công. Số tiền: " + amount + " VNĐ");
    }
}

class Product {
    private String name;
    private double price;
    private String category;

    public Product(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public double getPrice() { return price; }
    public String getName() { return name; }
}

class Order {
    private String customerName;
    private List<Product> products;
    private PaymentMethod paymentMethod;

    public Order(String customerName) {
        this.customerName = customerName;
        this.products = new ArrayList<>();
    }

    public void addProduct(Product p) {
        products.add(p);
    }

    public double getTotal() {
        double sum = 0;
        for (Product p : products) sum += p.getPrice();
        return sum;
    }

    public void setPaymentMethod(PaymentMethod method) {
        this.paymentMethod = method;
    }

    public void checkout() {
        double total = getTotal();
        System.out.println("Khách hàng: " + customerName + ". Tổng tiền: " + total + " VNĐ.");
        if (paymentMethod != null)
            paymentMethod.pay(total);
        else
            System.out.println("Chưa chọn phương thức thanh toán!");
    }
}

public class Main {
    public static void main(String[] args) {
        Product p1 = new Product("Áo thun", 150000, "Thời trang");
        Product p2 = new Product("Quần jean", 350000, "Thời trang");

        Order order1 = new Order("Nguyễn Văn A");
        order1.addProduct(p1);
        order1.addProduct(p2);
        order1.setPaymentMethod(new CashPayment());
        order1.checkout();

        Order order2 = new Order("Nguyễn Văn B");
        order2.addProduct(new Product("Giày thể thao", 500000, "Phụ kiện"));
        order2.setPaymentMethod(new CreditCardPayment());
        order2.checkout();

        order2.setPaymentMethod(new MomoPayment());
        order2.checkout();
    }
}
