
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

//顾客

public class Customer {

    static String account;
    String password;

    String WAREHOUSE_PATH;
    String ADMINISTRATORS_PATH;
    String SHOPPING_CART_PATH;
    String COMMODITY_PATH;

    public Customer(String account, String password) {
        Customer.account = account;
        this.password = password;
        this.WAREHOUSE_PATH = "src\\List\\Goods_list.txt"; // 全部商品名单
        this.ADMINISTRATORS_PATH = "src\\List\\Name_list.txt"; // 管理员名单
        this.SHOPPING_CART_PATH = "src\\List\\order_for_goods_" + account + ".txt"; // 订单名单
        this.COMMODITY_PATH = "src\\List\\Shopping_cart_" + account + ".txt"; // 购物车名单

    }


    public void function() {


        File file1 = new File(SHOPPING_CART_PATH);
        File file2 = new File(COMMODITY_PATH);

        if (!file1.exists()) {
            try {
                file1.createNewFile();
            } catch (IOException e) {
            }
        }

        if (!file2.exists()) {
            try {
                file2.createNewFile();
            } catch (IOException e) {
            }
        }

        Scanner sr = new Scanner(System.in);

        System.out.println("你可进行以下操作：");
        System.out.println("【1】   查看购物车");
        System.out.println("【2】   查看订单");
        System.out.println("【3】   查看商品");
        System.out.println("【4】   修改购物车中商品数量");
        System.out.println("【5】   删除购物车中商品");
        System.out.println("【6】   将购物车中商品添加至订单");
        System.out.println("【7】   将商场中商品添加至购物车");
        System.out.println("【8】   订单支付");
        System.out.println("【9】   取消订单");
        System.out.println("其他键  离店");


        aaaaa:
        do {
            try {
                int a = sr.nextInt();
                switch (a) {
                    case 1:
                        function1();
                        break;
                    case 2:
                        function2();
                        break;
                    case 3:
                        function3();
                        break;
                    case 4:
                        function4();
                        break;
                    case 5:
                        function5();
                        break;
                    case 6:
                        function6();
                        break;
                    case 7:
                        function7();
                        break;
                    case 8:
                        function8();
                        break;
                    case 9:
                        function9();
                        break;
                    default:
                        break aaaaa;
                }
            } catch (InputMismatchException e) {
                break aaaaa;
            }
        } while (true);

    }

    //查看购物车
    private void function1() {
        System.out.println("购物车列表：");
        Tool.show(COMMODITY_PATH);
        if (Tool.length(COMMODITY_PATH) == 0) {
            System.out.print("你还没有添加商品哦~  快来添加商品吧 " + "＼＼\\\\٩( 'ω' )و //／／");
        }
        System.out.println();

    }

    //查看订单
    private void function2() {
        System.out.println("订单列表：");
        Tool.show(SHOPPING_CART_PATH);
        if (Tool.length(SHOPPING_CART_PATH) == 0) {
            System.out.print("小丁好饿，快找些商品喂给小丁吧 (✧∇✧)");
        }
        System.out.println();
    }

    //查看商品
    private void function3() {
        System.out.println("商品列表：");
        Tool.show(WAREHOUSE_PATH);
        System.out.println();
    }

    //修改购物车中商品数量
    private void function4() {
        Scanner sr = new Scanner(System.in);
        System.out.print("请问小主要修改哪件商品的数量：  ");
        String name = sr.next();
        if (Tool.length(COMMODITY_PATH) == 0) {
            System.out.println("你还没有添加商品哦~  快来添加商品吧 " + "＼＼\\\\٩( 'ω' )و //／／");
            return;
        }
        if (!Tool.YesorNo(name, COMMODITY_PATH)) {
            System.out.println("小主是不是没看清呀. 没有这件商品 (●'◡'●)");
            return;
        }
        System.out.print("请问小主要添加几件：  ");
        int num = 0;
        try {
            num = sr.nextInt();
        } catch (Exception e) {
            System.out.println("输入错误，修改结束");
            return;
        }

        String[][] aarr = Tool.segmentation(COMMODITY_PATH);
        String[] arr = aarr[0];
        String[] brr = aarr[1];
        String[] crr = aarr[2];

        for (int i = 0; i < COMMODITY_PATH.length(); i++) {
            if (arr[i].equals(name)) {
                if (Integer.parseInt(brr[i]) >= num) {
                    brr[i] = num + "";
                } else {
                    System.out.println("抱歉，商品库存不足");
                }
                break;
            }
        }

        Tool.create(arr, brr, crr, COMMODITY_PATH);
        System.out.println("修改完成");
    }


