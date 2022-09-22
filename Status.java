import java.util.List;

public interface Status {

    /**
     * カード一覧を更新
     * @param cardList
     */
    public void setCardList(List<Integer> cardList);

    /**
     * カード一覧を取得
     * @return
     */
    public List<Integer> getCardList();

    /**
     * カードを捨てる
     * 
     * @param deleteCard
     */
    public void deleteCard(int deleteCard);

    /**
     * 選択したカードを設定
     * @param pickCard
     */
    public void setPickCard(int pickCard);

    /**
     * 選択したカードを取得
     * @param pickCard
     */
    public int getPickCard();
}
