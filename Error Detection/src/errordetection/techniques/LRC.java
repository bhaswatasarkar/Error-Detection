package errordetection.techniques;

public class LRC {//even parity is used
	//FRAMES codeword_list = new FRAMES();
	
	//take a data as FRAMES(argument) and return the codewords considering each packet of the FRAMES
	//using LRC
		public static FRAMES createCodeword(FRAMES dataword_list)
		{
			int i,j,count;
			
			StringBuffer tempstr = new StringBuffer();
			
			for(j=0;j<dataword_list.list.get(0).length();j++) {
				count = 0;
				for(i=0;i<dataword_list.list.size();i++) {
					if(dataword_list.list.get(i).charAt(j) == '1')
						count++;;
				}
				
				if (count%2==0)
					tempstr.append('0');
				else
					tempstr.append('1');	
		}
			FRAMES codeword_list = new FRAMES();
			
			for(i=0;i<dataword_list.list.size();i++) {
				codeword_list.list.add(dataword_list.list.get(i));
			}
			
			codeword_list.list.add(tempstr.toString());
			return codeword_list;//returning codeword list
		}
		
		
		//take the list of frames as argument and checks if its a perfect codeword list
		public static boolean checkCodeword(FRAMES codeword_list) {
			
			int i,j,count;
			
			//StringBuffer tempstr = new StringBuffer();
			
			for(j=0;j<codeword_list.list.get(0).length();j++) {
				count = 0;
				for(i=0;i<codeword_list.list.size();i++) {
					if(codeword_list.list.get(i).charAt(j) == '1')
						count++;;
				}
				
				if (count%2==1)
					return false;	
		}
			return true;

		}
	}
