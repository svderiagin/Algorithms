public class Main {
    public static void main(String[] args) {
        String result = Services.getDateTimeCurrent("yyyyMMdd_HHmmss");
        System.out.println(result);

        String result1 = Services.getDateTimePlusDays("yyyyMMdd_HHmmss", 5);
        System.out.println(result1);
    }


}
