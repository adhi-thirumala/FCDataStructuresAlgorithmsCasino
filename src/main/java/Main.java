import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        RandomOrgGenerator.setAPIKEY(args[0]);
        MasterFrame game = new MasterFrame();
    }
}
