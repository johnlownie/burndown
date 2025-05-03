package ca.jetsphere.core.common;

import java.sql.Timestamp;

/**
 *
 */

public interface IEntry extends IBean
{
    /**
     *
     */

    public int       getId          ();
    public String    getUuid        ();
    public int       getContainerId ();
    public int       getTypeId      ();
    public int       getRow         ();
    public int       getColumn      ();
    public String    getRowUuid     ();
    public String    getColumnUuid  ();
    public int       getMemberId    ();
    public Timestamp getEvent       ();
    public int       getWidgetId    ();
    public String    getWidgetValue ();
    public String    getName        ();
    public Timestamp getLastUpdate  ();
    public Timestamp getCreated     ();

    /**
     *
     */

    public void setId          ( int       id           );
    public void setUuid        ( String    uuid         );
    public void setContainerId ( int       container_id );
    public void setTypeId      ( int       type_id      );
    public void setRow         ( int       row          );
    public void setColumn      ( int       column       );
    public void setRowUuid     ( String    row_uuid     );
    public void setColumnUuid  ( String    column_uuid  );
    public void setMemberId    ( int       member_id    );
    public void setEvent       ( Timestamp event        );
    public void setWidgetId    ( int       widget_id    );
    public void setWidgetValue ( String    widget_value );
    public void setName        ( String    name         );
    public void setLastUpdate  ( Timestamp last_update  );
    public void setCreated     ( Timestamp created      );

    public void icopy ( IEntry entry );
    public boolean isValid();

}
