package demo.matmul;

public enum MatrixSize {
    一二五(125),
    二五零(250),
    五百(500),
    一千(1000),
    两千(2000),
    四千(4000),
    ;

    
    @Override
    public String toString() {
        return "" + value;
    }

    public static MatrixSize fromInt(int value) {
        for (MatrixSize size : MatrixSize.values()) {
            if (size.value == value) {
                return size;
            }
        }
        throw new IllegalArgumentException("No such matrix size: " + value);
    }
    public static boolean isValid(int value) {
        for (MatrixSize size : MatrixSize.values()) {
            if (size.value == value) {
                return true;
            }
        }
        return false;
    }

    int value;

    MatrixSize(int value) {
        this.value = value;
    }
}
