package network;

import java.util.*;
import errordetection.techniques.*;

public class User {

	public static void main(String[] args) {
		
		
		/*FRAMES temp = new FRAMES();
		//CRC c = new CRC();
		temp.list.add("10011");
		temp.list.add("00000");
		temp.list.add("10101");
		temp.list = CRC.createCodeword(temp).list;
		System.out.println(temp.list);
		temp.list.set(0, "100110010");
		System.out.println(temp.list);
		System.out.println(CRC.checkCodeword(temp));*/
		

		int i,countVRC=0,countLRC=0,countCHECKSUM=0,countCRC=0,temp = 0;
		Sender s = new Sender(args[0]);
		
			ArrayList<String> arrVRC = new ArrayList<String>(s.VRCcodeword.list);
			ArrayList<String> arrLRC = new ArrayList<String>(s.LRCcodeword.list);
			ArrayList<String> arrCHECKSUM = new ArrayList<String>(s.CHECKSUMcodeword.list);
			ArrayList<String> arrCRC = new ArrayList<String>(s.CRCcodeword.list);
			

		TransmissionMedium tm = new TransmissionMedium();			
		
		for(i=0;i<100000;i++)
		{
			s.VRCcodeword.list = new ArrayList<String>(arrVRC);
			s.LRCcodeword.list = new ArrayList<String>(arrLRC); 
			s.CHECKSUMcodeword.list = new ArrayList<String>(arrCHECKSUM);
			s.CRCcodeword.list = new ArrayList<String>(arrCRC);
			//System.out.println(s.dataword_list.list);
			//System.out.println(s.CRCcodeword.list);
			tm.pushBurstError(s, 5, '0');
			
			tm.pushBurstError(s, 8, '0');
			
			//System.out.println(s.CRCcodeword.list);
			Receiver r = new Receiver(s);
			if((r.VRCflag==0 && s.VRCcodeword.list.equals(arrVRC)== false) || (r.VRCflag==1 && s.VRCcodeword.list.equals(arrVRC)== true)) 
				countVRC++;
			//System.out.println(r.CHECKSUMflag+"       "+s.CHECKSUMcodeword.list.equals(arrCHECKSUM));
			if((r.LRCflag==0 && s.LRCcodeword.list.equals(arrVRC)== false) || (r.LRCflag==1 && s.LRCcodeword.list.equals(arrLRC)== true)) 
				countLRC++;
			if((r.CHECKSUMflag==0 && s.CHECKSUMcodeword.list.equals(arrCHECKSUM)== false) || (r.CHECKSUMflag==1 && s.CHECKSUMcodeword.list.equals(arrCHECKSUM)== true)) 
				countCHECKSUM++;
			//if((r.CHECKSUMflag==1 && s.CHECKSUMcodeword.list.equals(arrCHECKSUM)== false))
			//	countCHECKSUM++;
			if((r.CRCflag==0 && s.CRCcodeword.list.equals(arrCRC)== false) || (r.CRCflag==1 && s.CRCcodeword.list.equals(arrCRC)== true)) 
				countCRC++;
				
		}

		System.out.println("Successfully identified by VRC out of 100000 : " + (countVRC));
		System.out.println("Successfully identified by LRC out of 100000 : " + (countLRC));
		System.out.println("Successfully identified by CHECKSUM out of 100000 : " + (countCHECKSUM));
		System.out.println("Successfully identified by CRC out of 100000 : " + (countCRC));
		

		
	}

}
