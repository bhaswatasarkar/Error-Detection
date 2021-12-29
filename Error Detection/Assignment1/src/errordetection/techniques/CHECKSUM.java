package errordetection.techniques;

public class CHECKSUM{//implementing the checksum method
	//FRAMES codeword_list = new FRAMES();
	
	//take a data as FRAMES(argument) and return the codewords considering each packet of the FRAMES	
	
	//CHECKSUM
	public static FRAMES createCodeword(FRAMES dataword_list)
	{
int i,j,carry=0;
		
		StringBuffer sum = new StringBuffer();
		//StringBuffer checksum = new StringBuffer();
		for(j=dataword_list.list.get(0).length()-1;j>=0;j--)
			sum.append('0');
		//System.out.println(sum);//////////////////////
		
		
		//implementing addition of binary numbers
		for(j=dataword_list.list.get(0).length()-1;j>=0;j--) {
			for(i=0;i<dataword_list.list.size();i++) {
				if(dataword_list.list.get(i).charAt(j) == '1')
					carry++;
			}
			
			if (carry%2==0) {
				sum.setCharAt(j, '0');
				carry = carry/2;
				}
			else{
				sum.setCharAt(j, '1');
				carry = carry/2;
				}			
			}
		
		//System.out.println(sum);//////////////////////

		
		//adding the carry generated to the sum
		if(carry!=0) {
			for(j=sum.length()-1;j>=0;j--){
				carry = carry + Character.getNumericValue(sum.charAt(j));

			if (carry%2==0) { 
				sum.setCharAt(j, '0');
				carry = carry/2;
				}
			else{
				sum.setCharAt(j, '1');
				carry = carry/2;
				}
			}}
			
		//System.out.println(sum);
			
			//checksum by inverting the sum
			for(j=sum.length()-1;j>=0;j--) {
				if(sum.charAt(j)=='0')
					sum.setCharAt(j, '1');
				else
					sum.setCharAt(j, '0');
			}	

			
			FRAMES codeword_list = new FRAMES();
			for(i=0;i<dataword_list.list.size();i++) {
				codeword_list.list.add(dataword_list.list.get(i));
			}
			
			codeword_list.list.add(sum.toString());
			return codeword_list;//returning codeword list
		
	}
	
	
	public static boolean checkCodeword(FRAMES dataword_list) {
		
		int i,j,carry=0;
		
		StringBuffer sum = new StringBuffer();
		//StringBuffer checksum = new StringBuffer();
		for(j=dataword_list.list.get(0).length()-1;j>=0;j--)
			sum.append('0');
	
		
		for(j=dataword_list.list.get(0).length()-1;j>=0;j--) {
			for(i=0;i<dataword_list.list.size();i++) {
				if(dataword_list.list.get(i).charAt(j) == '1')
					carry++;
			}
			
			if (carry%2==0) {
				sum.setCharAt(j, '0');
				carry = carry/2;
				}
			else{
				sum.setCharAt(j, '1');
				carry = carry/2;
				}			
			}
		
		//System.out.println(sum);//////////////////////
		
		if(carry!=0) {
			for(j=sum.length()-1;j>=0;j--){
				carry = carry + Character.getNumericValue(sum.charAt(j));

			if (carry%2==0) { 
				sum.setCharAt(j, '0');
				carry = carry/2;
				}
			else{
				sum.setCharAt(j, '1');
				carry = carry/2;
				}
			}}
		
			for(j=sum.length()-1;j>=0;j--)
			{
				if(sum.charAt(j)=='0')
					return false;
			}
			
		return true;	
	
	}	
}


