package myProject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class KullaniciServisi {

    List<String> kullaniciAdiListesi = new ArrayList<>();
    List<String> emailListesi = new ArrayList<>();
    List<String> passwordListesi = new ArrayList<>();

    public void menuyuGoster() {
        System.out.println("<---QA TEAM 2--->");
        System.out.println("1-Uye Ol");
        System.out.println("2-Giris Yap");
        System.out.println("3-Cikis Yap");
        System.out.println("Lutfen seciminizi giriniz");
    }

    public void kayit() {

        Scanner input = new Scanner(System.in);
        System.out.println("Lutfen adinizi ve soyadinizi giriniz :");
        String isim = input.nextLine();

        String kullaniciAdi;
        boolean varMi;

        do {
            System.out.println("Kullanici adini giriniz:");
            kullaniciAdi = input.nextLine();
            varMi = kullaniciAdiListesi.contains(kullaniciAdi);

            if (varMi) {
                System.out.println("Bu kullanici adi daha once kullanilmistir. Lutfen yeni bir kullanici adi giriniz");
            }

        } while (varMi);

        String email;
        boolean gecerliMi;
        boolean emailVarMi;

        do {
            System.out.println("Lutfen emailinizi giriniz");
            email = input.nextLine();
            gecerliMi = emailiDogrula(email);
            emailVarMi = emailListesi.contains(email);

            if (emailVarMi) {
                System.out.println("Bu email daha once kullanilmistir. Yeniden deneyiniz");
                gecerliMi = false;
            }

        } while (!gecerliMi);

        String password;
        boolean pswGecerliMi;

        do {
            System.out.println("Lutfen sifrenizi giriniz:");
            password = input.nextLine();
            pswGecerliMi = passwordDogrula(password);


        } while (!pswGecerliMi);

        Kullanici kullanici = new Kullanici(isim, kullaniciAdi, email, password);
        System.out.println(kullanici);
        kullaniciAdiListesi.add(kullaniciAdi);
        emailListesi.add(email);
        passwordListesi.add(password);
        System.out.println("Tebrikler kayit isleminiz gerceklesmistir");
        System.out.println("Kullanici adi ve sifreniz ile giris yapabilirsiniz.");

    }

    public void giris() {
        Scanner input = new Scanner(System.in);
        System.out.println("Email ya da kullanici adinizi giriniz :");
        String kullaniciAdiVeyaEmail = input.nextLine();

        boolean emailIceriyorMu = emailListesi.contains(kullaniciAdiVeyaEmail);
        boolean kullaniciAdiIceriyorMu = kullaniciAdiListesi.contains(kullaniciAdiVeyaEmail);

        if (emailIceriyorMu || kullaniciAdiIceriyorMu) {
            while (true) {
                System.out.println("Lutfen sifrenizi giriniz :");
                String password = input.nextLine();
                int index;

                if (emailIceriyorMu) {
                    index = emailListesi.indexOf(kullaniciAdiVeyaEmail);
                } else {
                    index = kullaniciAdiListesi.indexOf(kullaniciAdiVeyaEmail);
                }

                if (passwordListesi.get(index).equals(password)) {
                    System.out.println("Sisteme giris yaptiniz.");
                    break;
                } else {
                    System.out.println("Sifre yanlis. Lutfen tekrar deneyiniz.");
                }
            }

        } else {
            System.out.println("Sisteme kayitli kullanici bulunamadi");
            System.out.println("Lutfen bilgilerinizi kontrol ediniz ya da uye olunuz");
        }


    }

    public static boolean emailiDogrula(String email) {

        boolean gecerliMi;
        boolean bosluk = email.contains(" ");
        boolean atIceriyorMu = email.contains("@");

        if (bosluk) {
            System.out.println("Email bosluk icermemeli");
            gecerliMi = false;
        } else if (!atIceriyorMu) {
            System.out.println("Email @ icermeli");
            gecerliMi = false;
        } else {
            String ilkParca = email.split("@")[0];
            String ikinciParca = email.split("@")[1];

            boolean ilkKontrol = ilkParca.replaceAll("[A-Za-z0-9-._]", "").isEmpty();

            boolean ikinciKontrol = ikinciParca.equals("gmail.com") || ikinciParca.equals("yahoo.com") || ikinciParca.equals("outlook.com");

            if (!ilkKontrol) {
                System.out.println("Email buyuk-kucuk harf, rakam ya da -._ disinda karakterler iceremez.");
            } else if (!ikinciKontrol) {
                System.out.println("Emailiniz gmail.com, yahoo.com veya outlook. com ile bitmeli.");
            }
            gecerliMi = ilkKontrol && ikinciKontrol;

        }
        return gecerliMi;


    }

    public static boolean passwordDogrula(String password) {

        boolean gecerliMi;

        boolean bosluk = password.contains(" ");
        boolean uzunluk = password.length() >= 6;
        boolean buyukHarf = !password.replaceAll("[^A-Z]", "").isEmpty();
        boolean kucukHarf = !password.replaceAll("[^a-z]", "").isEmpty();
        boolean rakam = !password.replaceAll("[^0-9]", "").isEmpty();
        boolean sembol = !password.replaceAll("[\\P{Punct}]", "").isEmpty();

        if (bosluk) {
            System.out.println("Sifre bosluk iceremez");
        } else if (!uzunluk) {
            System.out.println("Sifre en az 6 karakter icermelidir");
        } else if (!buyukHarf) {
            System.out.println("Sifre en az 1 tane buyuk harf icermelidir");
        } else if (!kucukHarf) {
            System.out.println("Sifre en az 1 tane kucuk harf icermelidir");
        } else if (!sembol) {
            System.out.println("Sifre en az 1 tane sembol icermelidir");
        } else if (!rakam) {
            System.out.println("Sifre en az bir tane rakam icermelidir");
        }

        gecerliMi = !bosluk && uzunluk && buyukHarf && kucukHarf && rakam && sembol;

        return gecerliMi;

    }
}
