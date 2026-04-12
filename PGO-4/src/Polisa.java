import java.util.Objects;

public class Polisa {
    private String numerPolisy;
    private String nazwaKlienta;
    private double skladkaBazowa;
    private int poziomRyzyka;
    private double wartoscPojazdu;
    private boolean czyMaAlarm;
    private boolean czyBezszkodowyKlient;

    private static int liczbaUtworzonychPolis = 0;
    private static final double OPLATA_ADMINISTRACYJNA = 50.0;

    public Polisa(
            String numerPolisy,
            String nazwaKlienta,
            double skladkaBazowa,
            int poziomRyzyka,
            double wartoscPojazdu,
            boolean czyMaAlarm,
            boolean czyBezszkodowyKlient
    ) {
        this.numerPolisy = numerPolisy;
        this.nazwaKlienta = nazwaKlienta;
        this.skladkaBazowa = skladkaBazowa;
        this.poziomRyzyka = poziomRyzyka;
        this.wartoscPojazdu = wartoscPojazdu;
        this.czyBezszkodowyKlient = czyBezszkodowyKlient;
        this.czyMaAlarm = czyMaAlarm;

        liczbaUtworzonychPolis++;
    }

    public String getNumerPolisy() {
        return numerPolisy;
    }

    public double obliczSkladkeKoncowa() {
        double skladka = skladkaBazowa + OPLATA_ADMINISTRACYJNA;

        skladka += poziomRyzyka * 120;

        if (wartoscPojazdu > 60000) {
            skladka += 200;
        }

        if (czyMaAlarm) {
            skladka -= 100;
        }

        if (czyBezszkodowyKlient) {
            skladka *= 0.9;
        }

        if (skladka < skladkaBazowa) {
            skladka = skladkaBazowa;
        }

        return Math.round(skladka * 100.0) / 100.0;
    }

    public double obliczSkladkeOdnowieniowa() {
        double bazowa = obliczSkladkeKoncowa();
        double nowa = bazowa;

        if (poziomRyzyka == 4) {
            nowa *= 1.10;
        }
        else if (poziomRyzyka >= 5) {
            nowa *= 1.20;
        }

        if (wartoscPojazdu > 60000) {
            nowa += 150;
        }

        if (czyBezszkodowyKlient) {
            nowa *= 0.92;
        }

        if (czyMaAlarm) {
            nowa *= 0.95;
        }

        double skladkaMin = bazowa * 0.9;
        double skladkaMax = bazowa * 1.25;

        if (nowa < skladkaMin) {
            nowa = skladkaMin;
        }

        if (nowa > skladkaMax) {
            nowa = skladkaMax;
        }

        return Math.round(nowa * 100.0) / 100.0;
    }

    public String pobierzPodsumowanieRyzyka() {
        if (poziomRyzyka >= 4) {
            return "Wysokie Ryzyko";
        } else if (poziomRyzyka == 3) {
            return "Srednie Ryzyko";
        } else {
            return "Niskie Ryzyko";
        }
    }

    public static int pobierzLiczbeUtworzonychPolis() {
        return liczbaUtworzonychPolis;
    }

    public String toString() {
        return "Polisa: " + numerPolisy + ", klient: " + nazwaKlienta + ", skladkaKoncowa: " + obliczSkladkeKoncowa();
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Polisa)) return false;
        Polisa polisa = (Polisa) o;
        return Objects.equals(numerPolisy, polisa.numerPolisy);
    }
}



