<%@ page import="java.io.*,java.util.*,java.sql.*" %>
   <%@ page import="javax.servlet.http.*,javax.servlet.*" %>
      <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
         <%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

            <html>

            <head>
               <title>SELECT Operation</title>
            </head>

            <body>
               <sql:setDataSource var="snapshot" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost/test"
                  user="root" password="" />

               <sql:query dataSource="${snapshot}" var="result">
                  SELECT * from marks;
               </sql:query>

               <table border="1" width="100%">
                  <tr>
                     <th>Name</th>
                     <th>Sub1</th>
                     <th>Sub2</th>
                     <th>Sub3</th>
                     <th>Sub4</th>
                  </tr>

                  <c:forEach var="row" items="${result.rows}">
                     <tr>
                        <td>
                            <c:out value="${row.Name}" />
                        </td>
                        <td>
                            <c:out value="${row.Sub1}"/>
                        </td>
                        <td>
                           <c:out value="${row.Sub2}" />
                        </td>
                        <td>
                           <c:out value="${row.Sub3}" />
                        </td>
                        <td>
                           <c:out value="${row.Sub4}" />
                        </td>
                     </tr>
                  </c:forEach>
               </table>

            </body>

            </html>