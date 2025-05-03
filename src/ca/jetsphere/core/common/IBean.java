package ca.jetsphere.core.common;

import ca.jetsphere.core.jdbc.JDBC;

import java.sql.Timestamp;

/**
 *
 */

public interface IBean
{
    /**
     *
     */

    public void delete ( JDBC jdbc ) throws Exception;

    public int getId();

    public void setId ( int id );

    public String getUuid();

    public void setUuid ( String uuid );

    public Timestamp getLastUpdate();

    public void setLastUpdate ( Timestamp ts );

    public Timestamp getCreated();

    public void setCreated ( Timestamp ts );

    public void save ( JDBC jdbc ) throws Exception;

}
