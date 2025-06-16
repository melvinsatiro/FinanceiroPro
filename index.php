<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/_estilo1.css"/>
    <meta http-equiv="refresh" content='8'/>
    <style>
		/* Formatação = MENU PRINCIPAL*/

		h1 {
			font-family: Arial;
        		font-size: 25pt;
	    		color:white;
        		text-shadow: 1px 1px 1px black;
		}
		
		h2 {
                        font-family: Arial;
                        font-size: 25pt;
                        color:white;
                        text-shadow: 1px 1px 1px black;
                }
		/*Plano de fundo*/

        	body {
			background-image: url("imagens/eletronic1.jpg");
			background-position:center;
			background-attachment: fixed;
			background-repeat: no-repeat;
			background-size:cover;
  		  }
		/*Icones canto superior da tela*/

		img.img-dir {
            		display: block;
            	float:right;
            	margin-left: 200px;
            	margin-top: -600px;
	    	padding: 0pt;	 
        	}    
    </style>
    	<title>Servidor</title>
		<!--Icone da barra do navegador-->	
    	<link rel="shortcut icon" href='imagens/dashboard1.png'>
</head>
<script src="javascript/funcoes.js"> 
  
</script>
<body>
	<div id="interface"> 
         	<h1>
        	<?php  echo"Dados do Servidor! \u{1F30E}";?>
		</h1>
		<h2>
		<?php 
    		
        	$hostname = "localhost:3306";
        	$bancodedados = "arduino";
        	$usuario = "root";
        	$senha = "";

        	$conexao = new mysqli($hostname, $bancodedados, $usuario, $senha);
        	if ($conexao->connect_errno) {
         	echo " falha ao conectar : (" . $conexao->connect_errno ." ) " . $conexao->connect_errno;
         	}
        
        	else  echo "Conectado!";

        	$sql = "SELECT * FROM sensores ORDER BY id DESC";

        	$result = $conexao->query($sql);

        	print_r($result);
        	?>
		</h2>
 		</div>

    		<footer id="rodape">
			<font color=black><p> Copyright &copy; 2025 - Melvin Satiro </p></font>
		</footer>
    <img id="icone" src="imagens/home.png" class="img-dir"/>
    <nav id="menu"> 
    <ul type="disc">
       <li onmouseover="mudaFoto('imagens/sistema.png')"onmouseout="mudaFoto('imagens/home.png')"><a href="sistema.php">SISTEMA</a></li>
       <li onmouseover="mudaFoto('imagens/dashboard.png')"onmouseout="mudaFoto('imagens/home.png')"><a href="Dashboard_de_Monitoramento.php">DASHBOARD</a></li>
    </ul> 
    </nav>
</body>
</html>
