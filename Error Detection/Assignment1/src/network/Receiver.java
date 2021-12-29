package network;
//import java.util.*;
import errordetection.techniques.*;

/*Receiver class constructor takes argument a Sender class object
 * It checks and prints if the data is needed to be accepted or discarded
 */

public class Receiver{
	public int VRCflag,LRCflag,CHECKSUMflag,CRCflag;//1=accepted,0=discarded
	Receiver(Sender s){

		
		//checking VRC codeword
		if(VRC.checkCodeword(s.VRCcodeword))
			{
				VRCflag = 1;
			}
		else {
			VRCflag = 0;
		}
		
		//checking LRC codeword
		if(LRC.checkCodeword(s.LRCcodeword))
		{
			LRCflag = 1;
		}
		else {
			LRCflag = 0;
		}
		
		//checking CHECKSUM codeword
		if(CHECKSUM.checkCodeword(s.CHECKSUMcodeword))
		{
			CHECKSUMflag = 1;
		}
		else {
		CHECKSUMflag=0;
		}
	
		//checking CRC codeword
		if(CRC.checkCodeword(s.CRCcodeword))
		{
			CRCflag=1;
		}
		else {
		CRCflag=0;
		}
		
		
		
	}
}