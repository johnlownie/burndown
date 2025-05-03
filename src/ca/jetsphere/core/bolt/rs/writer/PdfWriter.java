package ca.jetsphere.core.bolt.rs.writer;

import ca.jetsphere.core.bolt.rs.ResultSetBolt;
import ca.jetsphere.core.bolt.rs.ResultSetBoltMap;
import ca.jetsphere.core.common.DockYard;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;

import java.io.OutputStream;
import java.util.*;

/**
 *
 */

public class PdfWriter
{
    protected Locale                         locale        ;
    protected ResultSetBoltMap               rsbm          ;
    protected String                         title         ;
    protected String[]                       tableHeaders  ;

    protected Document                        document     ;
    protected com.itextpdf.text.pdf.PdfWriter pdfWriter    ;
    protected PdfPTable                       datatable    ;
    protected PageHeaderFooter                headerFooter ;
    public Font                               tableHeaderFont, tableContentFont, documentFont;

    /**
     *
     */

    public PdfWriter ( Locale locale, ResultSetBoltMap rsbm ) { this.locale = locale; this.rsbm = rsbm; clear (); }

    /**
     *
     */

    public void clear ()
    {
    tableHeaderFont  = FontFactory.getFont ( FontFactory.HELVETICA, 12, Font.BOLD   );

    tableContentFont = FontFactory.getFont ( FontFactory.HELVETICA, 10, Font.NORMAL );

    documentFont     = FontFactory.getFont ( FontFactory.HELVETICA, 10, Font.NORMAL );

    headerFooter = new PageHeaderFooter();
    }

    /**
     *
     */

    public void addTableRow ( Map data )
    {
    for ( int i = 0; i < tableHeaders.length; i++ )

        datatable.addCell ( new Phrase ( ( String ) data.get ( tableHeaders[ i ] ), tableContentFont ) );
    }

    /**
     *
     */

    public void closeDocument () { document.close (); }

    /**
     *
     */

    public void closeTable () throws DocumentException

    { document.add ( datatable ); datatable = null; }

    /**
     *
     */

    public void openDocument ( OutputStream os ) throws DocumentException
    {
    document = new Document ( PageSize.LETTER.rotate (), 36, 36, 72, 36 );

    pdfWriter = com.itextpdf.text.pdf.PdfWriter.getInstance ( document, os );

    pdfWriter.setPageEvent ( headerFooter );

    document.open ();
    }

    /**
     *
     */

    public void openTable ( String[] captions )
    {
    datatable = new PdfPTable ( captions.length );

    tableHeaders = captions;

    renderTableHeaders ( captions );
    }

    /**
     *
     */

    public void print ( OutputStream os ) throws Exception
    {
    openDocument ( os );

    printTitle();

    java.util.List list = DockYard.toList ( rsbm.getCaptions() );

    String[] tmpCaptions = ( String[] ) list.toArray ( new String[list.size()] );

    openTable ( tmpCaptions );

    Iterator it = rsbm.iterator ( true );

    while ( it.hasNext() )

    { ResultSetBolt bolt = ( ResultSetBolt ) it.next(); addTableRow ( bolt.getMap() ); }

    closeTable ();

    closeDocument ();
    }

    /**
     *
     */

    public void printTitle ( ) throws DocumentException
    {
    Paragraph p = new Paragraph ( title );

    p.setAlignment ( Paragraph.ALIGN_CENTER );

    document.add ( p );

    document.add ( new Paragraph( " " ) );
    }

    /**
     *
     */

    protected void renderTableHeaders ( String[] captions )
    {
    datatable.setWidthPercentage ( 100 );

    datatable.getDefaultCell ().setPadding ( 3 );

    datatable.getDefaultCell ().setBorderWidth ( 1 );

    datatable.getDefaultCell ().setHorizontalAlignment ( Element.ALIGN_LEFT );

    for ( int i = 0; i < captions.length; i++ )

        datatable.addCell ( new Phrase ( captions[ i ], tableHeaderFont ) );

    datatable.setHeaderRows ( 1 );
    }

    /**
     *
     */

    public void setTitle ( String title ) { this.title = title; headerFooter.setTitle ( title ); }

}
