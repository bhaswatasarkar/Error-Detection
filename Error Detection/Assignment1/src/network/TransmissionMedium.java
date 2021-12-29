package network;

import java.util.*;

import errordetection.techniques.*;

public class TransmissionMedium{
	
	//Randomly make a position erroneous
	//Sender class object is taken as argument
	void pushBitError(Sender s)
	{
		
		
		Random rand = new Random();
		////vrc
		int i = rand.nextInt(4);
		StringBuffer temp = new StringBuffer(s.VRCcodeword.list.get(i));
		int j = rand.nextInt(9);
		if(temp.charAt(j)=='1')
			temp.setCharAt(j, '0');
		else
			temp.setCharAt(j, '1');
		s.VRCcodeword.list.set(i, temp.toString());
		
		////lrc
		i = rand.nextInt(5);
		temp = new StringBuffer(s.LRCcodeword.list.get(i));
		j = rand.nextInt(8);
		if(temp.charAt(j)=='1')
			temp.setCharAt(j, '0');
		else
			temp.setCharAt(j, '1');
		s.LRCcodeword.list.set(i, temp.toString());
		
		////checksum
		i = rand.nextInt(5);
		temp = new StringBuffer(s.CHECKSUMcodeword.list.get(i));
		j = rand.nextInt(8);
		if(temp.charAt(j)=='1')
			temp.setCharAt(j, '0');
		else
			temp.setCharAt(j, '1');
		s.CHECKSUMcodeword.list.set(i, temp.toString());
		
		
		//crc
		i = rand.nextInt(4);
		temp = new StringBuffer(s.CRCcodeword.list.get(i));
		j = rand.nextInt(8+CRC.divisor.length()-1);//depends on size of crc polynomial(now 8 + (5-1))
		if(temp.charAt(j)=='1')
			temp.setCharAt(j, '0');
		else
			temp.setCharAt(j, '1');
		s.CRCcodeword.list.set(i, temp.toString());
	}
	
	//Arguments: Sender class object, size = no of consecutive bits to occur the burst error, c = choice between burst error resulting in stream of '0's or '1's
	
	void pushBurstError(Sender s,int size,char c)//c='0' or '1'
	{
		Random rand = new Random();
		//vrc
		StringBuffer temp = new StringBuffer(s.VRCcodeword.list.get(0)+s.VRCcodeword.list.get(1)+s.VRCcodeword.list.get(2)+s.VRCcodeword.list.get(3));
	
		int i = rand.nextInt(36),j=0;
		while(i<32 && j<size)
		{
				temp.setCharAt(i, c);
			i++;j++;
		}
		
		s.VRCcodeword.list.set(0,temp.substring(0,9));
		s.VRCcodeword.list.set(1,temp.substring(9,18));
		s.VRCcodeword.list.set(2,temp.substring(18,27));
		s.VRCcodeword.list.set(3,temp.substring(27));
		
		//lrc

		temp = new StringBuffer(s.LRCcodeword.list.get(0)+s.LRCcodeword.list.get(1)+s.LRCcodeword.list.get(2)+s.LRCcodeword.list.get(3)+s.LRCcodeword.list.get(4));
		
		i = rand.nextInt(40);
		j=0;
		while(i<40 && j<size)
		{
				temp.setCharAt(i, c);
			i++;j++;
		}
		
		s.LRCcodeword.list.set(0,temp.substring(0,8));
		s.LRCcodeword.list.set(1,temp.substring(8,16));
		s.LRCcodeword.list.set(2,temp.substring(16,24));
		s.LRCcodeword.list.set(3,temp.substring(24,32));
		s.LRCcodeword.list.set(4,temp.substring(32));
		//checksum
		
		temp = new StringBuffer(s.CHECKSUMcodeword.list.get(0)+s.CHECKSUMcodeword.list.get(1)+s.CHECKSUMcodeword.list.get(2)+s.CHECKSUMcodeword.list.get(3)+s.CHECKSUMcodeword.list.get(4));
		
		i = rand.nextInt(40);
		j=0;
		while(i<40 && j<size)
		{
				temp.setCharAt(i, c);
			i++;j++;
		}
		
		s.CHECKSUMcodeword.list.set(0,temp.substring(0,8));
		s.CHECKSUMcodeword.list.set(1,temp.substring(8,16));
		s.CHECKSUMcodeword.list.set(2,temp.substring(16,24));
		s.CHECKSUMcodeword.list.set(3,temp.substring(24,32));
		s.CHECKSUMcodeword.list.set(4,temp.substring(32));
		
		//crc
		temp = new StringBuffer(s.CRCcodeword.list.get(0)+s.CRCcodeword.list.get(1)+s.CRCcodeword.list.get(2)+s.CRCcodeword.list.get(3));
		
		i = rand.nextInt(32+4*(CRC.divisor.length()-1));
		j=0;
		while(i<32+4*(CRC.divisor.length()-1) && j<size)
		{
				temp.setCharAt(i, c);
			i++;j++;
		}
		int len = 8+CRC.divisor.length()-1;
		s.CRCcodeword.list.set(0,temp.substring(0,len));
		s.CRCcodeword.list.set(1,temp.substring(len,2*len));
		s.CRCcodeword.list.set(2,temp.substring(2*len,3*len));
		s.CRCcodeword.list.set(3,temp.substring(3*len));
		
		
	}
	
	
	//Enter the error manually as per the wish of user
	void putCustomError(Sender s,int packetnumber, int bitnumber) throws IndexOutOfBoundsException{
		
		//VRC
		StringBuffer temp = new StringBuffer(s.VRCcodeword.list.get(packetnumber));

		if(temp.charAt(bitnumber)=='1')
			temp.setCharAt(bitnumber, '0');
		else
			temp.setCharAt(bitnumber, '1');
		
		s.VRCcodeword.list.set(packetnumber, temp.toString());
		
		
		//LRC
		temp = new StringBuffer(s.LRCcodeword.list.get(packetnumber));

		if(temp.charAt(bitnumber)=='1')
			temp.setCharAt(bitnumber, '0');
		else
			temp.setCharAt(bitnumber, '1');
		
		s.LRCcodeword.list.set(packetnumber, temp.toString());
		
		//CHECKSUM
		temp = new StringBuffer(s.CHECKSUMcodeword.list.get(packetnumber));

		if(temp.charAt(bitnumber)=='1')
			temp.setCharAt(bitnumber, '0');
		else
			temp.setCharAt(bitnumber, '1');
		
		s.CHECKSUMcodeword.list.set(packetnumber, temp.toString());
		
		//CRC
		temp = new StringBuffer(s.CRCcodeword.list.get(packetnumber));

		if(temp.charAt(bitnumber)=='1')
			temp.setCharAt(bitnumber, '0');
		else
			temp.setCharAt(bitnumber, '1');
		
		s.CRCcodeword.list.set(packetnumber, temp.toString());
	}
}