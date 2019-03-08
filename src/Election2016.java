public class Election2016 {

    public int getD_votes() {
        return D_votes;
    }

    public void setD_votes(int d_votes) {
        D_votes = d_votes;
    }

    public int getR_votes() {
        return R_votes;
    }

    public void setR_votes(int r_votes) {
        R_votes = r_votes;
    }

    public int getTotal_votes() {
        return total_votes;
    }

    public void setTotal_votes(int total_votes) {
        this.total_votes = total_votes;
    }

    private int D_votes;
    private int R_votes;
    private int total_votes;

    public Election2016(int d_votes, int r_votes, int total_votes) {
        D_votes = d_votes;
        R_votes = r_votes;
        this.total_votes = total_votes;
    }
}
