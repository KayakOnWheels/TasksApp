import java.util.ArrayList;
import java.util.List;

public class sort {
    public static void main(String[] args) {

        ArrayList<Integer> unsortedList = new ArrayList<>(List.of(1,2,7,9,6));
        System.out.println(sortNumbers(unsortedList));

    }

    public static ArrayList<Integer> sortNumbers (ArrayList<Integer> unsortedNumbers) {

        for(int i = 0; i<unsortedNumbers.size(); i++) {

            for(int j = i+1; j<unsortedNumbers.size(); j++) {
                if (unsortedNumbers.get(i) > unsortedNumbers.get(j)) {
                    int x = unsortedNumbers.get(i);
                    unsortedNumbers.set(i, unsortedNumbers.get(j));
                    unsortedNumbers.set(j, x);
                }
            }
        }
        return unsortedNumbers;
    }
}
