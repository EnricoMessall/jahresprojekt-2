package de.hhbk.jahresprojekt.database.repositories;

import de.hhbk.jahresprojekt.database.Repository;
import de.hhbk.jahresprojekt.model.Tenant;

public class TenantRepository extends Repository<Tenant> {
    public TenantRepository() {
        super(Tenant.class);
    }
}
