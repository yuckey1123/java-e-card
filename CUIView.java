/**
 * CUIの見た目に関する処理
 */
public class CUIView {

    /**
     * テキストウィンドウを表示する
     * 
     * @param message 表示するメッセージ
     */
    public static void showMainTextWindow(String message) {
        consoleClear();
        System.out.println("★========================================★");
        System.out.println(message);
        System.out.println("★========================================★");
    }

    /**
     * シンプルなメッセージ表示
     * 
     * @param message 表示するメッセージ
     */
    public static void showSubText(String message) {
        System.out.println(message);
    }

    /**
     * ユーザーに入力を促すアローを表示
     */
    public static void showInputArrow() {
        System.out.print("->");
    }

    /**
     * ユーザーに入力を促すアローと入力ヒントを表示
     * 
     * @param hint ヒントメッセージ
     */
    public static void showInputArrowWithHint(String hint) {
        System.out.print("(" + hint + ") ");
        System.out.print("->");
    }

    /**
     * CUIの表示をクリアする
     */
    public static void consoleClear() {
        System.out.print("\033[H\033[2J");
    }

    /**
     * 現在のターンを知らせるメッセージ作成
     * 
     * @param turn 表示するターン(現在のターン)
     * @return 作成したメッセージ(例: [1ターン])
     */
    public static String createTurnMessage(int turn) {
        return "[" + turn + "ターン]";
    }

    /**
     * 『...』を表示(約3秒のアニメーション付き)
     */
    public static void showPointAnnimation() {
        int i = 0;

        try {
            while (i++ < 3) {
                System.out.print(".");
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {

        }
    }
}