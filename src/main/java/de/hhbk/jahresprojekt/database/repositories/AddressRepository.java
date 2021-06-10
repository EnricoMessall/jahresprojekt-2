package de.hhbk.jahresprojekt.database.repositories;

import de.hhbk.jahresprojekt.database.Repository;
import de.hhbk.jahresprojekt.model.Address;

public class AddressRepository extends Repository<Address> {

    public AddressRepository() {
        super(Address.class);
    }
}
