package structData.ProjectWork.controller;

//бекапила import javafx.collections.transformation.SortedList;
import structData.ProjectWork.randomGenerator.Polynom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import structData.ProjectWork.randomGenerator.RandomGenerator;
import structData.ProjectWork.randomGenerator.SCalculation;
import structData.ProjectWork.repos.PolynomRepo;

import java.util.*;

@Controller
public class MainController {
    @Autowired
    private PolynomRepo polynomRepo;
    private int polynomDegree = -1;
    private int polynomDegree2 = -1;
    private int polynomDegree3 = -1;
    private int polynomId = -1;
    private int polynomId2 = -1;
    private int polynomId3 = -1;
    Iterable<Polynom> polynoms;
    //формирование списка степеней полиномов
    Set<Integer> degrees = new HashSet<>();

    @GetMapping("/")
    public String main(Model model) {
        degrees.clear();
        polynoms = polynomRepo.findAll();
        for (Polynom p : polynoms) {
            degrees.add(p.getDegree());
        }
        model.addAttribute("degrees", degrees);

        model.addAttribute("cd", polynomDegree);
        model.addAttribute("cd2", polynomDegree);
        model.addAttribute("cd3", polynomDegree);
        model.addAttribute("pid", polynomId);
        model.addAttribute("pid", polynomId2);
        model.addAttribute("pid", polynomId3);

        return "main";
    }

    @GetMapping("/degree")
    public String degree(@RequestParam(defaultValue = "-1") int degree, Model model) {

        if (degree != -1 || polynomDegree != -1) {
            if (degree != -1)
                polynomDegree = degree;

            Iterable<Polynom> chosenPolynoms = polynomRepo.findByDegree(polynomDegree);
            List<Polynom> sortedListOfPolynoms = new ArrayList<>();
            for (Polynom p : chosenPolynoms) {
                sortedListOfPolynoms.add(p);
            }
            Collections.sort(sortedListOfPolynoms);
            model.addAttribute("polynoms", sortedListOfPolynoms);

        }

        if (polynomDegree2 != -1) {

            Iterable<Polynom> chosenPolynoms2 = polynomRepo.findByDegree(polynomDegree2);
            List<Polynom> sortedListOfPolynoms2 = new ArrayList<>();
            for (Polynom p : chosenPolynoms2) {
                sortedListOfPolynoms2.add(p);
            }
            Collections.sort(sortedListOfPolynoms2);
            model.addAttribute("polynoms2", sortedListOfPolynoms2);

        }
        if ( polynomDegree3 != -1) {

            Iterable<Polynom> chosenPolynoms3 = polynomRepo.findByDegree(polynomDegree3);
            List<Polynom> sortedListOfPolynoms3 = new ArrayList<>();
            for (Polynom p : chosenPolynoms3) {
                sortedListOfPolynoms3.add(p);
            }
            Collections.sort(sortedListOfPolynoms3);

            model.addAttribute("polynoms3", sortedListOfPolynoms3);

        }
        model.addAttribute("cd", polynomDegree);
        model.addAttribute("cd3", polynomDegree3);
        model.addAttribute("cd2", polynomDegree2);
        model.addAttribute("degrees", degrees);
        return "main";
    }

    @GetMapping("/degree2")
    public String degree2(@RequestParam(defaultValue = "-1") int degree2, Model model) {
        if ( polynomDegree != -1) {

            Iterable<Polynom> chosenPolynoms = polynomRepo.findByDegree(polynomDegree);
            List<Polynom> sortedListOfPolynoms = new ArrayList<>();
            for (Polynom p : chosenPolynoms) {
                sortedListOfPolynoms.add(p);
            }
            Collections.sort(sortedListOfPolynoms);
            model.addAttribute("polynoms", sortedListOfPolynoms);
        }

        if (degree2 != -1 || polynomDegree2 != -1) {
            if (degree2 != -1)
                polynomDegree2 = degree2;

            Iterable<Polynom> chosenPolynoms2 = polynomRepo.findByDegree(polynomDegree2);
            List<Polynom> sortedListOfPolynoms2 = new ArrayList<>();
            for (Polynom p : chosenPolynoms2) {
                sortedListOfPolynoms2.add(p);
            }
            Collections.sort(sortedListOfPolynoms2);
            model.addAttribute("polynoms2", sortedListOfPolynoms2);
        }
        if ( polynomDegree3 != -1) {

            Iterable<Polynom> chosenPolynoms3 = polynomRepo.findByDegree(polynomDegree3);
            List<Polynom> sortedListOfPolynoms3 = new ArrayList<>();
            for (Polynom p : chosenPolynoms3) {
                sortedListOfPolynoms3.add(p);
            }
            Collections.sort(sortedListOfPolynoms3);

            model.addAttribute("polynoms3", sortedListOfPolynoms3);
        }
        model.addAttribute("cd", polynomDegree);
        model.addAttribute("cd3", polynomDegree3);
        model.addAttribute("cd2", polynomDegree2);
        model.addAttribute("degrees", degrees);
        return "main";
    }

