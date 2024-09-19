public class Polynomial{
	double[] poly;


	public Polynomial(){
		poly = new double[1];
		poly[0] = 0;
	}


	public Polynomial(double[] input){
		poly = new double[input.length];
		for(int i = 0; i < input.length; i++){
			poly[i] = input[i];
	       }
	}
	
	public Polynomial add(Polynomial to_add){
		int longest;
		int shorter;
		if(to_add.poly.length >= this.poly.length){
			longest = to_add.poly.length;
			shorter = this.poly.length;
		}else{
			longest = this.poly.length;
			shorter = to_add.poly.length;
		}
		double[] output = new double[longest];
		for(int i = 0; i < longest; i++){
			if(i < shorter){
				output[i] = this.poly[i] + to_add.poly[i];
			}else{
			if(longest == this.poly.length){
				output[i] = this.poly[i];
			}else{
				output[i] = to_add.poly[i];
				}
	      }
	}
		Polynomial result = new Polynomial(output);
		return result;
  }
  public double evaluate(double a){
		double result = 0;
		for(int i = 0; i < this.poly.length; i++){
			result = result + poly[i]*Math.pow(a,i);
		}
		return result;
  }
  
  public boolean hasRoot(double a){
	   if(this.evaluate(a)==0){
		   return true;
	   }else{
		   return false;
	   }
}
}