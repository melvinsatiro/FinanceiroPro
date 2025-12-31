package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import jakarta.servlet.http.HttpSession;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.lang.management.ManagementFactory;
import com.sun.management.OperatingSystemMXBean;
import java.util.HashMap;
import java.util.Map;

@Controller
public class WebController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Retorna os dados para os gráficos e indicadores da Dashboard (JSON)
     */
    @GetMapping("/api/stats")
    @ResponseBody
    public Map<String, Object> getStats(HttpSession session) {
        // BLOQUEIO DE SEGURANÇA
        if (session.getAttribute("usuarioLogado") == null) {
            return null;
        }

        Map<String, Object> stats = new HashMap<>();
        OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);

        // 1. Memória RAM
        long totalRAM = osBean.getTotalMemorySize() / (1024 * 1024);
        long freeRAM = osBean.getFreeMemorySize() / (1024 * 1024);
        stats.put("ramUsada", (totalRAM - freeRAM) + " MB");
        stats.put("ramLivre", freeRAM + " MB");

        // 2. CPU: Uso e Temperatura
        double cpuLoad = osBean.getCpuLoad() * 100;
        stats.put("cpuUso", String.format("%.1f%%", cpuLoad));

        try {
            String tempStr = Files.readAllLines(Paths.get("/sys/class/thermal/thermal_zone0/temp")).get(0);
            double tempC = Double.parseDouble(tempStr) / 1000.0;
            stats.put("cpuTemp", String.format("%.1f°C", tempC));
        } catch (Exception e) {
            stats.put("cpuTemp", "N/A");
        }

        // 3. Armazenamento SSD Principal (/) - Cartão SD do Raspberry
        File rootDir = new File("/");
        long totalSD = rootDir.getTotalSpace() / (1024 * 1024 * 1024);
        long freeSD = rootDir.getFreeSpace() / (1024 * 1024 * 1024);
        stats.put("ssdUsado", (totalSD - freeSD) + " GB");
        stats.put("ssdLivre", freeSD + " GB");

        // 4. Armazenamento Externo (/mnt/hd) - O SSD de 112GB
        File sda1 = new File("/mnt/hd");
        if (sda1.exists()) {
            long totalSda = sda1.getTotalSpace() / (1024 * 1024 * 1024);
            long freeSda = sda1.getFreeSpace() / (1024 * 1024 * 1024);
            stats.put("sda1Usado", (totalSda - freeSda) + " GB");
            stats.put("sda1Livre", freeSda + " GB");
        } else {
            stats.put("sda1Usado", "0 GB");
            stats.put("sda1Livre", "Desconectado");
        }

        // 5. Dados do Banco de Dados (MariaDB)
        try {
            String sqlSize = "SELECT SUM(data_length + index_length) / 1024 / 1024 FROM information_schema.TABLES WHERE table_schema = 'arduino'";
            Double size = jdbcTemplate.queryForObject(sqlSize, Double.class);
            stats.put("mysqlSize", String.format("%.2f MB", (size != null ? size : 0.0)));
            stats.put("mysqlStatus", "Online");

            // Sensor: Última leitura
            String sqlSensor = "SELECT valor, data FROM sensores ORDER BY id DESC LIMIT 1";
            Map<String, Object> lastRead = jdbcTemplate.queryForMap(sqlSensor);
            stats.put("sensorValor", lastRead.get("valor").toString() + "°C");
            stats.put("sensorData", lastRead.get("data").toString());
        } catch (Exception e) {
            stats.put("mysqlStatus", "Erro/Offline");
            stats.put("sensorValor", "N/A");
        }

        return stats;
    }

    /**
     * Renderiza a página HTML do Gerenciador de Arquivos
     */
    @GetMapping("/gerenciar-arquivos")
    public String paginaArquivos(Model model, HttpSession session) {
        // BLOQUEIO DE SEGURANÇA
        if (session.getAttribute("usuarioLogado") == null) {
            return "redirect:/login"; 
        }

        File pasta = new File("/mnt/hd");
        String[] arquivos = pasta.list();

        // Envia a lista de arquivos para o Thymeleaf
        model.addAttribute("listaArquivos", (arquivos != null) ? arquivos : new String[0]);

        return "arquivos"; // Abre src/main/resources/templates/arquivos.html
    }
}
