package structData.ProjectWork.randomGenerator;

import javax.persistence.*;

@Entity
public class Polynom implements Comparable<Polynom> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    public String fullForm ;
    private Integer degree;
    private Integer j;
    private Integer number;
    private String chara = "";

    public Polynom() {}

    public Polynom (Integer degree, String fullForm) {
        this.degree=degree;
        this.fullForm=fullForm;
        fromFullForm();
    }
    public Polynom (Integer degree, Integer j, Integer number, String chara) {
        this.degree=degree;
        this.j=j;
        this.number=number;
        this.chara=chara;
    }

    public String getFullForm() {
        return fullForm;
    }

    public void setFullForm(String fullForm) {
        this.fullForm = fullForm;
    }

    public Integer getDegree() {
        return degree;
    }

    public void setDegree(Integer degree) {
        this.degree = degree;
    }

    public Integer getJ() {
        return j;
    }

    public void setJ(Integer j) {
        this.j = j;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getChara() {
        return chara;
    }

    public void setChara(String chara) {
        this.chara = chara;
    }

    public Integer getId () {
        return id;
    }

    private void fromFullForm() {
        //Достаём первую цифру из полной формы полинома
        j = Integer.parseInt(fullForm.split(" ")[0]);

        //Удаляем
        String str = fullForm;
        str = str.replaceAll("\\D+","");
        str = str.replaceFirst(j.toString(), "");
        number = Integer.parseInt(str);

        String str2 = fullForm;
        str2 = str2.replaceFirst(j.toString(), "");
        str2 = str2.replaceFirst(number.toString(),"");
        str2 = str2.replaceFirst(" ", "");
        chara = str2;
    }

    @Override
    public int compareTo(Polynom o) {
        if ( this.degree == o.getDegree())
            return this.j-o.getJ();

        else return this.getDegree() - o.getDegree();
    }
}
