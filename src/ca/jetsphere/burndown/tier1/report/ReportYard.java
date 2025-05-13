package ca.jetsphere.burndown.tier1.report;

import ca.jetsphere.core.common.DockYard;

import java.util.Map;

/**
 *
 */
public class ReportYard
{
    /**
     *
     */
    static public String getMorrisData ( Map<String, String> map )
    {
    StringBuilder sb = new StringBuilder();

    for ( Map.Entry<String, String> entry : map.entrySet() )
    {
    String key = entry.getKey(); String value = entry.getValue();

    if ( sb.length() > 0 ) sb.append ( ", " ); sb.append ( "{\"elapsed\": \"" + key + "\", \"value\": " + value + "}\n" );
    }

    return "[" + sb.toString() + "]";
    }

    /**
     *
     */
    static public String getPanel ( int numerator, String numText, int denominator, String demText, String icon, String color )
    {
    StringBuilder sb = new StringBuilder();

    int percentage = ( int ) DockYard.toPercent ( numerator, denominator );

    sb.append ( "<div class=\"panel panel-" + color + " panel-colorful\">" );
    sb.append ( "<div class=\"pad-all media\">" );
    sb.append ( "<div class=\"media-left\"><span class=\"icon-wrap icon-wrap-xs\"><i class=\"fa fa-" + icon + " fa-fw fa-2x\"></i></span></div>" );
    sb.append ( "<div class=\"media-body\"><p class=\"h3 text-thin media-heading\">" + numerator + "</p><small class=\"text-uppercase\">" + numText + "</small></div>" );
    sb.append ( "</div>" );
    sb.append ( "<div class=\"progress progress-xs progress-dark-base mar-no\"><div role=\"progressbar\" aria-valuenow=\"" + percentage + " aria-valuemin=\"0\" aria-valuemax=\"100\" class=\"progress-bar progress-bar-light\" style=\"width: " + percentage + "%\"></div></div>" );
    sb.append ( "<div class=\"pad-all text-right\"><small><span class=\"text-semibold\"><i class=\"fa fa-shopping-cart fa-fw\"></i><span class=\"count\">" + denominator + "</span></span> " + demText + "</small></div>" );
    sb.append ( "</div>" );

    return sb.toString();
    }

    /**
     *
     */
    static public String getStyleClass ( int cc )
    {
    switch ( cc )
    {
    case 1 : return "purple";

    case 2 : return "pink";

    case 3 : return "mint";

    case 4 : return "dark";

    case 5 : return "warning";

    case 6 : return "danger";

    case 7 : return "success";

    case 8 : return "info";

    case 9 : return "primary";

    default: return "purple";
    } }
}
