import java.util.ArrayList;
import java.util.Scanner;

public class peasoup {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num_restaurants = sc.nextInt();

        for(int i = 0; i < num_restaurants; i++) {
            int num_items = sc.nextInt();
            sc.nextLine();
            String restaurant_name = sc.nextLine();
            ArrayList<String> menu_items = new ArrayList<String>();

            for(int j = 0; j < num_items; j++) {
                String item = sc.nextLine();
                menu_items.add(item);
            }
            if(menu_items.contains("pea soup") && menu_items.contains("pancakes")) {
                System.out.println(restaurant_name);
                return;
            }
        }
        System.out.println("Anywhere is fine I guess");
    }
}
