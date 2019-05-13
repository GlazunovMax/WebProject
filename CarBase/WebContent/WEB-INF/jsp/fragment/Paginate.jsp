<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%--For displaying Page numbers. 
    The when condition does not display a link for the current page--%>
    <table border="1" bgcolor="gray">
	    
        <tr>
       		 <%--For displaying Previous link except for the 1st page --%>
	        <c:if test="${currentPage != 1}">
	       		<td><a href="Controller?command=${command}&page=${currentPage - 1}&id=${id}">Previous</a></td>
	    	</c:if>
	   
	        <c:forEach begin="1" end="${countRows}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <td>${i}</td>
                    </c:when>
	                    
                    <c:otherwise>
                        <td><a href="Controller?command=${command}&page=${i}&id=${id}">${i}</a></td>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
	            
            <%--For displaying Next link --%>
            <c:if test="${currentPage lt countRows}">
        		<td><a href="Controller?command=${command}&page=${currentPage + 1}&id=${id}">Next</a></td>
  			</c:if>        
        </tr>   
    </table>	

</body>
</html>