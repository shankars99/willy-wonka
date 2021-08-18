<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body> <center>
        <%
            int sub1=Integer.parseInt(request.getParameter("sub1"));
            int sub2=Integer.parseInt(request.getParameter("sub2"));
            int sub3=Integer.parseInt(request.getParameter("sub3"));
            int sub4=Integer.parseInt(request.getParameter("sub4"));
            int sub5=Integer.parseInt(request.getParameter("sub5"));
            int sub6=Integer.parseInt(request.getParameter("sub6"));

            out.println("Subject 1 - "  + getGrade(sub1));
            out.println("<br>Subject 2 - "  + getGrade(sub2));
            out.println("<br>Subject 3 - "  + getGrade(sub3));
            out.println("<br>Subject 4 - "  + getGrade(sub4));
            out.println("<br>Subject 5 - "  + getGrade(sub5));
            out.println("<br>Subject 6 - "  + getGrade(sub6));
            %>
            <%!
            public String getGrade(int mark){
               if(mark > 90 ){
                  return("O");
               }else if (mark >= 80) {
                  return("A+");
               }else if (mark >= 70) {
                  return("A");
               } else if (mark >= 60) {
                  return("B+");
               } else if(mark >= 50){
                  return("B");
               } else{
                  return("RA");
               }
            }


        %>
    </center></body>
</html>