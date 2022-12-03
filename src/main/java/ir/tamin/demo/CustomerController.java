package ir.tamin.demo;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CustomerController {

    private static List<Customer> customers = new ArrayList<>();

    static{
        customers.add(new Customer(1,"Mohsen","Malakouti",34));
        customers.add(new Customer(2,"Ali","Akbari",30));
        customers.add(new Customer(3,"Ahmad","Borazjani",25));
        customers.add(new Customer(4,"Neda","Azimi",45));
    }

    @GetMapping("/customers")
    public ResponseEntity<?> fetchAllCustomers(){
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<?> fetchCustomerById(@PathVariable int id){
        Customer customer = getCustomerById(id);
        if(customer == null){
            return ResponseEntity.badRequest().body("invalid customer id");
        }else
            return ResponseEntity.ok(customer);

    }


    private Customer getCustomerById(int id){
        for(Customer customer:customers){
            if(customer.getId() == id)
                return customer;
        }
        return null;
    }
}
