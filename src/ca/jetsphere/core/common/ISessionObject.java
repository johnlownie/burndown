package ca.jetsphere.core.common;

import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.bolt.BoltMap;

import javax.servlet.http.HttpServletRequest;
import java.util.Comparator;
import java.util.Iterator;

/**
 *
 */

public interface ISessionObject
{
    /**
     *
     */

    public String [] captions();

    public void clearSelected ( HttpServletRequest request );

    public boolean contains ( Bolt bolt );

    public String [] fields();

    public Bolt getBolt ( int id );

    public Bolt getBoltByUuid ( String uuid );

    public BoltMap getBoltMap();

    public String getKey();

    public Bolt getSelected();

    public int getSelectedId();

    public boolean isEmpty();

    public boolean isSelected ( Bolt bolt );

    public Iterator iterator ( boolean sort );

    public Iterator iterator ( Comparator comparator );

    public void setQualifiedSelected ( HttpServletRequest request, int edit );

    public void setQualifiedSelected ( HttpServletRequest request, String edit );

    public void setQualifiedSelected ( HttpServletRequest request, Bolt bolt );

}