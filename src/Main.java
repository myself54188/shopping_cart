
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
public class Main {
	
	public static final String ADMINISTRATORS_PATH = "src\\List\\Name_list.txt"; // 管理员名单
	
	public static void main(String[] args) {
		Scanner sr = new Scanner(System.in);
		/*
		 * 测试辅助输出
		 */
		System.out.println("管理员账号密码：");
		System.out.println("5001220103 5001220103");
		System.out.println("5001220101 5001220101");
		System.out.println("5001220126 5001220126");
		
		//初始化文件内容
		Initialization.Initializationn();
		
		// 1.确认用户是客户还是管理员
		System.out.print("请输入账户：  ");
		String account = sr.next();
		System.out.print("请输入密码：  ");
		String password = sr.next();
		System.out.println("未注册的用户自动注册...");
		
		switch (Determine_identity(account, password)) {
		case 1:
			//管理员
			Pdministrators pdministrators = new Pdministrators();
			if(pdministrators.a()) {
				pdministrators.manage();
			}
			else {
				System.out.println("已退出...");
			}
			break;
		case 0:
			System.out.println("欢迎光临/*————————————————————————————————————————*/超市");
			System.out.println("祝您购物愉快！");
			Customer customer = new Customer(account, password);
			customer.function();
			System.out.println("欢迎下次光临！");
			break;
		}
		
		System.out.println("本项目由程浩然  李琳琳   蔡豪 完成");
	}
	
	
	//确认用户权限：管理员 / 用户
	private static int Determine_identity(String a, String b) {
		char[] arr = new char[21]; // 定义一个字符串数组，用于存储文件中的数据
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(ADMINISTRATORS_PATH);
			while((fileReader.read(arr)) != -1) {
				String correct = String.valueOf(arr);
				String account_password = a + " " + b;
				if(correct.equals(account_password)) {
					return 1;
				}
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
				try {
					fileReader.close();
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
		}
		return 0;
	}
}

