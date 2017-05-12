package be.cheops.axer.complaint;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;

@Component
class PurchaseResolver {

    Collection<Purchase> resolve(String institute, String number) {
        return Collections.singleton(new Purchase());
    }
}
