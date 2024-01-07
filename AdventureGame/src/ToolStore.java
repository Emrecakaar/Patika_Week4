import java.util.Scanner;

public class ToolStore extends NormalLocation {
    ToolStore(Player player) {
        super(player, "Mağaza");
    }

    Scanner input = new Scanner(System.in);

    public boolean onLocation() {
        System.out.println("Mağazaya hoşgeldiniz");
        boolean showMenu = true;
        while (showMenu) {
            System.out.println("Kendinizi bu zorlu oyunda güçlendirmek için bir şeyler satın alın");
            System.out.println("1 - Silahlar");
            System.out.println("2 - Zırhlar");
            System.out.println("3 - Çıkış yap");
            System.out.println("Silah almak için 1'i tuşlayınız");
            System.out.println("Zırh almak için 2'yi tuşlayınız");
            System.out.println("Çıkış yapmak için 3'ü tuşlayınız");
            System.out.print("Seçeneğinizi buraya yazabilirsiniz : ");
            int selectCase = Location.input.nextInt();
            while (selectCase < 1 || selectCase > 3) {
                System.out.print("Geçersiz bir değer girdiniz, tekrar deneyiniz : ");
                selectCase = Location.input.nextInt();
            }

            switch (selectCase) {
                case 1:
                    printWeapon();
                    buyWeapon();
                    break;
                case 2:
                    printArmor();
                    buyArmors();
                    break;
                case 3:
                    System.out.println("Düşmanlarınız karşısında başarılar !!");
                    showMenu = false;
                    break;
            }
        }
        return true;
    }

    public void printWeapon() {
        System.out.println("-------SİLAHLAR-------");
        for (Weapons w : Weapons.weapon()) {
            System.out.println(w.getId() + ">> numaralı silahın adı : " +
                    w.getName() + "<< bu silahın hasarı : " +
                    w.getDamage() + " >> ve bu silahın ücreti : "
                    + w.getMoney() + " << ");
        }
        System.out.println("0 - Çıkış yap");
    }


    public void buyWeapon() {

        System.out.print("Bir silah seçiniz : ");
        int selectWeaponId = input.nextInt();
        while (selectWeaponId < 0 || selectWeaponId > Weapons.weapon().length) {
            System.out.print("Geçersiz bir değer girdiniz, lütfen tekrar giriniz : ");
            selectWeaponId = input.nextInt();
        }
        if (selectWeaponId != 0) {
            Weapons selectWeapon = Weapons.getWeaponObjById(selectWeaponId);

            if (selectWeapon != null) {
                if (selectWeapon.getMoney() > this.getPlayer().getMoney()) {
                    System.out.println("Bakiyeniz yetersiz olduğu için satın alımı yapılamaz !! ");
                } else {
                    System.out.println(selectWeapon.getName() + " silahını satın aldınız.");
                    int balance = this.getPlayer().getMoney() - selectWeapon.getMoney();
                    this.getPlayer().setMoney(balance);
                    System.out.println("Kalan bakiyeniz : " + this.getPlayer().getMoney());
                    System.out.println("Önceki silahınız : " + this.getPlayer().getInventory().getWeapon().getName());
                    this.getPlayer().getInventory().setWeapon(selectWeapon);
                    System.out.println("Şu an envanterinizdeki silah : " + this.getPlayer().getInventory().getWeapon().getName());

                }
            }

        }


    }

    public void buyArmors() {

        System.out.print("Bir zırh seçiniz : ");
        int selectArmorsId = input.nextInt();
        while (selectArmorsId < 0 || selectArmorsId > Weapons.weapon().length) {
            System.out.print("Geçersiz bir değer girdiniz, lütfen tekrar giriniz : ");
            selectArmorsId = input.nextInt();
        }

        if (selectArmorsId != 0) {
            Armors selectArmor = Armors.getArmorObjById(selectArmorsId);

            if (selectArmor != null) {
                if (selectArmor.getPrice() > this.getPlayer().getMoney()) {
                    System.out.println("Bakiyeniz yetersiz olduğu için satın alımı yapılamaz !! ");
                } else {
                    System.out.println(selectArmor.getName() + " zırhını satın aldınız.");
                    int balance = this.getPlayer().getMoney() - selectArmor.getPrice();
                    this.getPlayer().setMoney(balance);
                    System.out.println("Kalan bakiyeniz : " + this.getPlayer().getMoney());
                    System.out.println("Önceki zırhınız : " + this.getPlayer().getInventory().getArmors().getName());
                    this.getPlayer().getInventory().setArmors(selectArmor);
                    System.out.println("Şu an envanterinizdeki zırh : " + this.getPlayer().getInventory().getArmors().getName());
                }
            }
        }

    }

    public void printArmor() {
        System.out.println("-------ZIRHLAR-------");
        for (Armors a : Armors.armors()) {
            System.out.println(a.getId() + ">> numaralı zırhın adı : " +
                    a.getName() + "<< bu zırhın bloklama sayısı : " +
                    a.getBlock() + " >> ve bu zırhın ücreti : "
                    + a.getPrice() + " << ");
        }
        System.out.println("0 - Çıkış yap");
    }

}
