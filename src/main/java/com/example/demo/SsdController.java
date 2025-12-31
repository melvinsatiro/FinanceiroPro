import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/ssd")
public class SsdController {

    // O caminho que o lsblk nos mostrou
    private final String DIRETORIO_SSD = "/mnt/hd";

    // 1. Listar arquivos do SSD
    @GetMapping("/arquivos")
    public List<String> listarArquivos() {
        File pasta = new File(DIRETORIO_SSD);
        File[] listaDeArquivos = pasta.listFiles();
        
        if (listaDeArquivos == null) return Arrays.asList("Pasta vazia ou erro de permissão");

        return Arrays.stream(listaDeArquivos)
                .map(File::getName)
                .collect(Collectors.toList());
    }

    // 2. Download de um arquivo específico
    @GetMapping("/download/{nomeArquivo}")
    public ResponseEntity<Resource> baixar(@PathVariable String nomeArquivo) {
        try {
            Path caminho = Paths.get(DIRETORIO_SSD).resolve(nomeArquivo);
            Resource resource = new UrlResource(caminho.toUri());

            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    @PostMapping("/upload")
public ResponseEntity<String> upload(@RequestParam("arquivo") MultipartFile arquivo) {
    try {
        if (arquivo.isEmpty()) {
            return ResponseEntity.badRequest().body("Nenhum arquivo selecionado.");
        }

        // Define o caminho: /mnt/hd/nome-do-arquivo
        Path destino = Paths.get(DIRETORIO_SSD).resolve(arquivo.getOriginalFilename());
        
        // Copia o fluxo de dados para o SSD, substituindo se já existir
        Files.copy(arquivo.getInputStream(), destino, StandardCopyOption.REPLACE_EXISTING);

        return ResponseEntity.ok("Arquivo enviado com sucesso!");
    } catch (Exception e) {
        return ResponseEntity.internalServerError().body("Erro ao salvar: " + e.getMessage());
    }
}
}
