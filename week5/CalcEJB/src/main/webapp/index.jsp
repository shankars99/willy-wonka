<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Calculator</title>
    </head>
    <body>

        <% out.print("<h1>Calculator</h1>");%>
        <form method='POST' action="CalcNumbers">
            Val 1 : <input type=text value="0.0" name='val_1'><br>
            Val 2 : <input type=text value="0.0" name='val_2'><br>

            <input type="radio" id="add" name="op" value="add">
            <label for="add">Add</label><br>

            <input type="radio" id="sub" name="op" value="sub">
            <label for="sub">Sub</label><br>

            <input type="radio" id="mul" name="op" value="mul">
            <label for="mul">Mul</label><br>

            <input type="radio" id="div" name="op" value="div">
            <label for="div">Div</label><br>

            <input type='submit' value="Calculate">
        </form>
    </body>
</html>