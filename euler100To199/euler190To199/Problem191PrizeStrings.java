package euler100To199.euler190To199;

public class Problem191PrizeStrings {

    private static final int O = 0;
    private static final int A = 1;
    private static final int L = 2;
    private static final int DAYS = 30;

    public static void main(String[] args) {
        long[][][] late = new long[30][3][3];
        long[][][] timely = new long[30][2][2];

        late[1][L][A] = 1;
        late[1][L][O] = 1;
        late[1][O][L] = 1;
        late[1][A][L] = 1;

        timely[1][A][A] = 1;
        timely[1][A][O] = 1;
        timely[1][O][A] = 1;
        timely[1][O][O] = 1;

        for (int i = 2; i < DAYS; i++) {
            timely[i][A][A] += timely[i-1][O][A]; // cannot have 3 absences in a row
            timely[i][A][O] += timely[i-1][O][A] + timely[i-1][A][A];
            timely[i][O][A] += timely[i-1][O][O] + timely[i-1][A][O];
            timely[i][O][O] += timely[i-1][O][O] + timely[i-1][A][O];

            late[i][A][L] += timely[i-1][A][A] + timely[i-1][O][A]; // cannot have prior L
            late[i][O][L] += timely[i-1][O][O] + timely[i-1][A][O]; // cannot have prior L
            late[i][L][A] += late[i-1][O][L] + late[i-1][A][L];
            late[i][L][O] += late[i-1][O][L] + late[i-1][A][L];
            late[i][O][O] += late[i-1][O][O] + late[i-1][A][O] + late[i-1][L][O];
            late[i][A][O] += late[i-1][O][A] + late[i-1][A][A] + late[i-1][L][A];
            late[i][O][A] += late[i-1][O][O] + late[i-1][A][O] + late[i-1][L][O];
            late[i][A][A] += late[i-1][O][A] + late[i-1][L][A]; // cannot have 3 absences in a row
        }
        System.out.println(timely[29][O][O]
                        + timely[29][O][A]
                        + timely[29][A][A]
                        + timely[29][A][O]
                        + late[29][A][O]
                        + late[29][A][A]
                        + late[29][O][O]
                        + late[29][O][A]
                        + late[29][L][O]
                        + late[29][L][A]
                        + late[29][A][L]
                        + late[29][O][L]
        );
    }
}
