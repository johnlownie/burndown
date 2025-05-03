package ca.jetsphere.core.bolt.rs.writer;

import com.itextpdf.text.Document;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;

import java.util.Date;

/**
 *
 */

public class PageHeaderFooter extends PdfPageEventHelper
{
    protected String title;

    /**
     *
     */
    public void onEndPage ( com.itextpdf.text.pdf.PdfWriter writer, Document document )
    {
    PdfPCell cell = null;

    Rectangle page = document.getPageSize();

    PdfPTable head = new PdfPTable ( 3 );

    head.getDefaultCell().setBorder(  0 );

    cell = new PdfPCell ( new Phrase ( "" ) );

    cell.setBorder( 0 );

    cell.setHorizontalAlignment ( PdfPCell.ALIGN_LEFT );

    head.addCell ( cell );

    cell = new PdfPCell ( new Phrase ( title ) );

    cell.setHorizontalAlignment ( PdfPCell.ALIGN_CENTER );

    cell.setBorder ( 0 );

    head.addCell ( cell );

    cell = new PdfPCell ( new Phrase ( new Date().toString() ) );

    cell.setHorizontalAlignment (  PdfPCell.ALIGN_RIGHT );

    cell.setBorder ( 0 );

    head.addCell ( cell );
    }

    /**
     *
     */

    public void setTitle ( String title ) { this.title = title; }

}
