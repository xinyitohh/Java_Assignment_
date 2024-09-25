package customer;

class Customer extends User {

    public Customer(String id, String password, String name, String gender, String email, String phone, String picture, double balance) {
        super(id, name, email, phone, gender, picture, password,balance);
    }
    @Override
    public String toString() {
        return "Customer{" + super.toString() + "}\n";
    }
    
    
    

}