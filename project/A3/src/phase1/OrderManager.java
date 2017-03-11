package phase1;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.Thread;

public class OrderManager {
	private Map<Integer, Order> orders;
	private static int trail = 4;
	
	/**
     * Creates a new empty OrderManager.
     */
	public OrderManager() {
		 orders = new HashMap<Integer, Order>();
		
	}
	
	public void readFromCSVFile(String filePath) throws FileNotFoundException {
      
        Scanner scanner = new Scanner(new FileInputStream(filePath));
        String[] record;
        Order order;
        ArrayList<Order> temOrder  = new ArrayList();
        while(scanner.hasNextLine()) {
            record = scanner.nextLine().split(",");
            order = new Order(record[0], record[1], Integer.valueOf(record[2]), 
            		Integer.valueOf(record[3]));
            //�����Ǵ�������4��order���Ž�����code��
            
//            temOrder.add(order);
//            if (temOrder.size()==4){
//            	for (int i = 0; i < 4; i++){
//            		orders.put(order.getOrderCount(), order);
//            	}
//            	temOrder.clear();
//            }
            
  
        }
        scanner.close();
	}
  

    public void add(Order order) {
    	orders.put(order.getOrderCount(), order);
    }
    
	public Map<Integer, Order> generatePick() throws InterruptedException {
		//���������⣬����ôд�Ļ���ÿ�ε���generatePick���method�ͻ����ּ���һ��pick map��Ӧ�ð����map����method ���档
		Map<Integer, Order> pick = new HashMap<Integer, Order>();
		 if (this.orders.containsKey(trail)) {
			 for (int i = 0; i < 4; i++) {
				 //�����pick map��key��Ϊ�˱�pick��˳���������岻�󣬺��ڵ������ҵ�ʱ��Ҳ�鷳�����԰�KEY����Ϊpick��ID
				 //���� pick.put(trail/4,this.orders.get(trail-i);
				 //�����Ļ�����trail����ʼֵ��4��ÿpickһ��+4����ô��һ��pick id��,4/4=1���ڶ���8/2=2Ȼ��һ�� pick ID����
				 //��4��order�������ں��ھͺ�������
				pick.put(4-i, this.orders.get(trail-i));
				this.orders.get(trail-i).setStatus("picked");
			 }
		
			 trail += 4; 
	
		 } else {
			 //�ﲻ��4��orderֹͣ��λ��Ӧ�����������csv�ļ���ʱ�򣬶��Ҳ���Ҫ�����timmer���ҰѸõ����ݷ�������
			 Thread.sleep(5000);
			 generatePick();
		 }
		return pick;
		
		
	}

}
