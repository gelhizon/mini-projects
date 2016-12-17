#include <stdio.h>

int octet1, octet2, octet3, octet4, mask;
int hosts[50];

/*main(){
	int networks, i, totalhosts = 0, hostsavailable, hostsallocated, net1, net2, net3, net4, broadcast1, broadcast2, broadcast3, broadcast4;
	double bb;
	printf("Enter First Octet: ");
	scanf("%d", &octet1);
	printf("Enter Second Octet: ");
	scanf("%d", &octet2);
	printf("Enter Third Octet: ");
	scanf("%d", &octet3);
	printf("Enter Fourth Octet: ");
	scanf("%d", &octet4);
	printf("IP Adress: %d.%d.%d.%d", octet1, octet2, octet3, octet4);
	
	printf("\nEnter Subnet Mask: ");
	scanf("%d", &mask);
	printf("Subnet Mask: /%d", mask);
	
	printf("\nHow many networks you want to enter: ");
	scanf("%d", &networks);
	for(i = 0; i < networks; i++){
		printf("Network %d Hosts: ", i + 1);
		scanf("%d", &hosts[i]);
		bb = log(hosts[i] + 2) / log(2);
		if(bb != (int) bb){
			bb = bb - (bb - (int) bb) + 1;
		}
		totalhosts += pow(2, bb);
	}

	printf("\nTotal IP Addresses Needed: %d", totalhosts);
	
	if(mask / 8 == 3){
		hostsavailable = pow(2, 8 - (mask % 8));
		for(i = 1; i <= 8 - mask % 8; i++){
			if(octet4 % (int)pow(2, i) != 0){
				octet4 = octet4 - pow(2, i - 1);
			}
		}
	}else if(mask / 8 == 2){
		hostsavailable = pow(2, 8 - (mask % 8)) * 256;
		for(i = 1; i <= 8 - mask % 8; i++){
			if(octet3 % (int)pow(2, i) != 0){
				octet3 = octet3 - pow(2, i - 1);
			}
		}
		octet4 = 0;
	}else if(mask / 8 == 1){
		hostsavailable = pow(2, 8 - (mask % 8)) * 65536;
		for(i = 1; i <= 8 - mask % 8; i++){
			if(octet2 % (int)pow(2, i) != 0){
				octet2 = octet2 - pow(2, i - 1);
			}
		}
		octet4 = 0;
		octet3 = 0;
	}else if(mask / 8 == 0){
		hostsavailable = pow(2, 8 - (mask % 8)) * 16777216;
		for(i = 1; i <= 8 - mask % 8; i++){
			if(octet1 % (int)pow(2, i) != 0){
				octet1 = octet1 - pow(2, i - 1);
			}
		}
		octet4 = 0;
		octet3 = 0;
		octet2 = 0;
	}	
	printf("\nIP Addresses Available: %d", hostsavailable);
	printf("\nNetwork IP Address: %d.%d.%d.%d/%d\n", octet1, octet2, octet3, octet4, mask);
	
	printf("\nNetwork\tHosts\tNetwork Address\tSubnetMask\tBroadcast Address");
	for(i = 0; i < networks; i++){
		bb = log(hosts[i] + 2) / log(2);
		if(bb != (int) bb){
			bb = bb - (bb - (int) bb) + 1;
		}
		hostsallocated = pow(2, bb);
		
		net1 = octet1;
		net2 = octet2;
		net3 = octet3;
		net4 = octet4;
		
		octet4 += hostsallocated;
		octet3 += octet4 / 256;
		octet4 %= 256;
		octet2 += octet3 / 256;
		octet3 %= 256;
		octet1 += octet2 / 256;
		octet2 %= 256;
		
		broadcast4 = octet4 - 1;
		broadcast3 = octet3;
		broadcast2 = octet2;
		broadcast1 = octet1;
		if(octet4 == 0){
			broadcast4 = 255;
			if(octet3 == 0){
				broadcast3 = 255;
				if(octet2 == 0){
					broadcast2 = 255;
				}else{
					broadcast2 = octet2 - 1;
				}
			}else{
				broadcast3 = octet3 - 1;
			}
		}
		printf("\n%d\t%d\t%d.%d.%d.%d\t/%d\t\t%d.%d.%d.%d", i + 1, hosts[i], net1, net2, net3, net4, 32 - (int) bb, broadcast1, broadcast2, broadcast3, broadcast4);
	}
	return 0;
}
*/
