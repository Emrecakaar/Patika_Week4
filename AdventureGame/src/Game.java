import java.util.Scanner;

public class Game {

    Scanner input = new Scanner(System.in);

    public void gameStart() {
        System.out.println("Macera oyunumuza hoşgeldiniz !! ");
        System.out.println("Dikkat !! Burada yaşayacağınız her olay akıl sağlığınızla oynayabilir !!");
        System.out.print("Bir kullanıcı adı belirleyiniz : ");
        String playerName = input.nextLine();
        Player player = new Player(playerName);
        System.out.println("Sayın " + player.getName() + " bu macerada size başarılar !!");
        System.out.println("---------------------------------------------------------------------------------------------------------");
        player.selectChar();
        boolean visitedForest = false;
        boolean visitedCave = false;
        boolean visitedRiver = false;
        boolean visitedMine = false;

        Location location = null;
        while (true) {

            player.printPlayerInfo();
            System.out.println("0 - Çıkış yap ve oyunu sonlandır.");
            System.out.println("-------Güvenli Bölgeler-------");
            System.out.println("1 - Güvenli ev");
            System.out.println("2 - Mağaza");
            System.out.println("-------Savaş Bölgeleri-------");
            if (!visitedCave) {
                System.out.println("3 - Mağara");
            }
            if (!visitedForest) {
                System.out.println("4 - Orman");
            }
            if (!visitedRiver) {
                System.out.println("5 - Nehir");
            }
            if(!visitedMine) {
                System.out.println("6 - Maden");
            }

            System.out.print("Lütfen gitmek istediğiniz konumu seçiniz : ");
            int selectLocation = input.nextInt();
            switch (selectLocation) {
                case 0:
                    location = null;
                    break;
                case 1:
                    location = new SafeHouse(player);
                    break;
                case 2:
                    location = new ToolStore(player);
                    break;
                case 3:
                    if (!visitedCave) {
                        location = new Cave(player);
                        visitedCave = true;
                    } else {
                        System.out.println("Bu konumu daha önce ziyaret ettiniz. Başka bir konum seçin.");
                    }
                    break;
                case 4:
                    if (!visitedForest) {
                        location = new Forest(player);
                        visitedForest = true;
                    } else {
                        System.out.println("Bu konumu daha önce ziyaret ettiniz. Başka bir konum seçin.");
                    }
                    break;
                case 5:
                    if (!visitedRiver) {
                        location = new River(player);
                        visitedRiver = true;
                    } else {
                        System.out.println("Bu konumu daha önce ziyaret ettiniz. Başka bir konum seçin.");
                    }
                    break;
                case 6:
                    if(!visitedMine){
                        location = new Mine(player);
                        visitedMine = true;
                    } else {
                        System.out.println("Bu konumu daha önce ziyaret ettiniz. Başka bir konum seçin.");
                    }
                default:
                    System.out.println("Lütfen geçerli bir değer giriniz : ");
            }
            if (location == null) {
                System.out.println("Bu vahşi ortamdan uzaklaştırıldınız !!");
                break;
            }
            if (!location.onLocation()) {
                System.out.println("GAME OVER");
                break;
            }
        }
    }
}
