package de.hhbk.jahresprojekt.database;

import de.hhbk.jahresprojekt.model.Address;

public class AddressRepository extends Repository<Address> {

    public AddressRepository() {
        super(Address.class);
    }
}
