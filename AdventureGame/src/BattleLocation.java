import java.util.Random;
import java.util.Scanner;

public class BattleLocation extends Location {
    private Obstacle obstacle;
    private String award;
    private int maxObstacle;
    private Scanner input;

    public BattleLocation(Player player, String name, Obstacle obstacle, String award, int maxObstacle) {
        super(player, name);
        this.obstacle = obstacle;
        this.award = award;
        this.maxObstacle = maxObstacle;
        this.input = new Scanner(System.in);
    }

    @Override
    public boolean onLocation() {
        int obsNumber = this.randomObstacle();
        System.out.println("Şu an buradasınız: " + this.getName());
        System.out.println("DİKKAT!!! Burada " + obsNumber + " adet " + this.getObstacle().getName() + " yaşıyor.");
        System.out.println("Burada savaşı kazanırsanız alacağınız ödül: " + this.getAward());
        System.out.println("Savaşmak istiyorsanız ekrana <S> yazın");
        System.out.println("Savaşmak istemiyorsanız ekrana <K> yazın");
        System.out.print("Seçeneğinizi buraya giriniz: ");
        String selectBattleCase = input.nextLine().toUpperCase();

        if (selectBattleCase.equals("S")) {
            System.out.println("Korkusuz asker savaşmaya her zaman hazırdır!");

            if (combat(obsNumber)) {
                this.getPlayer().setAward(this.award);
                System.out.println("TEBRİKLER !!! " + this.getAward() + " envanterinize eklendi.");
                System.out.println(this.getName() + " içindeki düşmanları yendiniz !!");
                System.out.println("Kendinizi daha güçlü hale getirme vakti");
                System.out.println();
                return true;
            } else if (this.getPlayer().getHealth() <= 0) {
                System.out.println("Düşman sizi yendi !!");
                return false;
            }

        } else {
            System.out.println("Savaştan kaçmayı tercih ettiniz");
        }
        return true;
    }

    public boolean combat(int obsNumber) {
        boolean playerFirst = randomStart();
        System.out.println("-------------------------------------------------------------");
        if (playerFirst) {
            System.out.println("#######Bu savaşa önce " + this.getPlayer().getName() + " başlayacak#######");
        } else {
            System.out.println("#######Bu savaşa önce " + this.getObstacle().getName() + " başlayacak#######");
        }
        System.out.println("--------------------------------------------------------------");
        for (int i = 1; i <= obsNumber; i++) {
            this.getObstacle().setHealth(this.getObstacle().getOriginalHealth());
            playerStatistic();
            obstacleStatistic(i);

            while (this.getPlayer().getHealth() > 0 && this.getObstacle().getHealth() > 0) {
                if ((playerFirst && this.getPlayer().getHealth() > 0 && this.getObstacle().getHealth() > 0)) {
                    this.getObstacle().setHealth(this.getObstacle().getHealth() - this.getPlayer().getTotalDamage());
                    System.out.println();
                    System.out.println("Düşmana saldırdınız!");
                    afterHit();

                    int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmors().getBlock();
                    if (obstacleDamage < 0) {
                        obstacleDamage = 0;
                    }
                    this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
                    System.out.println();
                    System.out.println("Düşman size saldırdı!");
                    afterHit();

                } else if ((!playerFirst && this.getPlayer().getHealth() > 0) && this.getObstacle().getHealth() > 0) {


                    int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmors().getBlock();
                    if (obstacleDamage < 0) {
                        obstacleDamage = 0;
                    }
                    this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
                    System.out.println();
                    System.out.println("Düşman size saldırdı!");
                    afterHit();
                    this.getObstacle().setHealth(this.getObstacle().getHealth() - this.getPlayer().getTotalDamage());
                    System.out.println();
                    System.out.println("Düşmana saldırdınız!");
                    afterHit();
                }
            }
        }

        if (this.getObstacle().getHealth() < this.getPlayer().getHealth()) {
            System.out.println("TEBRİKLER! " + this.getObstacle().getAward() + " birim para kazandınız!");
            this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getObstacle().getAward());
            System.out.println("Güncel paranız: " + this.getPlayer().getMoney());
            return true;
        } else {
            return false;
        }
    }


    public boolean randomStart() {
        Random random = new Random();
        int randomRun = random.nextInt(100) + 1;
        return (randomRun > 50 && randomRun < 100);
    }

    public void playerStatistic() {
        System.out.println("-------Oyuncunun Durumu-------");
        System.out.println();
        System.out.println(" <> Sağlığı : " + this.getPlayer().getHealth());
        System.out.println(" <> Silahı : " + this.getPlayer().getInventory().getWeapon().getName());
        System.out.println(" <> Hasarı : " + this.getPlayer().getTotalDamage());
        System.out.println(" <> Zırh : " + this.getPlayer().getInventory().getArmors().getName());
        System.out.println(" <> Blocklama : " + this.getPlayer().getInventory().getArmors().getBlock());
        System.out.println(" <> Bütçe : " + this.getPlayer().getMoney());
    }

    public void obstacleStatistic(int i) {
        System.out.println("-------" + i + " . " + this.getObstacle().getName() + " Durumu-------");
        System.out.println();
        System.out.println(" <> Sağlığı : " + this.getObstacle().getHealth());
        System.out.println(" <> Hasarı : " + this.getObstacle().getDamage());
    }

    public void afterHit() {
        System.out.println("Canınız : " + this.getPlayer().getHealth());
        System.out.println(this.getObstacle().getName() + " canı " + this.getObstacle().getHealth());
    }

    public int randomObstacle() {
        Random r = new Random();
        return r.nextInt(this.getMaxObstacle()) + 1;
    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public int getMaxObstacle() {
        return maxObstacle;
    }

    public void setMaxObstacle(int maxObstacle) {
        this.maxObstacle = maxObstacle;
    }
}