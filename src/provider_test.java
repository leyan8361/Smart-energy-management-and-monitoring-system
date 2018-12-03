public class provider_test {
    public static void main(String[] args) throws Exception {
        System.out.println("Now you are provider");
        System.out.println("--------------------");
        System.out.println(MotifyUser.viewUserHistory("user1", "3", "6"));
        MotifyUser.createUserDir("user5", "12345", "ikfvoid@gmail.com");
        /*System.out.println("Create a user");*/
		/*MotifyUser mu = new MotifyUser();
		if(mu.CreateFolder("user2")){
			System.out.println("You create a folder successful");
		}
		else{
			System.out.println("you are failed");
		} //测试创建文件
		
		if(mu.DeleteFolder("user2")){
			System.out.println("You delete a folder successful");
		}
		else{
			System.out.println("You are failed");
		} //测试删除文件 */
		
		/*SetTariff set = new SetTariff();
		if(set.ChangeTariff("elecprice",5)){
			System.out.println("right 4!");
		}
		else{
			System.out.println("failed");
		}//测试修改价格 */
		
		/*GenerateBill gb = new GenerateBill("user1","3");
		if(gb.BillCaculate()){
			System.out.println("you successful");
		}
		else{
			System.out.println("gb false");
		}
		
		GenerateBill gb1 = new GenerateBill("user1","4");
		if(gb1.BillCaculate()){
			System.out.println("you successful");
		}
		else{
			System.out.println("gb false");
		}*/
        //MotifyUser.createUserDir("user3");
    }
}