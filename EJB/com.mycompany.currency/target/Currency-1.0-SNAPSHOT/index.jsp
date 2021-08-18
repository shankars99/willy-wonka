<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Convertor</title>
    </head>
    <body>

        <% out.print("<h1>Converter</h1>");%>
        <form method='POST' action="ConvertNumbers">
            Amount : <input type=text value="0.0" name='val_1'><br>
            <input type="radio" id="USD" name="op" value="USD">
            <label for="USD">USD</label><br>

            <input type="radio" id="EUR" name="op" value="EUR">
            <label for="EUR">EUR</label><br>

            <input type="radio" id="YEN" name="op" value="YEN">
            <label for="YEN">YEN</label><br>

            <input type='submit' value="Convert">
        </form>
    </body>
</html>