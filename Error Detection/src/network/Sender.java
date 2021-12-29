package network;

import java.io.*;
import java.util.*;
import errordetection.techniques.*;

class VRCsender implements Runnable{
	
	private FRAMES dataword_list = new FRAMES();
	private FRAMES codeword_list = new FRAMES();
	VRCsender(FRAMES dataword_list){
		//t = new Thread(this,"VRCThread");
		this.dataword_list = dataword_list;
	}
	
	public void run() {
				
		try {
			//VRC v = new VRC();
			
			codeword_list = VRC.createCodeword(dataword_list);
		}
		catch(Exception e) {
			System.out.print("Something went wrong checksum!");
		}
	}
	
//return the list of codewords
	FRAMES getCodewordList()
	{
		return codeword_list;
	}
}

class LRCsender implements Runnable{
	
	private FRAMES dataword_list = new FRAMES();
	private FRAMES codeword_list = new FRAMES();
	LRCsender(FRAMES dataword_list){
		//t = new Thread(this,"LRCThread");
		this.dataword_list = dataword_list;
	}

	public void run() {
				
		try {
			//LRC l = new LRC();
			
			codeword_list = LRC.createCodeword(dataword_list);
		}
		catch(Exception e) {
			System.out.print("Something went wrong!");
		}
	}

//return the list of codewords	
	FRAMES getCodewordList()
	{
		return codeword_list;
	}
}

class CHECKSUMsender implements Runnable{
	
	
	private FRAMES dataword_list = new FRAMES();
	private FRAMES codeword_list = new FRAMES();
	CHECKSUMsender(FRAMES dataword_list){
		//t = new Thread(this,"CHECKSUMThread");
		this.dataword_list = dataword_list;
	}
	
	
	public void run() {
				
		try {
			//CHECKSUM cs = new CHECKSUM();
			
			codeword_list = CHECKSUM.createCodeword(dataword_list);
		}
		catch(Exception e) {
			System.out.print("Something went wrong!");
		}
	}

//return the list of codewords
	FRAMES getCodewordList()
	{
		return codeword_list;
	}
}


class CRCsender implements Runnable{
	

	//String divisor;
	private FRAMES dataword_list = new FRAMES();
	private FRAMES codeword_list = new FRAMES();
	CRCsender(FRAMES dataword_list){
		//t = new Thread(this,"CRCThread");
		this.dataword_list = dataword_list;
	}
	
	public void run() {
				
		try {
			//CRC c = new CRC(divisor);
			
			codeword_list = CRC.createCodeword(dataword_list);
		}
		catch(Exception e) {
			System.out.print("Something went wrong!");
		}
	}

//return the list of codewords
	FRAMES getCodewordList()
	{
		return codeword_list;
	}

}

public class Sender{
	public FRAMES dataword_list;
	public FRAMES VRCcodeword;
	public FRAMES LRCcodeword;
	public FRAMES CHECKSUMcodeword;
	public FRAMES CRCcodeword;

	//copy constructor
	Sender()
	{
		
	}
	
	/*Constructor taking the filename as argument and storing the data in packets  and ultimately generating the 
	the lists of codewords by all 4 schemes*/
	Sender(String filename){
		
		//arr = new FRAMES[4];
		
		dataword_list = new FRAMES();
		String temp = new String();
		File f = new File(filename);
		try{
			Scanner sc = new Scanner(f);
			temp = String.format("%"+32+"s",sc.nextLine()).replace(' ','0');
			sc.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File not found!");
		}
		
		dataword_list.list.add(temp.substring(0,8));
		dataword_list.list.add(temp.substring(8,16));
		dataword_list.list.add(temp.substring(16,24));
		dataword_list.list.add(temp.substring(24));
		
		VRCsender v = new VRCsender(dataword_list);
		Thread tvrc = new Thread(v);
		LRCsender l = new LRCsender(dataword_list);
		Thread tlrc = new Thread(l);
		CHECKSUMsender cs = new CHECKSUMsender(dataword_list);
		Thread tcs = new Thread(cs);
		CRCsender c = new CRCsender(dataword_list);
		//c.getDivisor("1011");
		Thread tc = new Thread(c);
		
		//creating the codewords in parallel
		tvrc.start();
		tlrc.start();
		tcs.start();
		tc.start();
		
		//let the threads finish before main thread
		try {
			tvrc.join();
			tlrc.join();
			tcs.join();
			tc.join();
		}
		catch(InterruptedException e){
			System.out.println("Some Interruption occured in sender");
		}
		
		VRCcodeword = v.getCodewordList();
		LRCcodeword = l.getCodewordList();
		CHECKSUMcodeword = cs.getCodewordList();
		CRCcodeword = c.getCodewordList();
		
	}
	
	/*boolean isequalsVRC(Sender s)
	{
		int i;
		for(i=0;i<4;i++)
		{
			
			if(this.VRCcodeword.list.get(i).equals(s.VRCcodeword.list.get(i))==false)
				return false;
		}	
		return true;
	}*/
}
	