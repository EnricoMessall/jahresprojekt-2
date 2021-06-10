package de.hhbk.jahresprojekt.database.repositories;

import de.hhbk.jahresprojekt.database.Repository;
import de.hhbk.jahresprojekt.model.Address;
import de.hhbk.jahresprojekt.model.Item;

public class ItemRepository extends Repository<Item> {

    public ItemRepository() {
        super(Item.class);
    }
}
