import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * 汎用的なパラメーターやメソッドをまとめたクラス
 */
public class Util {

    private static final Map<Integer, String> CARD_LIST = new HashMap<Integer, String>() {{
        put(0, "市民");
        put(1, "皇帝");
        put(2, "奴隷");
    }};

    public static final List<Integer> CARD_LIST_EMP_SIDE = new LinkedList<>(Arrays.asList(0, 0, 0, 0, 1));
    public static final List<Integer> CARD_LIST_SLV_SIDE = new LinkedList<>(Arrays.asList(0, 0, 0, 0, 2));

    public static Scanner SCANNER;

    /**
     * カード名一覧を取得
     * @param cardList
     */
    public static List<String> getCardNameList(List<Integer> cardList) {

        List<String> result = new ArrayList<>();
        for(int cardID : cardList) {
            if(cardID <= 2 || cardID >= 0) {
                result.add(CARD_LIST.get(cardID));
            }
        }

        return result;
    }

    /**
     * 指定されたカード名を取得
     * 
     * @param id カードID
     * @return idで指定したカードの名称
     */
    public static String getCardName(int id) {
        return CARD_LIST.get(id);
    }

    /**
     * カード一覧をコンソールに表示
     * @param cardList
     */
    public static void showCardList(List<Integer> cardList) {

        System.out.println(getCardNameList(cardList));
    }

    /**
     * 文字列入力スキャナー実行
     * 
     * @return 入力した文字列
     */
    public static String strScanner() {

        String result = SCANNER.next();
        return result;
    }

    /**
     * 数値入力スキャナー実行
     * 
     * @return 入力した数値
     */
    public static int intScanner() {

        int result = SCANNER.nextInt();
        return result;
    }
}