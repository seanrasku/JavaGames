package cs310.games;
final class BestMove
{
    int i;
    int j;
    int val;

    // value-only constructor: no position information
    public BestMove(int v )
    { this( v, -6, -7 ); }  // provide illegal position to detect accidental use

    public BestMove(int v, int r, int c )
    { val = v; i = r; j = c; }

    @Override
    public String toString() {
        return "BestMove{" +
                "i=" + i +
                ", j=" + j +
                ", val=" + val +
                '}';
    }
}
