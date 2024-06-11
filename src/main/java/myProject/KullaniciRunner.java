package myProject;

import java.util.Scanner;

public class KullaniciRunner {
    public static void main(String[] args) {


        start();


    }

    private static void start() {

        KullaniciServisi kullaniciServisi = new KullaniciServisi();
        Scanner input = new Scanner(System.in);
        String secim;

        do {
            kullaniciServisi.menuyuGoster();
            secim = input.next();

            switch (secim) {
                case "1":
                    kullaniciServisi.kayit();
                    break;
                case "2":
                    kullaniciServisi.giris();
                    break;
                case "3":
                    System.out.println("Iyi gunler dileriz...");
                    break;
                default:
                    System.out.println("Hatali giris yaptiniz. Lutfen 1, 2 veya 3 rakamlarindan birine basiniz.");
            }

        } while (!secim.equals("3"));



    }
}