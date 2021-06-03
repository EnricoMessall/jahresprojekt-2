package de.hhbk.jahresprojekt.database;

import de.hhbk.jahresprojekt.model.Tenant;

public class TenantRepository extends Repository<Tenant> {
    public TenantRepository() {
        super(Tenant.class);
    }
}
