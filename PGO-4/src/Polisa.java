public class Polisa {
    private String numerPolisy;
    private String klient;
    private double skladkaBazowa;
    private int poziomRyzyka;
    private double wartoscPojazdu;
    private boolean czyMaAlarm;
    private boolean czyBezszkodowyKlient;

    private static int liczbaUtworzonychPolis = 0;
    private static final double OPLATA_ADMINISTRACYJNA = 50.0;

    public Polisa(String numerPolisy, String klient, double skladkaBazowa, int poziomRyzyka, double wartoscPojazdu, boolean czyMaAlarm, boolean czyBezszkodowyKlient) {
        this.numerPolisy = numerPolisy;
        this.klient = klient;
        this.skladkaBazowa = skladkaBazowa;
        this.poziomRyzyka = poziomRyzyka;
        this.wartoscPojazdu = wartoscPojazdu;
        this.czyBezszkodowyKlient = czyBezszkodowyKlient;

        liczbaUtworzonychPolis++;

    }

    public String getNumerPolisy() {
        return numerPolisy;
    }

    public double obliczSkladkeKoncowa() {
        double wynik = skladkaBazowa + OPLATA_ADMINISTRACYJNA;
        double obecna = obliczSkladkeKoncowa();
        double nowa = obecna;

        // dopłata za ryzyko
        wynik += poziomRyzyka * 120;

        // dopłata za drogi pojazd
        if (wartoscPojazdu > 60000) {
            wynik += 150;
        }

        // zniżki
        if (czyMaAlarm) {
            wynik *= 0.95;
        }

        if (czyBezszkodowyKlient) {
            wynik *= 0.92;

        }

        // minimalna skladka
        double min = obecna * 0.90;
        if (nowa < min) {
            nowa = min;
        }

        // maksymalna skladka
        double max = obecna * 1.25;
        if (nowa > max) {
            nowa = max;
        }
    }


}

