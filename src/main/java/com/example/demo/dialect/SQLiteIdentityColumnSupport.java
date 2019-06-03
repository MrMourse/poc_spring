package com.example.demo.dialect;

import org.hibernate.dialect.identity.IdentityColumnSupportImpl;

/**
 * Permet le mapping des colonnes pour sqlite.
 */
class SQLiteIdentityColumnSupport extends IdentityColumnSupportImpl {

    @Override
    public boolean supportsIdentityColumns() {
        return true;
    }

    @Override
    public String getIdentitySelectString(String table, String column, int type) {
        return "select last_insert_rowid()";
    }

    @Override
    public String getIdentityColumnString(int type){
        return "integer";
    }
}
