package customer;

public class Payment {
    private String hallType;
    private String hallName;
    private String startDate;
    private String endDate;
    private double totalPrice;
    private Customer customer;

    public Payment(String hallType, String hallName, String startDate, String endDate, double totalPrice, Customer customer) {
        this.hallType = hallType;
        this.hallName = hallName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalPrice = totalPrice;
        this.customer = customer;
    }

    public String getHallType() {
        return hallType;
    }

    public void setHallType(String hallType) {
        this.hallType = hallType;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    @Override
    public String toString() {
        return "Payment{" +
                "hallType='" + hallType + '\'' +
                ", hallName='" + hallName + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", totalPrice=" + totalPrice +
                ", customer=" + customer +
                '}';
    }
}
