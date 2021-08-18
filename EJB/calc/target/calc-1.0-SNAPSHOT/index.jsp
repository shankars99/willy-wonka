<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Calc</title>
    </head>
    <body>
        <form action = "Adder" method = "POST">
            Enter number 1: <input type = "number" name = "val1"><br><br>
            Enter number 2: <input type = "number" name= "val2"><br><br>
            <input type="radio" id="add" name="op" value="add">
            <label for="add">Add</label><br>
            <input type="radio" id="sub" name="op" value="sub">
            <label for="sub">Sub</label><br>
            <input type="radio" id="mul" name="op" value="mul">
            <label for="mul">Mul</label><br>
            <input type="radio" id="div" name="op" value="div">
            <label for="div">Div</label><br>     
            <input type ="submit" value = "Submit">
        </form>
    </body>
</html>
