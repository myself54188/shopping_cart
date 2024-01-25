import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

//工具类
/*
 *	void show(String path) 				       展示path的数据
 *
 *	boolean YesorNo(String aaa, String path)   判断aaa在不在地址path中贮存
 *
 *	int length(String path)					   返回地址path的行数
 *
 *	String[][] segmentation(String path)	   拆分path的商品名称，商品数量，商品价格
 *
 *	void create(String[] name, String[] num, String[] price, String path)
 *	将三个数组组合传入path的txt文件中
 *
 *  void Modify_quantity(String name, int num, String path)
 *  改变商品数量
 *
 *	void delete(String name, String path)
 *	删除文件中的商品
 *
 *	void increase(String path1, String path2, String name)
 *	将path1中的name添加到path2中
 *
 *	double total_price(String path)
 *	计算价格
 */
public class Tool {
	// 展示库存
	protected static void show(String path) {
			char[] arr = new char[1024];
			FileReader fileReader = null;
			int num = 0;
			try {
				fileReader = new FileReader(path);
				while((num = fileReader.read(arr)) != -1) {
					System.out.print(new String(arr, 0 , num));
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			finally {
				if(fileReader != null) {
					try {
						fileReader.close();
					} catch (IOException e2) {
						throw new RuntimeException("读取失败！");  
					}
				}
			}
	}
	
	
	//判断商品aaa是否在仓库中
	protected static boolean YesorNo(String aaa, String path) {
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(path);
			//将商品名称都写入arr数组中
			String[] arr = new String[Tool.length(path)];
			String[][] brr = Tool.segmentation(path);
			arr = brr[0];
			for(int j = 0; j < arr.length; j++) {
				if(arr[j].equals(aaa)) {
					return true;
				}
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if(fileReader != null) {
				try {
					fileReader.close();
				} catch (IOException e2) {
					throw new RuntimeException("读取失败！");  
				}
			}
		}
		return false;
	}
	
	
	//返回文件多少行
	public static int length(String path) {
		FileReader fileReader = null;
		int length = 0;
		try {
			fileReader = new FileReader(path);
			int a = 0;
			while ((a = fileReader.read()) != -1) {
				if(a == ' ') {
					length += 1;
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				fileReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return length / 2;
	}
	
	
	/*
	 * 输入：无
	 * 输出：String[][] 第一个元素是存有商品名字的String数组，第二个元素是存有商品价格的String数组
	 */
	//将文件数据拆分为商品名.商品价格和商品数量，分别放在String[]中，再放在一个二维数组中
	
	public static String[][] segmentation(String path){
		
		FileReader fileReader = null;
		String[][] arr = new String [3][];
		String[] name = new String[Tool.length(path)];
		String[] num = new String[Tool.length(path)];
		String[] price = new String[Tool.length(path)];
		String[] brr = new String[Tool.length(path)];
		
		try {
			fileReader = new FileReader(path);

			int a;
			int i = 0;
			
			name[i] = new String();
			num[i] = new String();
			price[i] = new String();
			brr[i] = new String();
			
			while((a = fileReader.read()) != -1) {
				
				if(a != '\n') {
					brr[i] += (char) a + "";
				}
				else {
					if(i < Tool.length(path) - 1) {
						i += 1;
						name[i] = new String();
						num[i] = new String();
						price[i] = new String();
						brr[i] = new String();
					}
				}
			}
			for(int j = 0; j < brr.length; j++) {
				String[] b = brr[j].split(" ");
				name[j] = b[0];
				num[j] = b[1];
				String[] c = b[2].split("￥");
				String[] d = c[1].split("\r");
				price[j] = d[0];
				
			}
			
			arr[0] = name;
			arr[1] = num;
			arr[2] = price;
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
				try {
					fileReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		return arr;
		}
	
	/*
	 * 输入：三个字符串数组,地址
	 * 输出：void
	 * 通过字符串数组构建文件
	 */
	public static void create(String[] name, String[] num, String[] price, String path) {
		String[] arr = new String[name.length];
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(path);
			for(int i = 0; i < name.length - 1; i++) {
				arr[i] = new String();
				arr[i] += name[i] + " " + num[i] + " ￥" + price[i] + "\r\n";
				fileWriter.write(arr[i]);
			}
			if(name.length - 1 >= 0) {
				arr[name.length - 1] = new String();
				arr[name.length - 1] += name[name.length - 1] + " " + num[name.length - 1] + " ￥" + price[name.length - 1];
				fileWriter.write(arr[name.length - 1]);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				fileWriter.close();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		
	}
	
	//改变仓库商品数量
	public static void Modify_quantity(String name, int num, String path) {
		
		
		String[][] arr = Tool.segmentation(path);
		String[] namearr = arr[0];
		String[] numarr = arr[1];
		
		for(int i = 0; i < namearr.length; i++) {
			if(namearr[i].equals(name)) {
				numarr[i] = num + "";
			}
		}
		Tool.create(namearr, numarr, arr[2], path);
	} 
	
	
	//删除文件中的商品
	public static void delete(String name, String path) {

		String[][] aarr = Tool.segmentation(path);
		String[] arr = aarr[0];
		String[] brr = aarr[1];
		String[] crr = aarr[2];
		int i = 0;
		for(i = 0; i < arr.length; i++) {
			if(name.equals(arr[i])) {
				break;
			}
		}
		
		String[] a1 = new String[arr.length - 1];
		String[] a2 = new String[brr.length - 1];
		String[] a3 = new String[crr.length - 1];
		
		int k = 0;
		for(int j = 0; j < arr.length; j++) {
			if(j != i) {
				a1[k] = arr[j];
				a2[k] = brr[j];
				a3[k] = crr[j];
				k += 1;
			}
		}
		arr = a1;
		brr = a2;
		crr = a3;
		
		Tool.create(arr, brr, crr, path);
	}
	
	//将path1中的name添加到path2中
	public  static void increase(String path1, String path2, String name) {
		String arr = name;
		String brr = null;
		String crr = null;
		
		String[][] drr = Tool.segmentation(path1);
		
		for(int i = 0; i < Tool.length(path1); i++) {
			if(drr[0][i].equals(name)) {
				brr = drr[1][i];
				crr = drr[2][i];
			}
		}
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(path2, true);
			fileWriter.write(arr + " " + brr + " ￥" + crr + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	//计算价格
	
	public static double total_price(String path) {
		String[][] arr = Tool.segmentation(path);
		double sum = 0;
		for(int i = 0; i < Tool.length(path); i++) {
			sum += Integer.parseInt(arr[1][i]) * Double.parseDouble(arr[2][i]);
		}
		return sum;
	}
}

