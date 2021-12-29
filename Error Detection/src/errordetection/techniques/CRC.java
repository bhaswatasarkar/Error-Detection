package errordetection.techniques;

import java.util.ArrayList;

public class CRC{
	
	//FRAMES codeword_list = new FRAMES();
	public static String divisor = "10011";//CRC-4-ITU//x^4+x+1
	
	/*CRC(String str){
		divisor = str;
	}*/
	//xor of two strings of binary values
	private static String xor(String str1, String str2) {//length of str1 and str2 must be same
		int i;
		StringBuffer temp = new StringBuffer();
		for (i=0;i<str1.length();i++)
		{
			if(str1.charAt(i)=='0' && str2.charAt(i)=='0'  ||  str1.charAt(i)=='1' && str2.charAt(i)=='1')//same for both
				temp.append('0');
			else
				temp.append('1');
		}
		return temp.toString();
	}
	
	
	//modulo-2 division for crc
	private static String binaryDivision(String dividendarg){//return remainder
		String dividend = new String(dividendarg);
		int i, len = divisor.length();
		/*for(i=0;i<len-1;i++){
			dividend = dividend + '0';
		}*/
		
		for(i=0;i<dividend.length()-len+1;i++){
			String temp = new String(dividend.substring(i, i+len));
			if(temp.charAt(0)=='1') {
				dividend = dividend.substring(0,i) + CRC.xor(temp, divisor) + dividend.substring(i+len);
			}
			}
		return dividend.substring(dividend.length()-len+1);//return remainder
			
	}
	
	//take a data as FRAMES(argument) and return the codewords considering each packet of the FRAMES
	//CRC
	public static FRAMES createCodeword(FRAMES dataword_list)
	{
		int i,j;
		FRAMES codeword_list = new FRAMES();
		//System.out.println(dataword_list.list);
		codeword_list.list = new ArrayList<String>(dataword_list.list);
		
		for(i=0;i<4;i++) {
			for(j=0;j<CRC.divisor.length()-1;j++) {
			codeword_list.list.set(i,codeword_list.list.get(i).toString()+"0");
			};
		}
		//System.out.println(codeword_list.list);
	
		for(i=0;i<codeword_list.list.size();i++){
			codeword_list.list.set(i, dataword_list.list.get(i)+binaryDivision(codeword_list.list.get(i)));
		}
		//System.out.println(codeword_list.list);
		return codeword_list;//returning codeword
	}
	
	//taking the codeword as argument and returning false if erroneous ,returning true if no error is found
	public static boolean checkCodeword(FRAMES codeword_list) {
		
		int i,j;
		FRAMES temp = new FRAMES();
		
		for(i=0;i<codeword_list.list.size();i++){
			j=0;
			temp.list.add(binaryDivision(codeword_list.list.get(i)));
			while(j<divisor.length()-1)
			{
				if(temp.list.get(i).charAt(j)=='1')
					return false;
				j++;
			}
		}
		return true;
		
		
	}
	
}