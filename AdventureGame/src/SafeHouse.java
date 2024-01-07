public class SafeHouse extends NormalLocation {
    public SafeHouse(Player player) {
        super(player, "Güvenli ev");
    }

    @Override
    public boolean onLocation() {
        System.out.println("Güvenli evdesiniz ");
        System.out.println("Burada canınız yenilenecektir.");
        this.getPlayer().upgradeHealth();
        System.out.println("Canınız yenilenmiştir : " + this.getPlayer().getOriginalHealth());
        this.getPlayer().useInventory();
        return true;
    }
}
