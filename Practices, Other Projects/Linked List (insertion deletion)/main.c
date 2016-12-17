#include <stdio.h>
#include <malloc.h>

struct NODE *head;

typedef struct NODE
{
	int data;
	struct NODE *link;
}item,*itemptr;

int cntlist();
void insert(int pos, int n);
void del(int pos);
void display();

void main()
{
	int n = 1, s = 1, pos = 0;
	itemptr x, q;
	
	head = NULL;
	printf("Enter Positive Number(s):\n");	
	while(n>=0){
		scanf("%d", &n);
		if(n > 0){
			x = (itemptr)malloc(sizeof(item));
			x->data = n;
			x->link = NULL;
			if(head == NULL)
				head = x;
			else
				q->link = x;
			q = x;
		}
	}	
	
	while(s!=0){
		
		system("cls");
		display();
		printf("\n\nMenu:\n");
		printf("[1] Insert\n");
		printf("[2] Delete\n");
		printf("[Anykey] Exit\n\n");
		scanf("%d", &s);
		
		switch(s){
			case 1:
				system("cls");
				display();
				printf("\n\nInsertion: Select your choice\n");
				printf("[1] Beginning\n");
				printf("[2] End\n");
				printf("[3] Specify\n");
				printf("[Anykey] Return To Menu\n\n");	
				scanf("%d", &pos);
				printf("\nEnter the number you wanted to insert: ");
				scanf("%d", &n);
				if(pos==1){
					insert(0, n);
				}else if(pos==2){
					insert(cntlist(), n);
				}else if(pos==3){
					ins_oob:;					
					printf("\nEnter Desired Position(1 to %d): ", cntlist() + 1);
					scanf("%d", &pos);
					if(pos < 1 || pos > cntlist() + 1){
						printf("Out of Bounds\n");
						printf("Choose other Options\n");
						getch();
						goto ins_oob;
					}else{
						insert(pos - 1, n);
					}				
				}
				break;		
			case 2:
				if(cntlist() > 1){
					system("cls");
					display();
					printf("\n\nDeletion: Select your choice\n");
					printf("[1] Beginning\n");
					printf("[2] End\n");
					printf("[3] Specify\n");
					printf("[Anykey] Return To Menu\n\n");				
					scanf("%d", &pos);
					if(pos==1){
						del(0);
					}else if(pos==2){
						del(cntlist() - 1);
					}else if(pos==3){
						del_oob:;
						printf("\nEnter Desired Position(1 to %d): ", cntlist());
						scanf("%d", &pos);
						if(pos < 1 || pos > cntlist()){
							printf("Out of Bounds\n");
							printf("Choose other Options\n");
							getch();
							goto del_oob;
						}else{
							del(pos - 1);
						}
					}
				}else{
					printf("Can't delete Header Node");
					getch();
				}
				break;
			default:
			printf("\n\nExit");
			getch();
			s = 0;
		}
		
		
	}
}

void display(){
	itemptr x;
	x = head;
	printf("Values:\n");
	do{
		printf("%d ", x->data);
		x = x->link;
		
	}while(x!=NULL);
}

int cntlist(){
	int i = 0;
	itemptr x;
	x = head;
	do{
		i++;
		x = x->link;
	}while(x!=NULL);
	return i;
}

void insert(int pos, int n){
	int i;
	itemptr x, temp;
	temp = head;
	x = (itemptr)malloc(sizeof(item));
	x->data = n;
	
	if(pos == 0){
		x->link = head;
		head = x;
	}else{
		for(i = 1; i < pos; i++){
			temp = temp->link;
		}
		x->link = temp->link;
		temp->link = x;
	}	
}

void del(int pos){
	int i;
	itemptr x, temp;
	temp = head;
	
	if(pos == 0){
		head = head->link;
		free(temp);
	}else{
		for(i = 1; i < pos; i++){
			temp = temp->link;
		}
		x = temp->link;
		temp->link = x->link;
		free(x);
	}
}