    @GetMapping("/degree3")
    public String degree3(@RequestParam(defaultValue = "-1") int degree3, Model model) {
        if (degree3 != -1 || polynomDegree3 != -1) {
            if (degree3 != -1)
                polynomDegree3 = degree3;

            Iterable<Polynom> chosenPolynoms3 = polynomRepo.findByDegree(polynomDegree3);
            List<Polynom> sortedListOfPolynoms3 = new ArrayList<>();
            for (Polynom p : chosenPolynoms3) {
                sortedListOfPolynoms3.add(p);
            }
            Collections.sort(sortedListOfPolynoms3);

            model.addAttribute("polynoms3", sortedListOfPolynoms3);
        }
        if (polynomDegree2 != -1) {

            Iterable<Polynom> chosenPolynoms2 = polynomRepo.findByDegree(polynomDegree2);
            List<Polynom> sortedListOfPolynoms2 = new ArrayList<>();
            for (Polynom p : chosenPolynoms2) {
                sortedListOfPolynoms2.add(p);
            }
            Collections.sort(sortedListOfPolynoms2);
            model.addAttribute("polynoms2", sortedListOfPolynoms2);
        }
        if ( polynomDegree != -1) {

            Iterable<Polynom> chosenPolynoms = polynomRepo.findByDegree(polynomDegree);
            List<Polynom> sortedListOfPolynoms = new ArrayList<>();
            for (Polynom p : chosenPolynoms) {
                sortedListOfPolynoms.add(p);
            }
            Collections.sort(sortedListOfPolynoms);
            model.addAttribute("polynoms", sortedListOfPolynoms);
        }
        model.addAttribute("cd", polynomDegree);
        model.addAttribute("cd3", polynomDegree3);
        model.addAttribute("cd2", polynomDegree2);
        model.addAttribute("degrees", degrees);
        return "main";
    }

    @PostMapping("/polynoms")
    public String result (
            @RequestParam (defaultValue = "-1") int polynomId,
            @RequestParam (defaultValue = "-1") int polynomId2,
            @RequestParam (defaultValue = "-1") int polynomId3,
            Model model
    ) {
        if ( polynomId == -1 || polynomId2 == -1)
            return "redirect:/main";

        this.polynomId = polynomId;
        this.polynomId2 = polynomId2;

        if  ( polynomId3 != -1 ) {
            this.polynomId3 = polynomId3;
            return "redirect:/result";
        }

        return "redirect:/anoutherResult";
    }

    @GetMapping("/result")
    public String result (Model model) {
        RandomGenerator[] list = new RandomGenerator[3];

        if(polynomId != -1) {
            Optional<Polynom> polynoms = polynomRepo.findById(polynomId);
            RandomGenerator result = new RandomGenerator(polynoms.get());
            list[0] = result;
            model.addAttribute("name", result.getName());
            model.addAttribute("Fx", result.getFx());
            model.addAttribute("Tex", result.getExperimentalT());
            model.addAttribute("Tteo", result.getTheoreticT());
            model.addAttribute("wt", result.wt());
            model.addAttribute("ListOfState", result.getListOfS());
            model.addAttribute("MState", result.getAllMState());
        }

        if(polynomId2 != -1) {
            Optional<Polynom> polynoms = polynomRepo.findById(polynomId2);
            RandomGenerator result = new RandomGenerator(polynoms.get());
            list[1] = result;
            model.addAttribute("name2", result.getName());
            model.addAttribute("Fx2", result.getFx());
            model.addAttribute("Tex2", result.getExperimentalT());
            model.addAttribute("wt2", result.wt());
            model.addAttribute("Tteo2", result.getTheoreticT());
            model.addAttribute("ListOfState2", result.getListOfS());
            model.addAttribute("MState2", result.getAllMState());
        }

        if(polynomId3 != -1) {
            Optional<Polynom> polynoms = polynomRepo.findById(polynomId3);
            RandomGenerator result = new RandomGenerator(polynoms.get());
            list[2] = result;
            model.addAttribute("name3", result.getName());
            model.addAttribute("Fx3", result.getFx());
            model.addAttribute("Tex3", result.getExperimentalT());
            model.addAttribute("wt3", result.wt());
            model.addAttribute("Tteo3", result.getTheoreticT());
            model.addAttribute("ListOfState3", result.getListOfS());
            model.addAttribute("MState3", result.getAllMState());
        }

        try {
            SCalculation solution = new SCalculation(list);
            model.addAttribute("Ts",solution.getTs());
            model.addAttribute("Slist",solution.getSList());
            model.addAttribute("akf", solution.akf());
        } catch (Exception e) {
            model.addAttribute("Ts", " Ошибка: периоды полиномов не взаимнопростые");
            e.printStackTrace();
        }
        return "result";
    }

    @GetMapping("/anoutherResult")
    public String aresult (Model model) {
        RandomGenerator[] list = new RandomGenerator[2];

        if(polynomId != -1) {
            Optional<Polynom> polynoms = polynomRepo.findById(polynomId);
            RandomGenerator result = new RandomGenerator(polynoms.get());
            list[0] = result;
            model.addAttribute("name", result.getName());
            model.addAttribute("Fx", result.getFx());
            model.addAttribute("Tex", result.getExperimentalT());
            model.addAttribute("Tteo", result.getTheoreticT());
            model.addAttribute("wt", result.wt());
            model.addAttribute("ListOfState", result.getListOfS());
            model.addAttribute("MState", result.getAllMState());
        }

        if(polynomId2 != -1) {

            Optional<Polynom> polynoms = polynomRepo.findById(polynomId2);
            RandomGenerator result = new RandomGenerator(polynoms.get());
            list[1] = result;
            model.addAttribute("name2", result.getName());
            model.addAttribute("Fx2", result.getFx());
            model.addAttribute("Tex2", result.getExperimentalT());
            model.addAttribute("Tteo2", result.getTheoreticT());
            model.addAttribute("wt2", result.wt());
            model.addAttribute("ListOfState2", result.getListOfS());
            model.addAttribute("MState2", result.getAllMState());
        }

        try {
            SCalculation solution = new SCalculation(list);

            model.addAttribute("Ts",solution.getTs());
            model.addAttribute("Slist",solution.getSList());
            model.addAttribute("akf", solution.akf());
        } catch (Exception e) {
            model.addAttribute("Ts", "Не взаимопростые периоды");
            e.printStackTrace();
        }
        return "anoutherResult";
    }
}
