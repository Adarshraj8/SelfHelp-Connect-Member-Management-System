<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <jsp:useBean id="sHGDAO" class="max.reg.SHGDAO"/>
    <%@ page import=" max.reg.SHGMemberDATA" %>
    <%@ page import="java.util.List" %>
    <%@ page import=" max.reg.ShgDTO" %>
    <%@ page import=" max.reg.MemberDTO" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
 String shgcode=request.getParameter("shgcode");
SHGMemberDATA shg=sHGDAO.getSHGDetail(shgcode);
ShgDTO p=shg.getShgDTO();
   

%>

<table border="2" bgcolor="yellow" align="center">
<tr><td><input type="text" name="shgcode" value="<%=shgcode%>" size="4"></td></tr>
<tr><td><input type="submit" value="Quick Search"></td></tr>
</table>

<table align="center" border="5">
<tr bgcolor="cyan"><td>SHG Code</td><td>SHG NAME</td><td>Account nUmber</td><td>IFSC</td><td>State Name</td><td>District Name</td></tr>


<tr bgcolor="red"><td><%=p.getShgCode()%></td>
<td><%=p.getGname()%></td>

<td><%=p.getAcc()%></td>
<td><%=p.getIfsc()%></td>
<td><%=p.getStName()%></td>
<td><%=p.getDistrictName()%></td>
</tr>
</table>
<table border="1" bgcolor="green" align="center">
<tr><td colspan="6" bgcolor="yellow"> MEMBER DETAIL</td></tr>

<tr><td>MCode</td><td>Adhar</td><td>Age</td><td>Name</td></tr>
<%
List<MemberDTO> memList=shg.getMemlist();
  for(MemberDTO q:memList)
  {
	   if(q.getCid()==p.getShgCode())
	   {
%>

<tr>
<td><%=q.getMid() %></td>
<td><%=q.getAdhar() %></td>
<td><%=q.getAge() %></td>
<td><%=q.getName()%></td>

</tr>

<%}} %>

</table>

</body>
</html>