package customer;

class Customer extends User {

    public Customer(String id, String password, String name, String gender, String email, String phone, String picture, double balance) {
        super(id, password, name, gender, email, phone, picture, balance);
    }
    @Override
    public String toString() {
        return "Customer{" + super.toString() + "}\n";
    }
    
    
    

}