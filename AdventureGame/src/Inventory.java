public class Inventory {
    private Weapons weapon;
    private Armors armors;

    public Inventory() {
        this.weapon = new Weapons(-1, "Yumruk", 0, 0);
        this.armors = new Armors(-1, "Paçavra", 0, 0);
    }

    public Armors getArmors() {
        return armors;
    }

    public void setArmors(Armors armors) {
        this.armors = armors;
    }

    public Weapons getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapons weapon) {
        this.weapon = weapon;
    }
}
