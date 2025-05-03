package ca.jetsphere.core.spreadsheet;

import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.common.DockYard;
import jxl.WorkbookSettings;
import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;
import org.apache.struts.util.LabelValueBean;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 */

public class Workbook extends ActionForm
{
    jxl.Workbook workbook; WorkbookSettings settings; FormFile formFile; String charset; int selected; List headerRows;


    /**
     *
     */

    public Workbook () { super(); clear(); }

    /**
     *
     */

    public void clear() { close(); workbook = null; setFile ( null ); settings = new WorkbookSettings(); headerRows = new ArrayList(); }

    /**
     *
     */

    public void close() { if ( workbook != null ) workbook.close(); }

    /**
     *
     */

    public String   getCharset  () { return charset   ; }
    public FormFile getFile     () { return formFile  ; }
    public int      getSelected () { return selected  ; }

    /**
     *
     */

    public List getHeaderRows() { return headerRows; }


    /**
     *
     */

    public int getHeaderRow ( int cc ) { return ( ( Integer ) headerRows.get ( cc ) ).intValue(); }

    /**
     *
     */

    public Sheet getSelectedSheet() { return new Sheet ( workbook.getSheet ( getSelected() ) ); }

    /**
     *
     */

    public WorkbookSettings getSettings() { return settings; }

    /**
     *
     */

    public Sheet getSheet ( int cc ) { return new Sheet ( workbook.getSheet ( cc ) ); }

    /**
     *
     */

    public List getSheets ( HttpServletRequest request, boolean blank )
    {
    List list = new ArrayList();

    if ( blank ) list.add ( new LabelValueBean( "", "" ) );

    for ( int cc=0; cc < workbook.getNumberOfSheets(); cc++ )

        list.add ( new LabelValueBean ( workbook.getSheetNames() [ cc ], "" + cc ) );

    return list;
    }

    /**
     *
     */

    public boolean hasFile() { return ! DockYard.isWhiteSpace ( formFile.getFileName() ); }

    /**
     *
     */

    public void parse() { setWorkbook ( getFile() ); }

    /**
     *
     */

    public void setCharset  ( String   charset  ) { this.charset  = charset  ; }
    public void setFile     ( FormFile formFile ) { this.formFile = formFile ; }
    public void setSelected ( int      selected ) { this.selected = selected ; }

    /**
     *
     */

    public void setHeaderRows ( String [] checks )
    {
    if ( checks == null ) return;

    headerRows.clear();

    for ( int cc = 0; cc < checks.length; cc ++ ) headerRows.add ( new Integer ( checks [ cc ] ) );

    sort();
    }

    /**
     *
     */

    public void setWorkbook ( InputStream is )
    {
    try {

        this.workbook = jxl.Workbook.getWorkbook ( is );

    } catch ( Exception e ) { Common.trace ( e ); }

    }

    /**
     *
     */

    public void setWorkbook ( FormFile file )
    {
    try {

        setFile ( file ); setWorkbook ( file.getInputStream() );

    } catch ( Exception e ) { Common.trace ( e ); }

    }

    /**
     *
     */

    public void sort() { Collections.sort ( headerRows ); }

    /**
     *
     */

    public void translate() { getSettings().setEncoding ( getCharset() ); }

}
