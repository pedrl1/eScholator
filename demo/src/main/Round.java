public class Round {
    private int roundNumber;
    private int roundMaxNumber;

    public Round(int roundNumber, int roundMaxNumber) {
        this.roundNumber = roundNumber;
        this.roundMaxNumber = roundMaxNumber;   
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public int getRoundMaxNumber() {
        return roundMaxNumber;
    }

    public void nextRound() {
        if (roundNumber < roundMaxNumber) {
            roundNumber++;
        }
    }

    public void resetRound() {
        roundNumber = 1;
    }

    public boolean isFinalRound() {
        return roundNumber == roundMaxNumber;
    }

}
