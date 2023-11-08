import java.util.ArrayList;
import java.util.Scanner;

class Klient {
    private String nazwaKlienta;
    private int numerIdKlienta;
    private String adresKlienta;

    public Klient(String nazwaKlienta, int numerIdKlienta, String adresKlienta) {
        this.nazwaKlienta = nazwaKlienta;
        this.numerIdKlienta = numerIdKlienta;
        this.adresKlienta = adresKlienta;
    }

    public String getNazwaKlienta() {
        return nazwaKlienta;
    }

    public int getNumerIdKlienta() {
        return numerIdKlienta;
    }

    public String getAdresKlienta() {
        return adresKlienta;
    }

    public void setAdresKlienta(String adresKlienta) {
        this.adresKlienta = adresKlienta;
    }

    public void wyswietlInformacjeOKliencie() {
        System.out.println("Nazwa klienta: " + nazwaKlienta);
        System.out.println("Numer ID klienta: " + numerIdKlienta);
        System.out.println("Adres klienta: " + adresKlienta);
    }
}

class KontoBankowe {
    private String numerKonta;
    private double saldo;
    private Klient wlascicielKonta;

    public KontoBankowe(String numerKonta, double saldo, Klient wlascicielKonta) {
        this.numerKonta = numerKonta;
        this.saldo = saldo;
        this.wlascicielKonta = wlascicielKonta;
    }

    public String getNumerKonta() {
        return numerKonta;
    }

    public double getSaldo() {
        return saldo;
    }

    public Klient getWlascicielKonta() {
        return wlascicielKonta;
    }

    public void wplacSrodki(double kwota) {
        saldo += kwota;
    }

    public void wyplacSrodki(double kwota) {
        if (saldo >= kwota) {
            saldo -= kwota;
        } else {
            System.out.println("Brak wystarczających środków na koncie.");
        }
    }

    public void wyswietlInformacjeOKoncie() {
        System.out.println("Numer konta: " + numerKonta);
        System.out.println("Saldo: " + saldo);
        System.out.println("Właściciel konta: " + wlascicielKonta.getNazwaKlienta());
    }

    public void menuBankowe() {
        Scanner scanner = new Scanner(System.in);
        int wybor;
        do {
            System.out.println("Menu Bankowe:");
            System.out.println("1. Wyświetl saldo");
            System.out.println("2. Wpłać środki");
            System.out.println("3. Wypłać środki");
            System.out.println("0. Wyjście");
            System.out.print("Wybierz opcję: ");
            wybor = scanner.nextInt();

            switch (wybor) {
                case 1:
                    System.out.println("Saldo: " + getSaldo());
                    break;
                case 2:
                    System.out.print("Podaj kwotę do wpłacenia: ");
                    double kwotaWplata = scanner.nextDouble();
                    wplacSrodki(kwotaWplata);
                    break;
                case 3:
                    System.out.print("Podaj kwotę do wypłacenia: ");
                    double kwotaWyplata = scanner.nextDouble();
                    wyplacSrodki(kwotaWyplata);
                    break;
                case 0:
                    System.out.println("Dziękujemy za skorzystanie z usług banku.");
                    break;
                default:
                    System.out.println("Niepoprawny wybór. Spróbuj ponownie.");
            }
        } while (wybor != 0);
    }
}

public class ZarzadzanieKontemBankowym {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<KontoBankowe> konta = new ArrayList<>();

        int wybor;
        do {
            System.out.println("Menu Główne:");
            System.out.println("1. Utwórz nowe konto");
            System.out.println("2. Zarządzaj istniejącym kontem");
            System.out.println("0. Wyjście");
            System.out.print("Wybierz opcję: ");
            wybor = scanner.nextInt();

            switch (wybor) {
                case 1:
                    System.out.print("Podaj numer konta: ");
                    String numerKonta = scanner.next();
                    System.out.print("Podaj początkowe saldo: ");
                    double saldo = scanner.nextDouble();
                    System.out.print("Podaj nazwę klienta: ");
                    String nazwaKlienta = scanner.next();
                    System.out.print("Podaj numer ID klienta: ");
                    int numerIdKlienta = scanner.nextInt();
                    System.out.print("Podaj adres klienta: ");
                    String adresKlienta = scanner.next();

                    Klient nowyKlient = new Klient(nazwaKlienta, numerIdKlienta, adresKlienta);
                    KontoBankowe noweKonto = new KontoBankowe(numerKonta, saldo, nowyKlient);
                    konta.add(noweKonto);
                    System.out.println("Nowe konto zostało utworzone.");
                    break;
                case 2:
                    if (konta.isEmpty()) {
                        System.out.println("Nie ma dostępnych kont.");
                    } else {
                        System.out.println("Dostępne konta:");
                        for (int i = 0; i < konta.size(); i++) {
                            System.out.println((i + 1) + ". Numer konta: " + konta.get(i).getNumerKonta());
                        }
                        System.out.print("Wybierz numer konta do zarządzania: ");
                        int numerKontaDoZarzadzania = scanner.nextInt();
                        if (numerKontaDoZarzadzania >= 1 && numerKontaDoZarzadzania <= konta.size()) {
                            KontoBankowe konto = konta.get(numerKontaDoZarzadzania - 1);
                            konto.menuBankowe();
                        } else {
                            System.out.println("Niepoprawny numer konta.");
                        }
                    }
                    break;
                case 0:
                    System.out.println("Dziękujemy za skorzystanie z programu.");
                    break;
                default:
                    System.out.println("Niepoprawny wybór. Spróbuj ponownie.");
            }
        } while (wybor != 0);
    }
}
