import java.util.List;

/**
 * 自分のカード情報
 */
public class YourStatus implements Status {

    private List<Integer> cardList;
    private int pickCard;

    @Override
    public void setCardList(List<Integer> cardList) {
        this.cardList = cardList;
    }

    @Override
    public List<Integer> getCardList() {
        return this.cardList;
    }

    @Override
    public void deleteCard(int deleteCard) {
        this.cardList.remove(deleteCard);
    }

    @Override
    public void setPickCard(int pickCard) {
        this.pickCard = pickCard;
    }

    @Override
    public int getPickCard() {
        
        return this.pickCard;
    }
}
