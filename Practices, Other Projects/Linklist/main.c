#include <stdio.h>
#include <stdlib.h>

struct NODE *head;

struct NODE
{
	int data;
	struct NODE *link;
};

int cntlist();
void insert(int pos, int n);
void del(int pos);
void display();

void main()
{
	int n = 1, s = 1, pos = 0, oob = 1;
	struct NODE *x;

	head = NULL;
	printf("Enter Positive Number(s) to continue inputting\n");
	while(1){
		scanf("%d", &n);
		if(n > 0){
			x = (struct NODE *)malloc(sizeof(struct NODE));
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
		printf("\nMenu:\n");
		printf("[1] Insert\n");
		printf("[2] Delete\n");
		printf("[Anykey] Exit\n");
		scanf("%d", &s);

		switch(s){
			case 1:
				oob = 1;
				while(oob != 0){
					system("cls");
					display();
					printf("\nInsertion: Select your choice\n");
					printf("[1] Beginning\n");
					printf("[2] End\n");
					printf("[3] Custom\n");
					printf("[Anykey] Return To Menu\n");
					scanf("%d", &pos);
					printf("\nEnter the number you wanted to insert: ");
					scanf("%d", &n);
					if(pos==1){
						insert(0, n);
						oob = 0;
					}else if(pos==2){
						insert(-1, n);
						oob = 0;
					}else if(pos==3){
						printf("Enter Custom Position(start from 0): ");
						scanf("%d", &pos);
						if(pos < 0 || pos > (cntlist(head) - 1)){
							printf("Out of Bounds\n");
							printf("Choose other Options\n");
							getch();
							oob = 1;
						}else{
							insert(pos, n);
							oob = 0;
						}

					}else
						oob = 0;
				}
				break;

			case 2:
				oob = 1;
				while(oob != 0){
					if(cntlist() > 1){
						system("cls");
						display();
						printf("\nDeletion: Select your choice\n");
						printf("[1] Beginning\n");
						printf("[2] End\n");
						printf("[3] Custom\n");
						printf("[Anykey] Return To Menu\n");

						scanf("%d", &pos);
						if(pos==1){
							del(0);
							oob = 0;
						}else if(pos==2){
							del(-1);
							oob = 0;
						}else if(pos==3){
							printf("Enter Custom Position(start from 0): ");
							scanf("%d", &pos);
							if(pos < 0 || pos > (cntlist(head) - 1)){
								printf("Out of Bounds\n");
								printf("Choose other Options\n");
								getch();
								oob = 1;
							}else{
								del(pos);
								oob = 0;
							}

						}else{
						oob = 0;
						}
					}else{
						printf("Can't delete Header Node");
						getch();
						oob = 0;
					}
				}
				break;
			default:
				printf("Exiting...");
				getch();
				s = 0;
		}


	}
}

void display(){
	struct NODE *x;
	x = head;
	do{
		printf("%d ", x->data);
		x = x->link;

	}while(x!=NULL);
}

int cntlist(){
	int i = 0;
	struct NODE *x;
	x = head;
	do{
		i++;
		x = x->link;
	}while(x!=NULL);
	return i;
}

void insert(int pos, int n){
	int i;
	struct NODE *x;
	struct NODE *temp;
	temp = head;
	x = (struct NODE *)malloc(sizeof(struct NODE));
	x->data = n;

	if(pos == 0){
		x->link = head;
		head = x;
	}else if(pos == -1){
		while(temp->link != NULL){
			temp = temp->link;
		}
		temp->link = x;
		x->link = NULL;

	}else{
		for(i = 1; i < pos; i++){
			temp = temp->link;
		}
		x->link = temp->link;
		temp->link = x;
	}
}

void delete(int pos){
	int i;
	struct NODE *x;
	struct NODE *temp;
	temp = head;

	if(pos == 0){
		head = head->link;
		free(temp);
	}else if(pos == -1){
		while(temp->link != NULL){
			x = temp;
			temp = temp->link;
		}
		free(temp);
		x->link = NULL;
	}else{
		for(i = 1; i < pos; i++){
			temp = temp->link;
		}
		x = temp->link;
		temp->link = x->link;
		free(x);
	}
}
