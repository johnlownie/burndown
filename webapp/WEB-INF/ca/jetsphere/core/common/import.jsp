<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<html:hidden property="edit" styleId="edit" value=""/><html:hidden property="delete" styleId="delete" value=""/><html:hidden property="copy" styleId="copy" value=""/>

<div class="panel-footer text-right"><button type="submit" class="btn btn-primary btn-labeled fa fa-plus text-uppercase importBtn" value="new" name="import"><bean:message key="import"/></button></div>
