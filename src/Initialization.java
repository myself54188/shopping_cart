import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Initialization {
    private static final String WAREHOUSE_PATH = "src\\List\\Goods_list.txt"; // 全部商品名单
    private static final String ADMINISTRATORS_PATH = "src\\List\\Name_list.txt"; // 管理员名单


    public static void Initializationn() {

        String Goods =
                "苹果 100 ￥2.3\r\n"
                        + "橙子 200 ￥3.4\r\n"
                        + "猕猴桃 300 ￥4.5\r\n"
                        + "车厘子 400 ￥5.6\r\n"
                        + "香蕉 500 ￥7.8\r\n"
                        + "葡萄 800 ￥10\r\n"
                        + "琼浆玉液 5 ￥10000000\r\n"
                        + "宝塔 2 ￥888\r\n"
                        + "桃 700 ￥4.9";

        String Name = "5001220103 50012201035001220126 50012201265001220101 5001220101";


        File file1 = new File(WAREHOUSE_PATH);//商品类
        File file2 = new File(ADMINISTRATORS_PATH);//管理员名单

        if (file1.exists()) {
            return;
        }
        FileWriter fileWriter1 = null;
        FileWriter fileWriter2 = null;

        try {
            file1.createNewFile();
            file2.createNewFile();


            fileWriter1 = new FileWriter(file1);
            fileWriter2 = new FileWriter(file2);

            fileWriter1.write(Goods);
            fileWriter2.write(Name);
        } catch (IOException e) {

        } finally {
            try {
                fileWriter1.close();
                fileWriter2.close();
            } catch (IOException e) {

            }

        }

    }
}