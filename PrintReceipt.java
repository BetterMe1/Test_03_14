class Supermarket{
	private int shirtNum = 100;//超市现有的
	private float shirtPrice = 245F;
	private int shirtIntegration = 10;
	private int sneakersNum = 100;
	private float sneakersPrice = 570F;
	private int sneakersIntegration = 20;
	private int racketNum = 50;
	private float racketPrice = 320F;
	private int racketIntegration = 15;
	private int discount = 8;
	public int getShirtNum(){
		return shirtNum;
	}
	public float getShirtPrice(){
		return shirtPrice;
	}
	public int updataShirtNum(int bshNum){
		if(shirtNum >= bshNum){
			this.shirtNum = shirtNum - bshNum;
			return shirtNum;
		}else{
			System.out.println("需求商品数大于超市存货，不能购买");
			return -1;
		}
	}
	public int getShirtIntegration(){
		return shirtIntegration;
	}
	public int getSneakersNum(){
		return sneakersNum;
	}
	public float getSneakersPrice(){
		return sneakersPrice;
	}
	public int updataSneakersNum(int bsnNum){
		if(sneakersNum >= bsnNum){
			this.sneakersNum = sneakersNum - bsnNum;
			return sneakersNum;
		}else{
			System.out.println("需求商品数大于超市存货，不能购买");
			return -1;
		}
	}
	public int getSneakersIntegration(){
		return sneakersIntegration;
	}
	public int getRacketNum(){
		return racketNum;
	}
	public float getRacketPrice(){
		return racketPrice;
	}
	public int updataRacketNum(int braNum){
		if(racketNum >= braNum){
			this.racketNum = racketNum - braNum;
			return racketNum;
		}else{
			System.out.println("需求商品数大于超市存货，不能购买");
			return -1;
		}
	}
	public int getRacketIntegration(){
		return racketIntegration;
	}	
	public int getDiscount(){
		return discount;
	}
	public int setDiscount(){
		return discount;
	}
}
class Receipt extends Supermarket{
	private int bshNum = 0;
	private int bsnNum = 0;
	private int braNum = 0;
	private float dollars = 0.0F;
	private float shExpense = 0;
	private float snExpense = 0;
	private float raExpense = 0;
	private float expenseSum = 0.0F;
	private int integrationSum = 0;
	public Receipt(int bshNum,int bsnNum,int braNum){
		this.bshNum = bshNum;
		this.bsnNum = bsnNum;
		this.braNum = braNum;
		this.updataShirtNum(bshNum);
		this.updataSneakersNum(bsnNum);
		this.updataRacketNum(braNum);
		shExpense = super.getShirtPrice()*this.bshNum;
		snExpense = super.getSneakersPrice()*this.bsnNum;
		raExpense = super.getRacketPrice()*this.braNum;
		expenseSum = (shExpense + snExpense + raExpense)*super.getDiscount()/10.0F ;
		integrationSum = bshNum*super.getShirtIntegration() + bsnNum*super.getSneakersIntegration() 
	                            +braNum*super.getRacketIntegration();
	}
	public boolean setDollars(int dollars){
		if(dollars >= expenseSum){
			this.dollars = dollars;
			return true;
		}
		return false;
	}
	public void printReceipt(){
		System.out.println("* * * * * * * * 消费单 * * * * * * * *");
		System.out.println("购买物品"+"\t"+"单价"+"\t"+"个数"+"\t"+"金额");
		System.out.println("T恤"+"\t\t"+"￥"+super.getShirtPrice()+"\t "+this.bshNum+"\t"+"￥"+shExpense);
		System.out.println("网球鞋"+"\t\t"+"￥"+super.getSneakersPrice()+"\t "+this.bsnNum+"\t"+"￥"+snExpense);
		System.out.println("网球拍"+"\t\t"+"￥"+super.getRacketPrice()+"\t "+this.braNum+"\t"+"￥"+raExpense);
		System.out.println();
		System.out.println("折扣:"+"\t"+super.getDiscount()+"折");
		System.out.println("消费总金额"+"\t\t"+"￥"+expenseSum);
		System.out.println("实付金额"+"\t"+"￥"+this.dollars);
		System.out.println("找钱"+"\t"+"￥"+(this.dollars - expenseSum));
		System.out.println("本次购物所获得的积分："+"\t"+this.integrationSum);
	}
}
public class PrintReceipt{
	public static void main(String[] args){
		Receipt r = new Receipt(2,1,1);
		if(r.setDollars(1500)){
			r.printReceipt();
		}else{
			System.out.println("钱不够");
		}
	}
}