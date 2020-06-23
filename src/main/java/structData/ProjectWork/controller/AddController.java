package structData.ProjectWork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import structData.ProjectWork.randomGenerator.Polynom;
import structData.ProjectWork.randomGenerator.RandomGenerator;
import structData.ProjectWork.repos.PolynomRepo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class AddController {
    @Autowired
    private PolynomRepo polynomRepo;
    @GetMapping("/add")
    public String addPage(Model model) {

        Iterable<Polynom> allPolynoms = polynomRepo.findAll();
        List<Polynom> sortedListOfPolynoms = new ArrayList<>();

        for(Polynom p : allPolynoms) {
            sortedListOfPolynoms.add(p);
        }

        Collections.sort(sortedListOfPolynoms);

        model.addAttribute("polys", sortedListOfPolynoms);

        return "add";
    }

    @PostMapping("/add")
    public String add(@RequestParam String degree, @RequestParam String polynom, Model model) {
        int degreeToInt = Integer.parseInt(degree);
        Polynom polynomnew = new Polynom(degreeToInt,polynom);
        RandomGenerator check = new RandomGenerator(polynomnew);
        if(check.isValid())
            polynomRepo.save(polynomnew);

        return "redirect:/add";
    }

    @PostMapping("/remove")
    public String remove(
            @RequestParam String [] removing,
            @RequestParam(defaultValue = "false") boolean deleteAll,
            Model model
    ) {
        if (deleteAll) {
            polynomRepo.deleteAll();
            return "redirect:/add";
        }

        for (String i: removing) {
            polynomRepo.deleteById(Integer.parseInt(i));
        }
        return "redirect:/add";
    }
}
