#include <stdio.h>
#include <math.h>

main(){
	int octet1, octet2, octet3, octet4, mask, hosts[50], networks, i, j, hostsallocated;
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
	}
	
	printf("\nNetwork IP Address: %d.%d.%d.%d/%d\n", octet1, octet2, octet3, octet4, mask);
	
	printf("\nNetwork\tHosts\tNetwork Address\tPrefix\tSNMask\tWCMask");
	for(i = 0; i < networks; i++){
		bb = log(hosts[i] + 2) / log(2);
	
		if(bb != (int) bb){
			bb = (int) bb + 1;
		}
		hostsallocated = pow(2, bb);
		
		printf("\n%d\t%d\t%d.%d.%d.%d\t/%d\t%d\t%d", i + 1, hosts[i], octet1, octet2, octet3, octet4, 32 - (int)bb, 256 - hostsallocated % 255, hostsallocated % 255 - 1);
		
		octet4 += hostsallocated;
		octet3 += octet4 / 256;
		octet4 %= 256;
		octet2 += octet3 / 256;
		octet3 %= 256;
		octet1 += octet2 / 256;
		octet2 %= 256;
		
	}
	getch();
	return 0;
}
