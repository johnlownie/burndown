<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<html:hidden property="edit" styleId="edit" value=""/><html:hidden property="delete" styleId="delete" value=""/><html:hidden property="copy" styleId="copy" value=""/>

<div class="panel-footer text-right"><button type="submit" class="btn btn-primary btn-labeled fa fa-plus text-uppercase addBtn" value="new" name="add"><bean:message key="add.a.row"/></button></div>
