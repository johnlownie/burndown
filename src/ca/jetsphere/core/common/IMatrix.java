package ca.jetsphere.core.common;

import ca.jetsphere.core.jdbc.JDBC;

/**
 *
 */

public interface IMatrix extends ISessionObject
{
    /**
     *
     */

    public void clear();

    public void clear ( int rows, int columns );

    public void copy ( JDBC jdbc, IMatrix matrix ) throws Exception;

    public int getColumns();

    public IEntry getEntry ( int row, int column );

    public int getRows();

    public int getContainerId();

    public void nullify();

    public void query ( JDBC jdbc, int rows, int columns );

    public void setEntry ( IEntry entry );

    public void setContainerId ( int parentId );

    public void save ( JDBC jdbc );

}
