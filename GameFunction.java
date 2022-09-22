import java.util.InputMismatchException;
import java.util.Random;

/**
 * ゲームの進行に関わる機能
 */
public class GameFunction {

    private YourStatus yourStatus; // 自分のステータス
    private EnemyStatus enemyStatus; // 相手のステータス
    private int turn; // 現在のターン

    /**
     * コンストラクタ
     */
    GameFunction() {
        yourStatus = new YourStatus();
        enemyStatus = new EnemyStatus();
        turn = 0;
    }

    /**
     * ゲームを開始
     */
    public void gameStart() {
        CUIView.showMainTextWindow("Eカードを開始します。\n続行するにはキーを押してください。");
        CUIView.showInputArrow();

        try {
            Util.strScanner();
        } catch (InputMismatchException e) {
            System.out.println("(system)不正なキーが入力されました。");
        }

        gameController();
    }

    /**
     * ゲーム開始から終わりまでを管理
     */
    private void gameController() {

        // お互いのカードを選択
        selectUseCardType();

        boolean match = false;

        while (!match) {
            ++turn;
            setYourCard();
            setEnemyCard();

            System.out.println(yourStatus.getPickCard());
            System.out.println(enemyStatus.getPickCard());
            System.out.println(checkSetCard(yourStatus.getPickCard(), enemyStatus.getPickCard()));

            if (checkResult()) {
                match = true;
                break;
            }
        }
    }

    /**
     * 使用するサイドを選択
     */
    private void selectUseCardType() {
        CUIView.showMainTextWindow("あなたのカードタイプを選んでください。\n");

        // 1か2が入力されるまで応答を繰り返す
        while (yourStatus.getCardList() == null || yourStatus.getCardList().isEmpty()) {
            System.out.println("[1]: 皇帝\n[2]: 奴隷");
            CUIView.showInputArrowWithHint("1 or 2");

            try {
                switch (Util.intScanner()) {
                    case 1:
                        yourStatus.setCardList(Util.CARD_LIST_EMP_SIDE);
                        break;
                    case 2:
                        yourStatus.setCardList(Util.CARD_LIST_SLV_SIDE);
                        break;
                    default:
                        System.out.println("1か2を入力してください。");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("(system)不正な値が入力されました。");
            }
        }

        if (yourStatus.getCardList() == Util.CARD_LIST_EMP_SIDE) {
            enemyStatus.setCardList(Util.CARD_LIST_SLV_SIDE);
        } else {
            enemyStatus.setCardList(Util.CARD_LIST_EMP_SIDE);
        }

        CUIView.showMainTextWindow("こちらのカードで対戦します。\n対戦開始するにはキーを押してください。");
        System.out.println("あなた: " + Util.getCardNameList(yourStatus.getCardList()));
        System.out.println("あいて: " + Util.getCardNameList(enemyStatus.getCardList()));
        CUIView.showInputArrow();

        try {
            Util.strScanner();
        } catch (InputMismatchException e) {
            System.out.println("(system)不正なキーが入力されました。");
        }
    }

    /**
     * セットするカードを選択
     */
    private void setYourCard() {

        while (true) {
            CUIView.showMainTextWindow(CUIView.createTurnMessage(turn) + "\nセットするカード番号を選んでください。");

            System.out.println("【あなたの手札】");
            int count = 0;
            // 選択できるカード一覧を表示
            for (String cardName : Util.getCardNameList(yourStatus.getCardList())) {
                System.out.println("[" + count + "]: " + cardName);
                count++;
            }

            CUIView.showInputArrow();

            try {
                int selectNumber = Util.intScanner();

                if (selectNumber >= 0 && selectNumber < count) {
                    CUIView.showMainTextWindow("[" + Util.getCardName(yourStatus.getCardList().get(selectNumber)) + "]"
                            + "をセットします。よろしいですか？\n");
                    System.out.println("[1]: はい");
                    System.out.println("[2]: いいえ");
                    CUIView.showInputArrowWithHint("1 or 2");
                    if (Util.intScanner() == 1) {
                        yourStatus.setPickCard(yourStatus.getCardList().get(selectNumber));
                        break;
                    } else {
                        // System.out.println("使用するカード番号を選んでください。");
                    }
                } else {
                    System.out.println("正しい番号を入力してください");
                }
            } catch (InputMismatchException e) {
                System.out.println("正しい番号を入力してください");
            }
        }
    }

    /**
     * 相手側のカードをセット
     */
    private void setEnemyCard() {

        int enemyPick = new Random().nextInt(enemyStatus.getCardList().size());

        // System.out.println(enemyPick);
        System.out.println(Util.getCardNameList(enemyStatus.getCardList()).get(enemyPick));

        enemyStatus.setPickCard(enemyStatus.getCardList().get(enemyPick));
    }

    /**
     * 結果を判定
     * 
     * @param yourCard  自分のセットしたカード
     * @param enemyCard 相手のセットしたカード
     * @return 結果(0:勝ち、1:負け、2:引き分け)
     */
    private int checkSetCard(int yourCard, int enemyCard) {

        int[][] result = {
                { 2, 1, 0 },
                { 0, 2, 1 },
                { 1, 0, 2 }
        };

        return result[yourCard][enemyCard];
    }

    /**
     * カードを見せ合った結果を確認
     * 
     * @return 結果 true:勝敗決定、false:引き分け(継続)
     */
    private boolean checkResult() {
        int result = checkSetCard(yourStatus.getPickCard(), enemyStatus.getPickCard());

        System.out.println(Util.getCardName(yourStatus.getPickCard()));

        switch (result) {
            case 0:
                CUIView.showMainTextWindow("お前の勝ちだ。\nConglatslation");
                CUIView.showSubText("あなたのセットしたカード: " + Util.getCardName(yourStatus.getPickCard()));
                CUIView.showSubText("相手のセットしたカード: " + Util.getCardName(enemyStatus.getPickCard()));
                System.out.println();
                return true;
            case 1:
                CUIView.showMainTextWindow("お前の負けだ。\n");
                CUIView.showSubText("あなたのセットしたカード: " + Util.getCardName(yourStatus.getPickCard()));
                CUIView.showSubText("相手のセットしたカード: " + Util.getCardName(enemyStatus.getPickCard()));
                return true;
            default:
                CUIView.showMainTextWindow("引き分けです。\n続行するにはキーを押してください。");
                CUIView.showSubText("あなた: " + Util.getCardName(yourStatus.getPickCard()));
                CUIView.showSubText("あいて: " + Util.getCardName(enemyStatus.getPickCard()));
                deletePickCard();
                CUIView.showInputArrow();
                try {
                    Util.strScanner();
                } catch (InputMismatchException e) {
                    System.out.println("(system)不正なキーが入力されました。");
                }
        }

        return false;
    }

    /**
     * セットしたカードを捨てる
     */
    private void deletePickCard() {

        yourStatus.deleteCard(yourStatus.getPickCard());
        enemyStatus.deleteCard(enemyStatus.getPickCard());

        // System.out.println(yourStatus.getCardList());
        // System.out.println(enemyStatus.getCardList());
    }
}