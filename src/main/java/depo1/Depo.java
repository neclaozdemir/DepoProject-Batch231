package depo1;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Depo extends Urun implements DepoYonetimSistemi {


    public static Map<Integer, Urun> urunler = new HashMap<>(); // Urunleri saklamak icin bir Map kullandik
    public static Scanner input = new Scanner(System.in);

    public Depo(int id, String ad, String uretici, int miktar, String birim, String raf) {
        super(id, ad, uretici, miktar, birim, raf);
    }

    public Depo() {

    }


    @Override
    public void menuyuGoster() {
        System.out.println("<--- DEPO UYUGULAMSINA HOSGELDINIZ --->");
        System.out.println("1- Urunleri Listele");
        System.out.println("2- Urun Tanımlama");
        System.out.println("3- Urun Girisi Yap");
        System.out.println("4- Urunu Rafa Koy");
        System.out.println("5- Urun Cikisi Yap");
        System.out.println("0- Cikis");
        System.out.println("-----------------------------------------------------------");
        System.out.println("| Lutfen yapmak istediginiz isleme gore bir secim yapiniz |");
        System.out.println("-----------------------------------------------------------");
    }

    @Override
    public void urunListele() {
        Map<Integer, Urun> siraliUrunler = new TreeMap<>(urunler);

        System.out.println("--------------------------------------------------------------");
        System.out.printf("| %-5s | %-15s | %-15s | %-7s | %-10s | %-5s |\n", "ID", "Urun Adi", "Uretici", "Miktar", "Birim", "Raf");
        System.out.println("--------------------------------------------------------------");
        for (Urun urun : siraliUrunler.values()) {
            System.out.printf("| %-5d | %-15s | %-15s | %-7d | %-10s | %-5s |\n", urun.getId(), urun.getAd(),
                    urun.getUretici(), urun.getMiktar(), urun.getBirim(), (urun.getRaf() == null ? "null" : urun.getRaf()));
        }
        System.out.println("--------------------------------------------------------------");
    }

    @Override
    public void urunTanimlama() {
        boolean gecerli = false;
        do {
            try {
                System.out.println("--------------------------------------------------------------------------------------");
                System.out.println("| Lutfen sizden istenen bilgileri giriniz. Ana menuye donmek isterseniz '*' basiniz. |");
                System.out.println("--------------------------------------------------------------------------------------");
                System.out.println("-----------------------");
                System.out.println("| Urun adini giriniz: |");
                System.out.println("-----------------------");
                String ad = input.nextLine();
                if (ad.isEmpty() || ad.isBlank()) {
                    throw new Exception();
                }
                if (ad.equals("*")) { // Ana menüye dönme kontrolü
                    System.out.println("------------------------------------");
                    System.out.println("| Ana menuye yonlendiriliyorsunuz. |");
                    System.out.println("------------------------------------");
                    return; // Ana menüye dön
                }

                System.out.println("------------------------------");
                System.out.println("| Uretici bilgisini giriniz: |");
                System.out.println("------------------------------");
                String uretici = input.nextLine();
                if (uretici.isEmpty() || uretici.isBlank()) {
                    throw new Exception();
                }
                if (uretici.equals("*")) { // Ana menüye dönme kontrolü
                    System.out.println("------------------------------------");
                    System.out.println("| Ana menuye yonlendiriliyorsunuz. |");
                    System.out.println("------------------------------------");
                    return; // Ana menüye dön
                }
                System.out.println("------------------");
                System.out.println("| Birim giriniz: |");
                System.out.println("------------------");
                String birim = input.nextLine();
                if (birim.isEmpty() || birim.isBlank()) {
                    throw new Exception();
                }
                if (birim.equals("*")) { // Ana menüye dönme kontrolü
                    System.out.println("------------------------------------");
                    System.out.println("| Ana menuye yonlendiriliyorsunuz. |");
                    System.out.println("------------------------------------");
                    return; // Ana menüye dön
                }
                boolean varMi = false;
                for (Urun urun : urunler.values()) {
                    if (urun.getAd().equalsIgnoreCase(ad) &&
                            urun.getUretici().equalsIgnoreCase(uretici) &&
                            urun.getBirim().equalsIgnoreCase(birim)) {
                        varMi = true;
                        break;
                    }
                }

                if (varMi) {
                    System.out.println("-------------------------------------------------------------");
                    System.out.println("| Bu urun daha once tanimlanmistir. Lutfen tekrar deneyiniz |");
                    System.out.println("-------------------------------------------------------------");
                    continue;
                }

                int id = urunler.size() + 1000; // Gecici bir ID atama
                Urun urun = new Urun(id, ad, uretici, 0, birim, null);
                urunler.put(id, urun);
                System.out.println("-----------------------------");
                System.out.println("| Urun basariyla tanimlandi |");
                System.out.println("-----------------------------");
                urunListele();
                gecerli = true;
            } catch (Exception e) {
                System.out.println("-------------------------------------------");
                System.out.println("| Lutfen bilgileri dogru sekilde giriniz. |" + e.getClass());
                System.out.println("-------------------------------------------");
            }
        } while (!gecerli);
    }

    @Override
    public void urunGirisi() {
        boolean gecerli = false;
        do {
            try {
                urunListele();
                System.out.println("--------------------------------------------------------------------------------------");
                System.out.println("| Lutfen sizden istenen bilgileri giriniz. Ana menuye donmek isterseniz '*' basiniz. |");
                System.out.println("--------------------------------------------------------------------------------------");
                System.out.println("-------------------------------------------------------------");
                System.out.println("| Lutfen yukarıdaki listeden ilgili urunun ID'sini giriniz: |");
                System.out.println("-------------------------------------------------------------");
                String idGirisi = input.nextLine();
                if (idGirisi.isEmpty() || idGirisi.isBlank()) {
                    throw new Exception();
                }
                if (idGirisi.equals("*")) { // Ana menüye dönme kontrolü
                    System.out.println("------------------------------------");
                    System.out.println("| Ana menuye yonlendiriliyorsunuz. |");
                    System.out.println("------------------------------------");
                    return; // Ana menüye dön
                }
                int id = Integer.parseInt(idGirisi);

                if (urunler.containsKey(id)) {
                    System.out.println("-----------------------------------------------------------");
                    System.out.println("| Lutfen giris yapmak istediginiz urun miktarini giriniz: |");
                    System.out.println("-----------------------------------------------------------");
                    String girisMiktar = input.nextLine();
                    if (girisMiktar.isEmpty() || girisMiktar.isBlank()) {
                        throw new Exception();
                    }
                    if (girisMiktar.equals("*")) { // Ana menüye dönme kontrolü
                        System.out.println("------------------------------------");
                        System.out.println("| Ana menuye yonlendiriliyorsunuz. |");
                        System.out.println("------------------------------------");
                        return; // Ana menüye dön
                    }
                    int miktar = Integer.parseInt(girisMiktar);
                    if (miktar < 0) {
                        System.out.println("----------------------------------");
                        System.out.println("| Negatif bir deger giremezsiniz |");
                        System.out.println("----------------------------------");
                        continue;
                    }
                    urunler.get(id).setMiktar(urunler.get(id).getMiktar() + miktar);
                    System.out.println("--------------------------------------------");
                    System.out.println("| Giris basariyla yapildi. Yeni miktar: " + urunler.get(id).getMiktar() + " |");
                    System.out.println("--------------------------------------------");
                    urunListele();

                    gecerli = true;
                } else {
                    System.out.println("----------------------------------------");
                    System.out.println("| Gecersiz ID!. Lutfen tekrar giriniz. |");
                    System.out.println("----------------------------------------");
                }
            } catch (Exception e) {
                System.out.println("-------------------------------------------");
                System.out.println("| Lutfen bilgileri dogru sekilde giriniz. |" + e.getClass());
                System.out.println("-------------------------------------------");

            }
        } while (!gecerli);
    }
    @Override
    public void urunuRafaKoy() {
        boolean gecerli = false;
        do {
            try {
                urunListele();
                System.out.println("--------------------------------------------------------------------------------------");
                System.out.println("| Lutfen sizden istenen bilgileri giriniz. Ana menuye donmek isterseniz '*' basiniz. |");
                System.out.println("--------------------------------------------------------------------------------------");
                System.out.println("-------------------------------------------------------------");
                System.out.println("| Lutfen yukarıdaki listeden ilgili urunun ID'sini giriniz: |");
                System.out.println("-------------------------------------------------------------");
                String idGirisi = input.nextLine();
                if (idGirisi.isEmpty() || idGirisi.isBlank()) {
                    throw new Exception();
                }
                if (idGirisi.equals("*")) { // Ana menüye dönme kontrolü
                    System.out.println("------------------------------------");
                    System.out.println("| Ana menuye yonlendiriliyorsunuz. |");
                    System.out.println("------------------------------------");
                    return; // Ana menüye dön
                }
                int id = Integer.parseInt(idGirisi);
                if (urunler.containsKey(id)) {
                    System.out.println("-------------------------------");
                    System.out.println("| Lutfen raf bilgisi giriniz: |");
                    System.out.println("-------------------------------");
                    String raf = input.nextLine();
                    if (raf.isEmpty() || raf.isBlank()) {
                        throw new Exception();
                    }
                    if (raf.equals("*")) { // Ana menüye dönme kontrolü
                        System.out.println("------------------------------------");
                        System.out.println("| Ana menuye yonlendiriliyorsunuz. |");
                        System.out.println("------------------------------------");
                        return; // Ana menüye dön
                    }
                    urunler.get(id).setRaf(raf);
                    System.out.println("----------------------------------------------");
                    System.out.println("| Urun rafa basariyla yerlestirildi. Raf: " + urunler.get(id).getRaf() + " |");
                    System.out.println("----------------------------------------------");
                    gecerli = true;
                } else {
                    System.out.println("----------------------------------------");
                    System.out.println("| Gecersiz ID!. Lutfen tekrar giriniz. |");
                    System.out.println("----------------------------------------");
                }
            } catch (Exception e) {
                System.out.println("-------------------------------------------");
                System.out.println("| Lutfen bilgileri dogru sekilde giriniz. |" + e.getClass());
                System.out.println("-------------------------------------------");
            }
        } while (!gecerli);
    }

    @Override
    public void urunCikisi() {
        boolean gecerli = false;
        do {
            try {
                urunListele();
                System.out.println("-------------------------------------------------------------");
                System.out.println("| Lutfen yukarıdaki listeden ilgili urunun ID'sini giriniz: |");
                System.out.println("-------------------------------------------------------------");
                String idGirisi = input.nextLine();
                if (idGirisi.isEmpty() || idGirisi.isBlank()) {
                    throw new Exception();
                }
                if (idGirisi.equals("*")) { // Ana menüye dönme kontrolü
                    System.out.println("------------------------------------");
                    System.out.println("| Ana menuye yonlendiriliyorsunuz. |");
                    System.out.println("------------------------------------");
                    return; // Ana menüye dön
                }
                int id = Integer.parseInt(idGirisi);
                if (urunler.containsKey(id)) {
                    System.out.println("----------------------------------------------------------");
                    System.out.println("| Lutfen cikis yapmak istediginiz urun miktarini giriniz: |");
                    System.out.println("----------------------------------------------------------");
                    String cikisMiktar = input.nextLine();
                    if (cikisMiktar.isEmpty() || cikisMiktar.isBlank()) {
                        throw new Exception();
                    }
                    if (cikisMiktar.equals("*")) { // Ana menüye dönme kontrolü
                        System.out.println("------------------------------------");
                        System.out.println("| Ana menuye yonlendiriliyorsunuz. |");
                        System.out.println("------------------------------------");
                        return; // Ana menüye dön
                    }
                    int miktar = Integer.parseInt(cikisMiktar);
                    if (miktar < 0) {
                        System.out.println("-----------------------------------");
                        System.out.println("| Negatif bir deger giremezsiniz! |");
                        System.out.println("-----------------------------------");
                        continue;
                    }
                    if (miktar <= urunler.get(id).getMiktar()) {
                        urunler.get(id).setMiktar(urunler.get(id).getMiktar() - miktar);
                        System.out.println("-------------------------------------------------");
                        System.out.println("| Urun cikis basariyla yapildi. Yeni miktar: " + urunler.get(id).getMiktar() + " |");
                        System.out.println("-------------------------------------------------");
                        gecerli = true;
                    } else {
                        System.out.println("------------------------------------------------------------");
                        System.out.println("Depoda bulunan urun miktari kadar urun cikisi yapabilirsiniz. \nDepodaki urun miktari: " +
                                urunler.get(id).getMiktar() + "\nCikis yapmak istediginiz miktar: " + miktar);
                        System.out.println("------------------------------------------------------------");
                    }
                } else {
                    System.out.println("----------------------------------------");
                    System.out.println("| Gecersiz ID!. Lutfen tekrar giriniz. |");
                    System.out.println("----------------------------------------");
                }
            } catch (Exception e) {
                System.out.println("-------------------------------------------");
                System.out.println("| Lutfen bilgileri dogru sekilde giriniz. |" + e.getClass());
                System.out.println("-------------------------------------------");
            }
        } while (!gecerli);
    }


}