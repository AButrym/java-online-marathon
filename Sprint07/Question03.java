class Main {
    interface Strategy {
        double doOperation(int a, int b);
    }

    public static void execute(int a, int b, Strategy strategy) {
        double result = strategy.doOperation(a, b);
        System.out.println(result);
    }

    public static void addAtoB(int a, int b) {
        execute(a, b, Integer::sum);
    }

    public static void subtractBfromA(int a, int b) {
        execute(a, b, (arg1, arg2) -> arg1 - arg2);
    }

    public static void multiplyAbyB(int a, int b) {
        execute(a, b, (arg1, arg2) -> arg1 * arg2);
    }

    public static void divideAbyB(int a, int b) {
        execute(a, b, new Strategy() {
            @Override
            public double doOperation(int a, int b) {
                return a / (double) b;
            }
        });
    }
}
