<?php include("includes/conn.php"); ?>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet"  href="css/_estilo2.css"/>
    <meta http-equiv="refresh" content='5'>
	<style>
        h1 {font-family: Arial;
            font-size: 30pt;
            color:white;
            text-shadow: 1px 1px 1px black;
        
	}
	/* Plano de fundo*/

        body {
	background-image: url("imagens/eletronic.jpg");
	background-position: center;
	background-attachment: fixed;
	background-size: cover;
	background-repeat: no-repeat;
	
    }
	
	    img.img-dir {
            display: block;
            float:right;
            margin-left:600px;
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
         <h1>
         Dados do Sistema! 
        
                </h1>
    <font color="yellow">
    <section id="corpo">	
    <p> <span>Rapberrypi</span> </p>
    </font>
     <h2> 
        Servidor
    </h2>
    </section>
     <font color="white"> 
    <h3>

    <table id="tabela1">
    
        <caption>Informações do Servidor<span>2025</span></caption>
        <tr><td><?php
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
                echo "CPU " . " = ".  $row["valor"]. "  ºC ". $row["data"]. "<br>";
	 }
	} else {
                echo "0 resultados";
	}
        $conn->close();
?>
<?php
        $servername = "localhost:3306";
        $username = "root";
        $password = "";
        $dbname = "arduino";

        $conn = new mysqli($servername, $username, $password, $dbname);

        if ($conn->connect_error) {
        die("Conexão falhou: " . $conn->connect_error);
        }

        $sql = "SELECT * FROM armazenamento";
        $result = $conn->query($sql);

        if ($result->num_rows > 0) {
         while($row = $result->fetch_assoc()) {
                echo $row["nome_disco"] . " = ". " utilizados ". $row["usado"]. " livres ".  $row["livre"]. "<br>";
         }
        } else {
                echo "0 resultados";
        }
        $conn->close();
?>


</td></tr>
    </table>
    </h3>
    </font>	
    
    
    <footer id="rodape"></footer>
    <font color="white">
    <p> Copyright &copy; 2025 - Melvin Satiro </p>
    </font>
   


    </footer>
    <img id="icone" src="imagens/sistema.png"class="img-dir"/>
    <nav id="menu"> 
    <aside id="lateral">
    <h1>Menu Principal</h1>
    <ul type="disc">
       <li onmouseover="mudaFoto('imagens/home.png')"onmouseout="mudaFoto('imagens/sistema.png')"><a href="index.php">home</a></li>
       <li onmouseover="mudaFoto('imagens/dashboard.png')"onmouseout="mudaFoto('imagens/sistema.png')"><a href="Dashboard_de_Monitoramento.php">dashboard</a></li>
    </aside> 
    </ul> 
    </nav>
    </header>
