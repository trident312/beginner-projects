public class Percolation {

    private int n;
    private boolean[][] grid;
    private int openSites;
    private WQU uf;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Invalid input");
        }
        this.n = n;
        grid = new boolean[n][n];
        uf = new WQU(n * n + 2); // two extra virtual sites
    }

    public void open(int row, int col) {
        validateIndices(row, col);
        if (!isOpen(row, col)) {
            grid[row - 1][col - 1] = true;
            openSites++;
            int index = getIndex(row, col);
            // connect to virtual site at top
            if (row == 1) {
                uf.union(0, index);
            }
            // connect to virtual site at bottom
            if (row == n) {
                uf.union(n * n + 1, index);
            }
            // connect to neighboring open sites
            if (row > 1 && isOpen(row - 1, col)) {
                uf.union(index, getIndex(row - 1, col));
            }
            if (row < n && isOpen(row + 1, col)) {
                uf.union(index, getIndex(row + 1, col));
            }
            if (col > 1 && isOpen(row, col - 1)) {
                uf.union(index, getIndex(row, col - 1));
            }
            if (col < n && isOpen(row, col + 1)) {
                uf.union(index, getIndex(row, col + 1));
            }
        }
    }

    public boolean isOpen(int row, int col) {
        validateIndices(row, col);
        return grid[row - 1][col - 1];
    }

    public boolean isFull(int row, int col) {
        validateIndices(row, col);
        return uf.find(0) == uf.find(getIndex(row, col));
    }

    public boolean percolates() {
        return uf.find(0) == uf.find(n * n + 1);
    }

    public int numberOfOpenSites() {
        return openSites;
    }

    private int getIndex(int row, int col) {
        return (row - 1) * n + (col - 1) + 1;
    }

    private void validateIndices(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n) {
            throw new IllegalArgumentException("Invalid indices");
        }
    }

    public static void main(String[] args) {
        int n = 5; // grid size
        Percolation percolation = new Percolation(n); // create a new Percolation instance
        percolation.open(1, 1); // open site (1,1)
        percolation.open(2, 1); // open site (2,1)
        percolation.open(3, 1); // open site (3,1)
        percolation.open(4, 1); // open site (4,1)
        percolation.open(5, 1); // open site (5,1)
        boolean isPercolated = percolation.percolates(); // check if the grid percolates
        System.out.println("Does the grid percolate? " + isPercolated); // print the result

    }

}
