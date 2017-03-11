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
            //这里是处理不满足4个order不放进来的code。
            
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
		//这里有问题，你这么写的话，每次调用generatePick这个method就会重现简历一个pick map，应该吧这个map放在method 外面。
		Map<Integer, Order> pick = new HashMap<Integer, Order>();
		 if (this.orders.containsKey(trail)) {
			 for (int i = 0; i < 4; i++) {
				 //这里的pick map的key成为了被pick的顺序。这样意义不大，后期调回来找的时候也麻烦。可以吧KEY设置为pick的ID
				 //例如 pick.put(trail/4,this.orders.get(trail-i);
				 //这样的话由于trail的起始值是4，每pick一次+4，那么第一个pick id就,4/4=1，第二个8/2=2然后一个 pick ID下面
				 //有4个order。这样在后期就好利用了
				pick.put(4-i, this.orders.get(trail-i));
				this.orders.get(trail-i).setStatus("picked");
			 }
		
			 trail += 4; 
	
		 } else {
			 //达不到4个order停止的位置应该在你上面读csv文件的时候，而且不需要用这个timmer，我把该的内容放在上面
			 Thread.sleep(5000);
			 generatePick();
		 }
		return pick;
		
		
	}

}
