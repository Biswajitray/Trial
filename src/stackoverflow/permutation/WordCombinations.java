package stackoverflow.permutation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordCombinations {

    String[] listWords = new String[] {

            "windows",
            "linux",
            "mac",
            "10",
            "20"

    };

    public static void main(String[] s){
        WordCombinations wc = new WordCombinations();
        wc.execute();
    }

    private void execute()
    {
        Arrays.stream(combinations(listWords)).forEach(
                wordArray -> { System.out.println(Arrays.asList(wordArray)); }
        );
    }


    public String[][] combinations (String[] ports) {
        List<String[]> combinationList = new ArrayList<String[]>();
        for ( long i = 1; i < Math.pow(2, ports.length); i++ ) {
            List<String> portList = new ArrayList<String>();
            for ( int j = 0; j < ports.length; j++ ) {
                if ( (i & (long) Math.pow(2, j)) > 0 ) {
                    portList.add(ports[j]);
                }
            }
            combinationList.add(portList.toArray(new String[0]));
        }
        return combinationList.toArray(new String[0][0]);
    }
}
