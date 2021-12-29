package errordetection.techniques;

public class VRC {//even parity is used
	
	
	//take a data as FRAMES(argument) and return the codewords considering each packet of the FRAMES
	//using VRC
	public static FRAMES createCodeword(FRAMES dataword_list)
	{
		//VRC
		int i,j,count;
		
		FRAMES codeword_list = new FRAMES();
		
		for(i=0;i<dataword_list.list.size();i++){
			count = 0;
			for(j=0;j<dataword_list.list.get(0).length();j++)
			{
				if(dataword_list.list.get(i).charAt(j)=='1')
				{
					count++;
				}
			}
			if (count%2==0)//redundancy is added to each packet
				codeword_list.list.add(dataword_list.list.get(i)+'0');
			else
				codeword_list.list.add(dataword_list.list.get(i)+'1');
		}
	
		return codeword_list;//returning list of codewords
	}
	
	
	//take FRAMES as argument(list of codewords) and returning boolean
	public static boolean checkCodeword(FRAMES codeword_list)
	{
		int i,j,count;
		
		for(i=0;i<codeword_list.list.size();i++) {
			count = 0;
			for(j=0;j<codeword_list.list.get(i).length();j++)
			{
				if(codeword_list.list.get(i).charAt(j)=='1')
				{
					count++;
					//System.out.println(count);
				}
			}

			if (count%2==1)
				return false;//error is detected in a packet
	}
		return true;//no error detected
}
	
}

