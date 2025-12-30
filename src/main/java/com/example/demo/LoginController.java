package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

  @GetMapping("/login")
  public String loginPage() {
    return "login.html"; // Serve o arquivo da pasta static
  }

  @PostMapping("/autenticar")
  public String autenticar(@RequestParam String usuario, @RequestParam String senha, HttpSession session) {
    // Validação simples
    if ("melvin".equals(usuario) && "1234".equals(senha)) {
      session.setAttribute("usuarioLogado", usuario); // Cria a sessão
      return "redirect:/dashboard"; // Vai para a rota protegida
    }
    return "redirect:/login?erro=true";
  }

  @GetMapping("/logout")
  public String logout(HttpSession session) {
    session.invalidate(); // Destrói a sessão do usuário
    return "redirect:/login";
  }

  // Dentro de LoginController.java
  @GetMapping("/dashboard")
  public String dashboard(HttpSession session) {
    // Se o usuário não passou pelo login, redireciona para a tela de entrada
    if (session.getAttribute("usuarioLogado") == null) {
      return "redirect:/login";
    }
    // Caso esteja logado, o Java carrega o arquivo privado da pasta templates
    return "dashboard";
  }

}
