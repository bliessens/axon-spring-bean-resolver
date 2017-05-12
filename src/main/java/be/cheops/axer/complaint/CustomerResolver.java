package be.cheops.axer.complaint;

import org.springframework.stereotype.Component;

@Component
class CustomerResolver {

    Customer resolve(String institute, String number) {
        return new Customer(institute,number);
    }
}