    //删除购物车中商品
    private void function5() {
        Scanner sr = new Scanner(System.in);
        System.out.print("请问小主要删除哪件商品的数量：  ");
        String name = sr.next();
        if (Tool.length(COMMODITY_PATH) == 0) {
            System.out.println("你还没有添加商品哦~  快来添加商品吧 " + "＼＼\\\\٩( 'ω' )و //／／");
            return;
        }
        if (!Tool.YesorNo(name, COMMODITY_PATH)) {
            System.out.println("小主是不是没看清呀. 没有这件商品 (●'◡'●)");
            return;
        }

        Tool.delete(name, COMMODITY_PATH);
        System.out.println("修改完成");
    }

    //将购物车中商品添加至订单
    public void function6() {
        Scanner sr = new Scanner(System.in);
        System.out.print("请输入需要添加到订单的商品名称：  ");
        String name = sr.next();

        if (Tool.length(COMMODITY_PATH) == 0) {
            System.out.println("你还没有添加商品哦~  快来添加商品吧 " + "＼＼\\\\٩( 'ω' )و //／／");
            return;
        }
        if (!Tool.YesorNo(name, COMMODITY_PATH)) {
            System.out.println("小主是不是没看清呀. 没有这件商品 (●'◡'●)");
            return;
        }

        Tool.increase(COMMODITY_PATH, SHOPPING_CART_PATH, name);
        System.out.println("添加成功");
        Tool.delete(name, COMMODITY_PATH);
        int num = 0;
        String[][] arr = Tool.segmentation(WAREHOUSE_PATH);
        for (int i = 0; i < Tool.length(WAREHOUSE_PATH); i++) {
            if (arr[0][i].equals(name)) {
                num = Integer.parseInt(arr[1][i]);
            }
        }

        int numm = 0;
        String[][] brr = Tool.segmentation(SHOPPING_CART_PATH);
        for (int i = 0; i < Tool.length(SHOPPING_CART_PATH); i++) {
            if (brr[0][i].equals(name)) {
                numm = Integer.parseInt(brr[1][i]);
            }
        }

        Tool.Modify_quantity(name, num - numm, WAREHOUSE_PATH);
    }


    //将商场中商品添加至购物车
    private void function7() {
        Scanner sr = new Scanner(System.in);
        System.out.print("请输入需要添加到购物车的商品名称：  ");
        String name = sr.next();
        if (!Tool.YesorNo(name, WAREHOUSE_PATH)) {
            System.out.println("抱歉，商品不存在...");
            return;
        }

        Tool.increase(WAREHOUSE_PATH, COMMODITY_PATH, name);
        System.out.println("添加成功");
    }

    //订单支付
    private void function8() {
        Scanner sr = new Scanner(System.in);
        System.out.println("您当前订单如下\n");
        Tool.show(SHOPPING_CART_PATH);
        System.out.println("\n是否支付 y/n");
        String a = sr.next();
        if (a.equals("y")) {
            System.out.printf("总价：%.2f元", Tool.total_price(SHOPPING_CART_PATH));
            System.out.println("\n支付成功，订单列表已清除...");
            try {
                FileWriter fileWriter = new FileWriter(SHOPPING_CART_PATH);
            } catch (IOException e) {
                // TODO 自动生成的 catch 块
                e.printStackTrace();
            }
        } else if (a.equals("n")) {
            System.out.println("取消支付成功...");
        } else {
            System.out.println("输入错误...");
        }
    }

    //取消订单
    private void function9() {
        Scanner sr = new Scanner(System.in);
        System.out.println("确认取消订单？y/n");
        String a = sr.next();
        if (a.equals("y")) {
            try {
                FileWriter fileWriter = new FileWriter(SHOPPING_CART_PATH);
            } catch (IOException e) {
                // TODO 自动生成的 catch 块
                e.printStackTrace();
            }
            System.out.println("订单列表已清空...");
        } else if (a.equals("n")) {
            System.out.println("订单列表未清空...");
        } else {
            System.out.println("输入错误...");
        }
    }
}