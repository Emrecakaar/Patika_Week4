public class Weapons {
    private int id;
    private String name;
    private int damage;
    private int money;

    public Weapons(int id, String name, int damage, int money) {
        this.id = id;
        this.name = name;
        this.damage = damage;
        this.money = money;
    }

    public static Weapons[] weapon() {
        Weapons[] weaponList = new Weapons[3];
        weaponList[0] = new Weapons(1, "Tabanca", 2, 5);
        weaponList[1] = new Weapons(2, "Kılıç", 3, 35);
        weaponList[2] = new Weapons(3, "Tüfek", 7, 45);
        return weaponList;
    }

    public static Weapons getWeaponObjById(int id) {
        for (Weapons w : Weapons.weapon()) {
            if (w.getId() == id) {
                return w;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
