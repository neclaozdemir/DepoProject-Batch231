package myProject;

public class Kullanici {
    private String isim;
    private String kullaniciAdi;
    private String email;
    private String password;


    public Kullanici(String isim, String kullaniciAdi, String email, String password) {
        this.isim = isim;
        this.kullaniciAdi = kullaniciAdi;
        this.email = email;
        this.password = password;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public void setKullaniciAdi(String kullaniciAdi) {
        this.kullaniciAdi = kullaniciAdi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Kullanici{" +
                "isim='" + isim + '\'' +
                ", kullaniciAdi='" + kullaniciAdi + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
