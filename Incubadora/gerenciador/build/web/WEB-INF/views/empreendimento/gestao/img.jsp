<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Upload de Imagem</title>
    </head>
    <body>
        <h1>Upload de Imagem</h1>
        <form method="post" action="upload.asp" enctype="multipart/form-data">
            <input type="file" name="logo" /><br/>
            <input type="submit" value="Enviar"/>
        </form>
    </body>
</html>