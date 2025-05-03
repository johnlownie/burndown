package ca.jetsphere.core.tier0.common.calendar_event.bean;

import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.common.IBean;

import java.sql.ResultSet;
import java.sql.Timestamp;

/**
 *
 */

abstract public class CalendarEvent extends Bolt implements IBean
{
    int       id              ;
    String    title           ;
    String    start           ;
    String    end             ;
    String    url             ;
    String    className       ;
    boolean   editable        ;
    String    rendering       ;
    boolean   overlap         ;
    String    backgroundColor ;
    String    borderColor     ;
    String    textColor       ;
    String    description     ;

    Timestamp last_update     ;
    Timestamp created         ;

    /**
     *
     */

    public void clear()
    {
    setId              (  -1   );
    setTitle           (  ""   );
    setStart           (  ""   );
    setEnd             (  ""   );
    setUrl             (  ""   );
    setClassName       (  ""   );
    setEditable        ( false );
    setRendering       (  ""   );
    setOverlap         ( false );
    setBackgroundColor (  ""   );
    setBorderColor     (  ""   );
    setTextColor       (  ""   );
    setDescription     (  ""   );

    setLastUpdate      ( null  );
    setCreated         ( null  );
    }

    /**
     *
     */

    public void get ( ResultSet rs ) throws Exception {}

    /**
     *
     */

    public int       getId              () { return id              ; }
    public String    getTitle           () { return title           ; }
    public String    getStart           () { return start           ; }
    public String    getEnd             () { return end             ; }
    public String    getUrl             () { return url             ; }
    public String    getClassName       () { return className       ; }
    public boolean   getEditable        () { return editable        ; }
    public String    getRendering       () { return rendering       ; }
    public boolean   getOverlap         () { return overlap         ; }
    public String    getBackgroundColor () { return backgroundColor ; }
    public String    getBorderColor     () { return borderColor     ; }
    public String    getTextColor       () { return textColor       ; }
    public String    getDescription     () { return description     ; }

    public Timestamp getLastUpdate      () { return last_update     ; }
    public Timestamp getCreated         () { return created         ; }

    /**
     *
     */

    static public String key() { return "calendar_event"; }

    /**
     *
     */

    public void setId              ( int      id              ) { this.id              = id              ; }
    public void setTitle           ( String   title           ) { this.title           = title           ; }
    public void setStart           ( String   start           ) { this.start           = start           ; }
    public void setEnd             ( String   end             ) { this.end             = end             ; }
    public void setUrl             ( String   url             ) { this.url             = url             ; }
    public void setClassName       ( String   className       ) { this.className       = className       ; }
    public void setEditable        ( boolean  editable        ) { this.editable        = editable        ; }
    public void setRendering       ( String   rendering       ) { this.rendering       = rendering       ; }
    public void setOverlap         ( boolean  overlap         ) { this.overlap         = overlap         ; }
    public void setBackgroundColor ( String   backgroundColor ) { this.backgroundColor = backgroundColor ; }
    public void setBorderColor     ( String   borderColor     ) { this.borderColor     = borderColor     ; }
    public void setTextColor       ( String   textColor       ) { this.textColor       = textColor       ; }
    public void setDescription     ( String   description     ) { this.description     = description     ; }

    public void setLastUpdate      ( Timestamp last_update    ) { this.last_update     = last_update     ; }
    public void setCreated         ( Timestamp created        ) { this.created         = created         ; }
}
