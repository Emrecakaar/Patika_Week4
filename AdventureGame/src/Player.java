import java.util.Random;
import java.util.Scanner;

public class Player {

    private int damage;
    private int health;
    private int originalHealth;
    private int money;
    private String name;
    private String charName;

    private Inventory inventory;
    private String award;

    Scanner input = new Scanner(System.in);

    Player(String name) {
        this.name = name;
        this.inventory = new Inventory();
    }

    public int getTotalDamage() {
        return damage + this.getInventory().getWeapon().getDamage();
    }

    public int getDamage() {

        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharName() {
        return charName;
    }

    public void setCharName(String charName) {
        this.charName = charName;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public int getOriginalHealth() {
        return originalHealth;
    }

    public void setOriginalHealth(int originalHealth) {
        this.originalHealth = originalHealth;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public void selectChar() {
        Samurai samurai = new Samurai();
        Knight knight = new Knight();
        Archer archer = new Archer();

        GameChar[] charList = {new Samurai(), new Knight(), new Archer()};

        for (GameChar gameChar : charList) {
            System.out.println("KARAKTERLER");
            System.out.println("Karakterin ID nosu : " + gameChar.getID() +
                    "\t Karakter ismi : " + gameChar.getName() +
                    "\t Karakterin sağlığı : " + gameChar.getHealth() +
                    "\t Karakterin hasarı : " + gameChar.getDamage() +
                    "\t Karakterin sahip olduğu para : " + gameChar.getMoney());
        }

        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.print("Lütfen bir karakter seçiniz : ");
        int selectChar = input.nextInt();

        switch (selectChar) {

            case 1:
                initPlayer(new Samurai());
                break;
            case 2:
                initPlayer(new Knight());
                break;
            case 3:
                initPlayer(new Archer());
                break;
            default:
                initPlayer(new Samurai());
        }
        System.out.println("<> Seçtiğiniz karakter : " + this.getCharName() +
                " <> Sağlığı : " + this.getHealth() +
                " <> Hasar : " + this.getDamage() +
                " <> Parası : " + this.getMoney());
    }


    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public void printPlayerInfo() {
        System.out.println("<> Silahınız : " + this.inventory.getWeapon().getName() +
                " <> Kuşandığınız zırhın blok sayısı : " + this.getInventory().getArmors().getBlock() +
                " <> Sağlığınız : " + this.getHealth() +
                " <> Vereceğiniz hasar : " + this.getTotalDamage() +
                " <> Paranız : " + this.getMoney() +
                " <> Envanterinizde bulunan ürün : " + this.getAward());

    }

    public void upgradeHealth() {
        if (this.getHealth() < this.originalHealth) {
            this.setHealth(this.originalHealth);
        }
    }

    boolean useFood = false;
    boolean useFirewood = false;
    boolean useWater = false;
    boolean useMuhimmat = false;

    public void useInventory() {
        if (this.getAward() == null) {
            System.out.println("Envanterinizde ürün bulunmamaktadır.");
        } else {
            System.out.println("<> 1 - Food = +2 Para <> ");
            System.out.println("<> 2 - Firewood = +2 Hasar <>");
            System.out.println("<> 3 - Water = +1 Bloklama <>");
            System.out.println("<> 4 - Mühimmat = Silah zırh veya para");
            System.out.print("Kullanmak istediğiniz envanteri seçiniz : ");
            int selectedInventory = input.nextInt();
            switch (selectedInventory) {
                case 1:
                    if (this.award.equals("Food") && !useFood) {
                        this.setMoney(this.getMoney() + 2);
                        useFood = true;
                    } else {
                        System.out.println("Elinizde bu envanter bulunmamaktadır veya daha önce bu ürünü kullandınız.");
                    }

                    break;

                case 2:
                    if (this.award.equals("Firewood") && !useFirewood) {
                        this.setDamage(this.getDamage() + 2);
                        useFirewood = true;
                    } else {
                        System.out.println("Elinizde bu envanter bulunmamaktadır veya daha önce bu ürünü kullandınız.");
                    }
                    break;
                case 3:
                    if (this.award.equals("Water") && !useWater) {
                        this.getInventory().getArmors().setBlock(this.getInventory().getArmors().getBlock() + 1);
                        useWater = true;
                    } else {
                        System.out.println("Elinizde bu envanter bulunmamaktadır veya daha önce bu ürünü kullandınız.");
                    }
                    break;
                case 4:
                    Random random = new Random();
                    if (this.award.equals("Mühimmat") && !useMuhimmat) {
                        int randomAward = random.nextInt(100) + 1;
                        if (randomAward <= 7) {
                            this.getInventory().getWeapon().setDamage(this.getInventory().getWeapon().getDamage() + 2);
                        }
                        if (randomAward > 7 && randomAward < 10) {
                            this.getInventory().getWeapon().setDamage(this.getInventory().getWeapon().getDamage() + 3);
                        }
                        if (randomAward >= 10 && randomAward < 14) {
                            this.getInventory().getWeapon().setDamage(this.getInventory().getWeapon().getDamage() + 7);
                        }
                        if (randomAward >= 14 && randomAward < 21) {
                            this.getInventory().getArmors().setBlock(this.getInventory().getArmors().getBlock() + 1);
                        }
                        if (randomAward >= 21 && randomAward < 25) {
                            this.getInventory().getArmors().setBlock(this.getInventory().getArmors().getBlock() + 3);
                        }
                        if (randomAward >= 25 && randomAward < 28) {
                            this.getInventory().getArmors().setBlock(this.getInventory().getArmors().getBlock() + 5);
                        }
                        if (randomAward >= 28 && randomAward < 41) {
                            this.setMoney(this.getMoney() + 1);
                        }
                        if (randomAward >= 41 && randomAward < 49) {
                            this.setMoney(this.getMoney() + 5);
                        }
                        if (randomAward >= 49 && randomAward < 55) {
                            this.setMoney(this.getMoney() + 10);
                        }
                        if (randomAward >= 55 && randomAward <= 100) {
                            System.out.println("Üzgünüm !!! Buradan ödül kazanamadınız.");
                        }
                    }
                    break;

                default:
                    System.out.println("Elinizde buna göre envanter bulunmamaktadır!!!");

                    System.out.println();
            }
        }
    }

    public void initPlayer(GameChar gameChar) {
        this.setDamage(gameChar.getDamage());
        this.setHealth(gameChar.getHealth());
        this.setMoney(gameChar.getMoney());
        this.setCharName(gameChar.getName());
        this.setOriginalHealth(gameChar.getHealth());
    }
}

