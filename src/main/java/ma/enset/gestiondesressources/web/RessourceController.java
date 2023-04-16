package ma.enset.gestiondesressources.web;


import jakarta.validation.Valid;
import ma.enset.gestiondesressources.entities.Ressource;
import ma.enset.gestiondesressources.services.RessourceService;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RessourceController {

    private final RessourceService ressourceService;

    public RessourceController(RessourceService ressourceService) {
        this.ressourceService = ressourceService;
    }

    @GetMapping(path = "/user/index")
    public String Ressources(Model model,
                           @RequestParam(name = "page", defaultValue = "0")int page,
                           @RequestParam(name = "size", defaultValue = "5")int size,
                           @RequestParam(name = "motCle", defaultValue = "")String motCle){
        Page<Ressource> pageRessources = ressourceService.searchRessourcesByKeyword(motCle, page, size);
        model.addAttribute("ListRessources", pageRessources.getContent());
        model.addAttribute("pages", new int[pageRessources.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("motCle", motCle);
        return "ressources";
    }

    @GetMapping("/admin/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(Long id, String motCle, int page){
        ressourceService.deleteRessourceById(id);
        return "redirect:/user/index?page="+page+"&motCle="+motCle;
    }

    @GetMapping("/admin/formulaireRessource")
    @PreAuthorize("hasRole('ADMIN')")
    public String formulaireRessource(Model model){
        model.addAttribute("ressource", new Ressource());
        return "formulaireRessource";
    }

    @PostMapping(path = "/admin/save")
    @PreAuthorize("hasRole('ADMIN')")
    public String save(Model model, @Valid Ressource ressource, BindingResult bindingResult, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = " ") String motCle){
        if (bindingResult.hasErrors())
            return "formulaireRessource";
        ressourceService.saveRessource(ressource);
        return "redirect:/user/index?page="+page+"&motCle="+motCle;
    }

    @GetMapping(path = "/admin/editRessource")
    @PreAuthorize("hasRole('ADMIN')")
    public String editRessource(Model model, Long id, String motCle, int page){
        Ressource ressource = ressourceService.findRessouceById(id);
        if(ressource == null)
            throw new RuntimeException("Ressource introuvable");
        model.addAttribute("ressource", ressource);
        model.addAttribute("page", page);
        model.addAttribute("motCle", motCle);
        return "editRessource";
    }

    @GetMapping("/")
    public String home(){
        return "redirect:/user/index";
    }
}
