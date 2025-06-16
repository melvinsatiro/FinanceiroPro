<?php include("includes/conn.php"); ?>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet"  href="css/_estilo3.css"/>
    <style>
        h1 {font-family: Arial;
            font-size: 20pt;
            color:white;
            text-shadow: 1px 1px 1px yellowgreen;
        
        }
        body {
        background-image: url("imagens/akatsuki3.jpg");
    }
        img.img-dir {
            display: block;
            float:right;
            margin-left: 600px;
            margin-top: -200px;
            padding: 0pt;
        }    
    </style>

    <title>Site php</title>

</head>
<script src="javascript/funcoes.js"> 
  
</script>
<body>
<div id="interface"> 

    <header id="cabecalho">
        
    <hgroup>
         <h1> VIGIAVLS_backups
       
        </h1>
    
    <section id="corpo">
    <p> <span>Rapberrypi</span> </p>
    <h2> 
        "Backups "    </h2>
    </section>
    <h3>
    <table id="tabela1">
        <caption>Tabela De IDs<span>Nov/2024</span></caption>
	<tr><td class="ce"></td><td class="cd">	<?php
        $servername = "localhost:3306";
        $username = "root";
        $password = "";
        $dbname = "arduino";

        $conn = new mysqli($servername, $username, $password, $dbname);

        if ($conn->connect_error) {
        die("Conexão falhou: " . $conn->connect_error);
        }

        $sql = "SELECT * FROM sensores";
        $result = $conn->query($sql);

        if ($result->num_rows > 0) {
         while($row = $result->fetch_assoc()) {
                echo $row["id"]. " - " . $row["nome"]. " - ".  $row["valor"]. " - ". $row["data"]. "<br>";
        }
        } else {
                echo "0 resultados";
        }

        $conn->close();
?>
 </td></tr>
       
    </table>
    </h3>
 
    <footer id="rodape"></footer>
    <p> Copyright &copy; 2024 - Melvin Satiro </p>
    </footer>
    <img id="icone" src="imagens/dashboard.png"class="img-dir"/>
    <nav id="menu"> 
    <aside id="lateral">
    <h1>Menu Principal</h1>
    <ul type="disc">
       <li onmouseover="mudaFoto('imagens/home.png')"onmouseout="mudaFoto('imagens/dashboard.png')"><a href="index.php">HOME</a></li>
       <li onmouseover="mudaFoto('imagens/sistema.png')"onmouseout="mudaFoto('imagens/dashboard.png')"><a href="sistema.php">SISTEMA</a></li>
    </aside> 
    </ul> 
    </nav>
