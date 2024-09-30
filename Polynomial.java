import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.util.*;

public class Polynomial{
	double[] coe;
	int[] exp;
	
	public Polynomial(){
		coe = new double[1];
		coe[0] = 0;
		exp = new int[1];
		exp[0] = 0;
	}

	public Polynomial(double [] coe, int [] expo){
		this.coe = new double[coe.length];
		this.exp = new int[expo.length];
		for(int i = 0;i<coe.length;i++){
			this.coe[i] = coe[i];
		}
		
		for(int i = 0;i<expo.length;i++){
			this.exp[i] = expo[i];
		}
	}
	
	public Polynomial(File file){
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			String[] result = line.split("[+-]");
			
			int len = result.length;
			exp = new int[len];
			coe = new double[len];
			
			for(int i = 0; i<len;i++){
				if(result[i].contains("x")){
					String nums[] = result[i].split("x");
					coe[i] = Double.parseDouble(nums[0]);
					if(nums.length > 1){
						exp[i] = Integer.parseInt(nums[1]);
					}
				}else{
					exp[i] = 0;
					coe[i] = Double.parseDouble(result[i]);
				}
			}
			

			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void saveToFile(String file){
		 StringJoiner build = new StringJoiner("");
		 for(int i = 0; i<this.exp.length;i++){
			if(this.exp[i] == 0){
				if(this.coe[i] < 0){
				build.add("-");
				build.add(Double.toString(this.coe[i]));
				}else{
				build.add(Double.toString(this.coe[i]));
				}
			}
			
			
			else{
			if(this.coe[i] < 0){
			build.add("-");
			build.add(Double.toString(this.coe[i]));
			build.add("x");
			build.add(Integer.toString(this.exp[i]));
			}
			else{
			build.add("+");
			build.add(Double.toString(this.coe[i]));
			build.add("x");
			build.add(Integer.toString(this.exp[i]));
			}
		 }
		 String result = build.toString();
		try {
      FileWriter myWriter = new FileWriter(file);
      myWriter.write(result);
      myWriter.close();
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
	}
	}
	}
	
	public Polynomial add(Polynomial to_add){
		int longest;
		int shorter;
		if(to_add.exp.length >= this.exp.length){
			longest = to_add.exp.length;
			shorter = this.exp.length;
		}else{
			longest = this.exp.length;
			shorter = to_add.exp.length;
		}
		
		double[] new_coe = new double[longest+shorter];
		int[] new_exp = new int[longest+shorter];
		int r = 0;
		for(int i =0; i<longest; i++){
			if(i < shorter){
				
				if(this.exp[i] == to_add.exp[i]){
					new_coe[r] = this.coe[i] + to_add.coe[i];
					new_exp[r] = this.exp[i];
					r++;
				}
				if(this.exp[i] < to_add.exp[i]){
					
					new_exp[r] = this.exp[i];
					new_coe[r] = this.coe[i];
					r++;
					new_exp[r] = to_add.exp[i];
					new_coe[r] = to_add.coe[i];
					r++;
				}
				if(this.exp[i] > to_add.exp[i]){
					
					new_exp[r] = to_add.exp[i];
					new_coe[r] = to_add.coe[i];
					r++;
					new_exp[r] = this.exp[i];
					new_coe[r] = this.coe[i];
					r++;
				}
			}
			else{
				
				if(longest == this.exp.length){
					new_exp[r] = this.exp[i];
					new_coe[r] = this.coe[i];
					r++;
				}else{
					new_exp[r] = to_add.exp[i];
					new_coe[r] = to_add.coe[i];
					r++;
				}
			}
		}
		int[] final_exp = new int[r];
		double[] final_coe = new double[r];
		for(int i = 0;i<r;i++){
			final_exp[i] = new_exp[i];
			final_coe[i] = new_coe[i];
		}
		Polynomial result = new Polynomial(final_coe, final_exp);
		return result;
  }
  public double evaluate(double a){
		double result = 0;
		for(int i = 0; i < this.exp.length; i++){
			result = result + (this.coe[i] * Math.pow(a, this.exp[i]));
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

	public Polynomial multiply(Polynomial mult){
		int [] result_exp = new int[mult.exp.length*this.exp.length];
		double [] result_coe = new double[mult.exp.length*this.exp.length];
		int r = 0;
		for (int i = 0; i<mult.exp.length;i++){
			for(int j = 0; j < this.exp.length;j++){
				double coef = mult.coe[i]*this.coe[j];
				int expo = mult.exp[i] + this.exp[j];
				int n = 0;
				boolean has_match = false;
				while(n < r){
					if(result_exp[n] == expo){
						result_coe[n] = result_coe[n] + coef; 
						n = r + 1;
						has_match = true;
					}
					n++;
				}
			
				if(has_match == false){
					result_exp[r] = expo;
					result_coe[r] = coef;
					r++; 
				}
			}		
		}
		int final_exp[] = new int[r];
		double final_coe[] =new double[r];
		int j = 0;
		while(result_coe[j]!=0||result_exp[j]!=0){
			final_coe[j] = result_coe[j];
			final_exp[j] = result_exp[j];
			j++;
		}
		Polynomial final_p = new Polynomial(result_coe,result_exp);
		return final_p;
	}
	
	
}
