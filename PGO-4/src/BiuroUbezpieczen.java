import java.util.ArrayList;

public class BiuroUbezpieczen {
    private String nazwa;
    private ArrayList<Polisa> polisy;
    public BiuroUbezpieczen(String nazwa) {
        this.nazwa = nazwa;
        this.polisy = new ArrayList<>();
    }

    public void dodajPolise(Polisa polisa) {
        polisy.add(polisa);
    }

    public void wypiszRaport() {
        System.out.println("Raport: " + nazwa);
        for (Polisa p : polisy) {
            System.out.println(p);
        }
    }

    public double policzLacznaSkladke() {
        double suma = 0;
        for (Polisa p : polisy) {
            suma += p.obliczSkladkeKoncowa();
        }
        return suma;
    }

    public double policzLacznaPrognozeOdnowien() {
        double suma = 0;
        for (Polisa p : polisy) {
            suma += p.obliczSkladkeOdnowieniowa();
        }
        return suma;
    }

    public int policzPolisyWysokiegoRyzyka() {
        int licznik = 0;
        for (Polisa p : polisy) {
            if (p.pobierzPodsumowanieRyzyka().equals("Wysokie Ryzyko")) {
                licznik++;
            }
        }
        return licznik;
    }

    public Polisa znajdzPoNumerze(String numer) {
        for (Polisa p : polisy) {
            if (p.getNumerPolisy().equals(numer)) {
                return p;
            }
        }
        return null;
    }

    public void wypiszTanszeNiz(double prog) {
        System.out.println("Tańsze niż " + prog + ":");
        for (Polisa p : polisy) {
            if (p.obliczSkladkeKoncowa() < prog) {
                System.out.println(p);
            }
        }
    }
}
