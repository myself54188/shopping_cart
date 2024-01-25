
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

//管理员
public class Pdministrators{
	public static final String WAREHOUSE_PATH = "src\\Goods_list.txt"; // 全部商品名单
	/*
	public static final String ADMINISTRATORS_PATH = "E:\\java\\Shopping_Cart\\src\\shopping__Cart\\Txt_Document\\Name_list.txt"; // 管理员名单
	public static final String SHOPPING_CART_PATH = "E:\\java\\Shopping_Cart\\src\\shopping__Cart\\Txt_Document\\order_for_goods_" + account + ".txt"; // 订单名单
	public static final String COMMODITY_PATH = "E:\\java\\Shopping_Cart\\src\\Shopping_Cart\\Txt_Document\\Shopping_Cart_" + account + ".txt"; // 购物车名单
	*/

	//判断是否进行货物管理
	protected boolean a() {
		System.out.println("管理员，您好，扣“1”进行货物管理...");
		Scanner sr = new Scanner(System.in);
		int i = 0;
		try {
			i = sr.nextInt();
		} catch (InputMismatchException e) {
		}
		return i == 1;
	}
	
	//进行货物管理
	protected void manage() {
		Scanner sr = new Scanner(System.in);
		Tool.show(WAREHOUSE_PATH);//展示货物数量
		System.out.println("\n\n增加商品        请扣1...");
		System.out.println("删除商品        请扣2...");
		System.out.println("改变商品数量    请扣3...");
		System.out.println("改变商品价格    请扣4...");
		System.out.println("退出修改        请扣其他键...");
		aaaaa:
		do {
			try {
				int a = sr.nextInt();
				switch (a) {
				case 1:
					manage1();
					break;
				case 2:
					manage2();
					break;
				case 3:
					manage3();
					break;
				case 4:
					manage4();
					break;
					
				default:
					System.out.println("修改完成，退出...");
					break aaaaa;
				}
			} catch (InputMismatchException e) {
				System.out.println("修改完成，退出...");
				break aaaaa;
			}
		} while (true);
		System.out.println("修改完成后结果如下：\n\n\n");
		Tool.show(WAREHOUSE_PATH);//展示货物数量
	}
	
	protected void manage1() { // 增加商品
		Scanner sr = new Scanner(System.in);
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(WAREHOUSE_PATH, true);
			System.out.print("请输入增加商品名称：  ");
			String name = sr.next();
			if(Tool.YesorNo(name, WAREHOUSE_PATH)) {
				System.out.println("商品已存在，添加失败...");
				return;
			}
			System.out.print("请输入该商品数量：  ");
			int num;
			double price;
			try {
				 num = sr.nextInt();
				 System.out.print("请输入该商品价格：  ");
				 price = sr.nextDouble();
				 String a = name + " " + num + " ￥" + price;
				 fileWriter.write("\n" + a);
				 System.out.println("添加成功~~");
			} catch (Exception e) {
				System.out.println("输入错误，本次修改结束...");
			}
			
			
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
				try {
					fileWriter.close();
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	
	protected void manage2() { // 删除商品
		Scanner sr = new Scanner(System.in);
		System.out.print("请输入要删除的商品名称：  ");
		String a = sr.next();
		String[][] arr = Tool.segmentation(WAREHOUSE_PATH);
		String[] name = arr[0];
		String[] num = arr[1];
		String[] price = arr[2];
		
		int i = 0;
		
		for(i = 0; i < name.length; i++) {
			if(name[i].equals(a)){
				break;
			}
		}
		
		if(i == name.length) {
			System.out.println("抱歉，您想删除的商品仓库没有~~~");
			System.out.println("删除进程结束...");
			return;
		}
		Tool.delete(a, WAREHOUSE_PATH);
		System.out.println("删除成功...");
	}
		
	protected void manage3() { // 改变商品数量
		
		Scanner sr = new Scanner(System.in);
		try {
			System.out.print("请输入需要修改数量的商品名称：  ");
			String a = sr.next();
			if(!Tool.YesorNo(a, WAREHOUSE_PATH)) {
				System.out.println("该商品不存在...");
				System.out.println("修改失败~~~");
				return;
			}
			System.out.print("请输入改变后的商品数量：  ");
			int aa = sr.nextInt();
			Tool.Modify_quantity(a, aa, WAREHOUSE_PATH);
			System.out.println("修改完成~~~");
			} 
			catch (Exception e) {
		}
		
	}
	
	protected void manage4() { // 改变商品价格
		Scanner sr = new Scanner(System.in);
		System.out.print("请输入需要修改价格的商品名称：  ");
		try {
			String a = sr.next();
		if(!Tool.YesorNo(a, WAREHOUSE_PATH)) {
			System.out.println("该商品不存在...");
			System.out.println("修改失败~~~");
			return;
		}
		System.out.print("请输入改变后的商品价格：  ");
		int aa = sr.nextInt();
		String[][] arr = Tool.segmentation(WAREHOUSE_PATH);
		String[] name = arr[0];
		String[] price = arr[2];
		
		for(int i = 0; i < name.length; i++) {
			if(name[i].equals(a)) {
				price[i] = aa + "";
			}
		}
		Tool.create(name, arr[1], price, WAREHOUSE_PATH);
		System.out.println("修改完成~~~");
		} 
		catch (Exception e) {
		}
	}
}
