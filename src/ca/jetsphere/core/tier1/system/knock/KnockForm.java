package ca.jetsphere.core.tier1.system.knock;

import org.apache.struts.action.ActionForm;

/**
 *
 */

public class KnockForm extends ActionForm
{
    String knock_key   ;
    String knock_value ;

    /**
     *
     */

    public void clear()
    {
    setKey   ( "" );
    setValue ( "" );
    }

    /**
     *
     */

    public String getKey  () { return knock_key   ; }
    public String getValue() { return knock_value ; }

    /**
     *
     */

    public void setKey   ( String knock_key   ) { this.knock_key   = knock_key   ; }
    public void setValue ( String knock_value ) { this.knock_value = knock_value ; }

}
