<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="ca.jetsphere.core.bolt.rs.ResultSetBolt" %>
<%@ page import="ca.jetsphere.core.bolt.rs.ResultSetBoltMap" %>
<%@ page import="ca.jetsphere.core.tier2.table.TableWriter" %>
<%@ page import="ca.jetsphere.core.common.DockYard" %>

<% String query = ( String ) DockYard.getAttribute ( request, "query", false ); if ( query == null ) query = ""; %>

<div id="page-content"><div class="panel">

<div class="panel-heading"><h3 class="panel-title"><bean:message key="read.only.query"/></h3></div>

<html:form action="/read_only_query" method="post">

<div class="panel-body">

<p><textarea name="query" rows="15" cols="150"><%= query%></textarea></p>

</div>

<div class="panel-footer text-right"><button type="submit" class="btn btn-primary btn-labeled fa fa-question text-uppercase" value="query" name="query"><bean:message key="query"/></button></div>

</html:form>

<!-- -->

<div class="panel mar-top">

<div class="panel-heading"><h3 class="panel-title"><bean:message key="read.only.query.results"/></h3></div>

<div class="panel-body">

<% ResultSetBoltMap rsbm = ( ResultSetBoltMap ) DockYard.getAttribute ( request, ResultSetBolt.key(), false ); if ( rsbm != null ) new TableWriter ( rsbm, rsbm.getCaptions(), rsbm.getFields() ).print ( out, request ); %>

</div>

</div>

</div></div>
